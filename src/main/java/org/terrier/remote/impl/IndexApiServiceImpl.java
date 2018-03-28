package org.terrier.remote.impl;

import com.drew.lang.annotations.NotNull;
import org.terrier.matching.ResultSet;
import org.terrier.matching.models.queryexpansion.Bo1;
import org.terrier.querying.Manager;
import org.terrier.querying.SearchRequest;
import org.terrier.remote.api.*;

import org.terrier.remote.model.*;
import org.terrier.structures.CollectionStatistics;
import org.terrier.structures.Index;
import java.util.*;

import org.terrier.remote.api.NotFoundException;
import org.terrier.structures.IndexOnDisk;
import org.terrier.structures.MetaIndex;
import org.terrier.utility.ApplicationSetup;

import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.SecurityContext;

@javax.annotation.Generated(value = "class io.swagger.codegen.languages.JavaJerseyServerCodegen", date = "2017-11-22T14:19:28.322Z")
public class IndexApiServiceImpl extends IndexApiService {

    @Override
    public Response getIndexes(SecurityContext securityContext) throws NotFoundException {
        try{
            //build the array of index objects to be returned
            LinkedList<RemoteIndex> indexList = new LinkedList<RemoteIndex>();

            for(String key : ImportedIndexes.getIndexes().keySet()){

                //Build a RemoteIndex object from the Index object in the importedIndex list
                RemoteIndex index = new RemoteIndex();
                index.setIndexName(key);
                index.setPrefix(((IndexOnDisk) (ImportedIndexes.getIndexes().get(key))).getPrefix());
                Properties properties = ImportedIndexes.getIndexes().get(key).getProperties();

                index.setPath(((IndexOnDisk) ImportedIndexes.getIndexes().get(key)).getPath());

                LinkedList<KeyValue> indexProperties = new LinkedList();

                Iterator itr = properties.keySet().iterator();
                while(itr.hasNext()){
                    String propName = (String) itr.next();

                    KeyValue prop = new KeyValue();
                    prop.setPropName(propName);
                    prop.setPropValue(properties.getProperty(propName));

                    indexProperties.add(prop);
                }
                index.setProps(indexProperties);

                indexList.add(index);
            }

            return Response.ok(indexList, MediaType.APPLICATION_JSON).build();
        }
        catch (Exception e){
            return Response.status(500).entity(new ApiResponseMessage(ApiResponseMessage.ERROR, "Error getting index list")).build();
        }
    }

    @Override
    public Response importIndex(RemoteIndex remoteIndex, SecurityContext securityContext) throws NotFoundException {

        //If the specified index is not already imported, add a new Index object to the map
        try{
            if(!(ImportedIndexes.getIndexes().containsKey(remoteIndex.getIndexName()))) {

                Index i = Index.createIndex(remoteIndex.getPath(), remoteIndex.getPrefix());
                if (i != null) {
                    ImportedIndexes.addIndex(remoteIndex.getIndexName(), i);
                }
                //If an index is not found at the specified path, return 404
                else {
                    System.out.println("\n Index not found: " + remoteIndex.getPath() + " " + remoteIndex.getPrefix());
                    return Response.status(404).entity((new ApiResponseMessage(ApiResponseMessage.ERROR,
                            "Index not found at specified path " + Index.getLastIndexLoadError()))).build();
                }
            }

            return Response.ok(remoteIndex.getIndexName(), MediaType.APPLICATION_JSON).build();
        }
        catch (Exception e){
            return Response.status(500).entity(new ApiResponseMessage(ApiResponseMessage.ERROR, "Server error")).build();
        }
    }

    @Override
    public Response deleteIndex(String indexName, SecurityContext securityContext) throws NotFoundException {
        if(!ImportedIndexes.getIndexes().containsKey(indexName)){
            return Response.status(404).entity(new ApiResponseMessage(ApiResponseMessage.ERROR, "index not found")).build();
        }

        ImportedIndexes.getIndexes().remove(indexName);
        ImportedIndexes.getManagers().remove(indexName);

        return Response.ok("Deleted " + indexName, MediaType.APPLICATION_JSON).build();
    }

