package org.terrier.remote.api.impl;

import com.drew.lang.annotations.NotNull;
import org.terrier.matching.ResultSet;
import org.terrier.querying.Manager;
import org.terrier.querying.RemoteClientManager;
import org.terrier.querying.SearchRequest;
import org.terrier.querying.parser.QueryParserException;
import org.terrier.remote.api.*;

import org.terrier.remote.model.*;
import org.terrier.structures.CollectionStatistics;
import org.terrier.structures.Index;
import java.util.*;

import org.terrier.remote.api.NotFoundException;

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
                index.setName(key);
                Properties properties = ImportedIndexes.getIndexes().get(key).getProperties();

                index.setPath(properties.getProperty("terrier.index.path"));

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
            if(!(ImportedIndexes.getIndexes().containsKey(remoteIndex.getName()))){

                Index i = Index.createIndex(remoteIndex.getPath(), remoteIndex.getName());
                if(i != null) {
                    ImportedIndexes.addIndex(remoteIndex.getName(), i);
                }
                //If an index is not found at the specified error, return 404
                else{
                    return Response.status(404).entity((new ApiResponseMessage(ApiResponseMessage.ERROR,
                            "Index not found at specified path " + Index.getLastIndexLoadError()))).build();
                }
            }
            //
            return Response.ok(remoteIndex.getName(), MediaType.APPLICATION_JSON).build();
            //return Response.ok().entity(new ApiResponseMessage(ApiResponseMessage.OK, remoteIndex.getName())).build();
        }
        catch (Exception e){
            return Response.status(500).entity(new ApiResponseMessage(ApiResponseMessage.ERROR, "Server error")).build();
        }
    }

    @Override
    public Response newIndex(String path, String documentType, SecurityContext securityContext) throws NotFoundException {
        // do some magic!
        return Response.ok().entity(new ApiResponseMessage(ApiResponseMessage.OK, "magic!")).build();
    }

    @Override
    public Response retrieve(String indexId, @NotNull String queryString, String queryId, List<String> queryControlNames, List<String> queryControlValues, SecurityContext securityContext) throws NotFoundException {
        //create manager
        if(!ImportedIndexes.getIndexes().containsKey(indexId)){
            return Response.status(404).entity(new ApiResponseMessage(ApiResponseMessage.ERROR, "index not found")).build();
        }
        Manager indexManager = null;

        if(ImportedIndexes.getManagers().containsKey(indexId)){
             indexManager = ImportedIndexes.getManagers().get(indexId);
        }
        else{
             indexManager = new Manager(ImportedIndexes.getIndexes().get(indexId));
            ImportedIndexes.addManager(indexId, indexManager);
        }

        //create property list and set the retrieval properties using the manager setProperties method
        Properties props = new Properties();
        for(int p = 0; p<queryControlNames.size(); p++){
            props.setProperty(queryControlNames.get(p), queryControlValues.get(p));
        }
        indexManager.setProperties(props);

        //create searchRequest
        SearchRequest srq = null;

        try{
            if(indexId == ""){
                srq = indexManager.newSearchRequestFromQuery(queryString);
            } else{
                srq = indexManager.newSearchRequest(queryId, queryString);
            }
        } catch(Exception e){
            e.printStackTrace();
            return Response.status(403).entity(new ApiResponseMessage(ApiResponseMessage.ERROR, "failed to parse query")).build();

        }


        srq.addMatchingModel("org.terrier.matching.daat.Full", "BM25");
        //run query
        indexManager.runSearchRequest(srq);
        ResultSet results = srq.getResultSet();

        //build remoteResultSet
        RemoteResultSet remoteResults = new RemoteResultSet();

        //ArrayList<Integer> l1 = Arrays.asList(results.getDocids());
        List<Integer> docIdList = new ArrayList<Integer>();
        int[] dId = results.getDocids();
        for(int e=0; e < dId.length; e++){
            docIdList.add(dId[e]);
        }

        //ArrayList<Double> l2 = Arrays.asList(results.getScores());
        List<Double> scoreList= new ArrayList<Double>();
        double[] sL = results.getScores();
        for(int e=0; e < sL.length; e++){
            scoreList.add(sL[e]);
        }

        Metadata meta = new Metadata();
        List<String> metaKeys = Arrays.asList(results.getMetaKeys());
        List<List<String>> metaItems = new ArrayList<List<String>>();
        for(int e=0; e<metaKeys.size(); e++){
            metaItems.add(Arrays.asList(results.getMetaItems( metaKeys.get(e))));
        }
        meta.setMetaKeys(metaKeys);
        meta.setMetaItems(metaItems);

        remoteResults.setDocIds(docIdList);
        remoteResults.setScores(scoreList);
        remoteResults.setResultSize(results.getResultSize());
        remoteResults.setMetadata(meta);

        //convert results to json and return

        return Response.ok(remoteResults, MediaType.APPLICATION_JSON).build();
    }

    @Override
    public Response stats(String indexId, SecurityContext securityContext) throws NotFoundException {

        if(!ImportedIndexes.getIndexes().containsKey(indexId)){
            return Response.status(404).entity(new ApiResponseMessage(ApiResponseMessage.ERROR, "Index not found")).build();
        }

        Index index = ImportedIndexes.getIndexes().get(indexId);
        CollectionStatistics stats = index.getCollectionStatistics();

        IndexStats remoteStats = new IndexStats();
        remoteStats.setNumberOfDocuments(stats.getNumberOfDocuments());
        remoteStats.setNumberOfTerms(stats.getNumberOfUniqueTerms());
        remoteStats.setNumberOfPointers(stats.getNumberOfPointers());
        remoteStats.setNumberOfTokens(stats.getNumberOfTokens());

        return Response.ok(remoteStats, MediaType.APPLICATION_JSON).build();
    }
}
