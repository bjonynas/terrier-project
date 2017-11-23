package org.terrier.remote.api.impl;

import org.terrier.querying.Manager;
import org.terrier.remote.api.*;

import org.terrier.remote.model.IndexStats;
import org.terrier.remote.model.KeyValue;
import org.terrier.remote.model.RemoteIndex;
import org.terrier.remote.model.RemoteResultSet;
import org.terrier.structures.CollectionStatistics;
import org.terrier.structures.Index;

import org.terrier.utility.Files;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Properties;

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
                if(Files.exists(remoteIndex.getPath())) {
                    Index i = Index.createIndex(remoteIndex.getPath(), remoteIndex.getName());
                    ImportedIndexes.addIndex(remoteIndex.getName(), i);
                }
                //If an index is not found at the specified error, return 404
                else{
                    return Response.status(404).entity((new ApiResponseMessage(ApiResponseMessage.ERROR,
                            "Index not found at specified path"))).build();
                }
            }
            return Response.ok().entity(new ApiResponseMessage(ApiResponseMessage.OK, remoteIndex.getName())).build();
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
    public Response retrieve(String indexId, SecurityContext securityContext) throws NotFoundException {
//
//        //create manager
//        if(!ImportedIndexes.getIndexes().containsKey(indexId)){
//            return Response.status(404).entity(new ApiResponseMessage(ApiResponseMessage.ERROR, "index not found")).build();
//        }
//        Manager indexManager = new Manager(ImportedIndexes.getIndexes().get(indexId));
//
//        //run searchRequest
//        //build remoteResultSet
//        //return

        return Response.ok().entity(new ApiResponseMessage(ApiResponseMessage.OK, "magic!")).build();
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