    @Override
    public Response retrieve(String indexName,  @NotNull String queryString,  String queryId,  String matchingModel,  String weightingModel,  List<String> queryControlNames,  List<String> queryControlValues, SecurityContext securityContext) throws NotFoundException {
        //create manager
        if(!ImportedIndexes.getIndexes().containsKey(indexName)){
            return Response.status(404).entity(new ApiResponseMessage(ApiResponseMessage.ERROR, "index not found")).build();
        }
        Manager indexManager = null;
        if(ImportedIndexes.getManagers().containsKey(indexName)){
            indexManager = ImportedIndexes.getManagers().get(indexName);
        } else{
            indexManager = new Manager(ImportedIndexes.getIndexes().get(indexName));
            ImportedIndexes.addManager(indexName, indexManager);
        }

        boolean queryExpansion = false;
        //create property list and set the retrieval properties using the manager setProperties method
        Properties props = new Properties();
        for(int p = 0; p < queryControlNames.size(); p++){
            if(queryControlNames.get(p).equals("queryExpansion")){
                queryExpansion = Boolean.parseBoolean(queryControlValues.get(p));
            }
            props.setProperty(queryControlNames.get(p), queryControlValues.get(p));
        }
        indexManager.setProperties(props);

        //create searchRequest
        SearchRequest srq = null;

        try{
            if(queryId == null){
                srq = indexManager.newSearchRequestFromQuery(queryString);
            } else{
                srq = indexManager.newSearchRequest(queryId, queryString);
            }
        } catch(Exception e){
            e.printStackTrace();
            return Response.status(403).entity(new ApiResponseMessage(ApiResponseMessage.ERROR, "failed to parse query")).build();

        }

        if(matchingModel == null || matchingModel == "") {
            matchingModel = org.terrier.matching.daat.Full.class.getName();
        }
        if(weightingModel == null || weightingModel == "") {
            weightingModel = "BM25";
        }
        srq.addMatchingModel(matchingModel, weightingModel);

        MetaIndex metaIndex = ImportedIndexes.getIndexes().get(indexName).getMetaIndex();

        srq.setControl("qemodel", ApplicationSetup.getProperty("trec.qe.model", Bo1.class.getName()));
        if(queryExpansion)
            System.out.println("\n query expansion: true");
            srq.setControl("qe", "on");
        //run query
        indexManager.runSearchRequest(srq);
        ResultSet results = srq.getResultSet();

        //build remoteResultSet
        RemoteResultSet remoteResults = new RemoteResultSet();

        List<ResultDocument> docList = new LinkedList<>();
        int[] dId = results.getDocids();
        double[] dScores = results.getScores();
        String[] keys = metaIndex.getKeys();

        for(int x=0; x < dId.length; x++){

            ResultDocument doc = new ResultDocument();
            doc.setDocId(dId[x]);
            doc.setScore(dScores[x]);
            String[] metaItems = new String[0];
            try{
                metaItems = metaIndex.getItems(keys, x);
            } catch (Exception e) {
                e.printStackTrace();
            }

            LinkedList<String> keyList = new LinkedList<>();
            LinkedList<String> valueList = new LinkedList<>();
            for(int k=0; k<keys.length; k++){
                keyList.add(keys[k]);
                if(metaItems.length > 0){
                    valueList.add(metaItems[k]);
                }
            }

            Metadata docMeta = new Metadata();
            docMeta.setMetaKeys(keyList);
            docMeta.setMetaItems(valueList);
            doc.setMetadata(docMeta);
            docList.add(doc);
        }

        remoteResults.setDocuments(docList);
        remoteResults.setResultSize(results.getResultSize());

        //convert results to json and return
        return Response.ok(remoteResults, MediaType.APPLICATION_JSON).build();
    }

    @Override
    public Response stats(String indexName, SecurityContext securityContext) throws NotFoundException {

        if(!ImportedIndexes.getIndexes().containsKey(indexName)){
            return Response.status(404).entity(new ApiResponseMessage(ApiResponseMessage.ERROR, "Index not found")).build();
        }

        Index index = ImportedIndexes.getIndexes().get(indexName);
        CollectionStatistics stats = index.getCollectionStatistics();

        IndexStats remoteStats = new IndexStats();
        remoteStats.setNumberOfDocuments(stats.getNumberOfDocuments());
        remoteStats.setNumberOfTerms(stats.getNumberOfUniqueTerms());
        remoteStats.setNumberOfPointers(stats.getNumberOfPointers());
        remoteStats.setNumberOfTokens(stats.getNumberOfTokens());

        return Response.ok(remoteStats, MediaType.APPLICATION_JSON).build();
    }
}
