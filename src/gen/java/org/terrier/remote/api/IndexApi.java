package org.terrier.remote.api;

import org.terrier.remote.model.*;
import org.terrier.remote.api.IndexApiService;
import org.terrier.remote.api.factories.IndexApiServiceFactory;

import io.swagger.annotations.ApiParam;
import io.swagger.jaxrs.*;

import org.terrier.remote.model.IndexStats;
import org.terrier.remote.model.RemoteIndex;
import org.terrier.remote.model.RemoteResultSet;

import java.util.List;
import org.terrier.remote.api.NotFoundException;

import java.io.InputStream;

import org.glassfish.jersey.media.multipart.FormDataContentDisposition;
import org.glassfish.jersey.media.multipart.FormDataParam;

import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.SecurityContext;
import javax.ws.rs.*;

@Path("/index")


@io.swagger.annotations.Api(description = "the index API")
@javax.annotation.Generated(value = "class io.swagger.codegen.languages.JavaJerseyServerCodegen", date = "2017-11-23T16:54:04.803Z")
public class IndexApi  {
   private final IndexApiService delegate = IndexApiServiceFactory.getIndexApi();

    @GET
    
    
    @Produces({ "application/json" })
    @io.swagger.annotations.ApiOperation(value = "get all available indexes", notes = "Get all available indexes ", response = RemoteIndex.class, responseContainer = "List", tags={  })
    @io.swagger.annotations.ApiResponses(value = { 
        @io.swagger.annotations.ApiResponse(code = 200, message = "list of available indexes", response = RemoteIndex.class, responseContainer = "List"),
        
        @io.swagger.annotations.ApiResponse(code = 500, message = "server error while retrieving indexes", response = RemoteIndex.class, responseContainer = "List") })
    public Response getIndexes(@Context SecurityContext securityContext)
    throws NotFoundException {
        return delegate.getIndexes(securityContext);
    }
    @POST
    @Path("/import")
    @Consumes({ "application/json" })
    @Produces({ "application/json" })
    @io.swagger.annotations.ApiOperation(value = "import existing index and return its id", notes = "", response = String.class, tags={  })
    @io.swagger.annotations.ApiResponses(value = { 
        @io.swagger.annotations.ApiResponse(code = 200, message = "index successfuly created", response = String.class),
        
        @io.swagger.annotations.ApiResponse(code = 400, message = "incorrect input", response = String.class) })
    public Response importIndex(@ApiParam(value = "Index object to be imported. Returns the name of the imported index" ,required=true) RemoteIndex index
,@Context SecurityContext securityContext)
    throws NotFoundException {
        return delegate.importIndex(index,securityContext);
    }
    @POST
    @Path("/new")
    @Consumes({ "multipart/form-data" })
    @Produces({ "application/json" })
    @io.swagger.annotations.ApiOperation(value = "create a new index", notes = "Create a new index for a collection of documents ", response = void.class, tags={  })
    @io.swagger.annotations.ApiResponses(value = { 
        @io.swagger.annotations.ApiResponse(code = 200, message = "index successfuly created", response = void.class),
        
        @io.swagger.annotations.ApiResponse(code = 400, message = "incorrect input", response = void.class) })
    public Response newIndex(@ApiParam(value = "Path to the collection", required=true)@FormDataParam("path")  String path
,@ApiParam(value = "Type of documents to be indexed", required=true)@FormDataParam("documentType")  String documentType
,@Context SecurityContext securityContext)
    throws NotFoundException {
        return delegate.newIndex(path,documentType,securityContext);
    }
    @GET
    @Path("/{indexId}/retrieve")
    
    @Produces({ "application/json" })
    @io.swagger.annotations.ApiOperation(value = "run a query", notes = "Runs a query ", response = RemoteResultSet.class, responseContainer = "List", tags={  })
    @io.swagger.annotations.ApiResponses(value = { 
        @io.swagger.annotations.ApiResponse(code = 200, message = "query results", response = RemoteResultSet.class, responseContainer = "List"),
        
        @io.swagger.annotations.ApiResponse(code = 403, message = "incorrect input", response = RemoteResultSet.class, responseContainer = "List"),
        
        @io.swagger.annotations.ApiResponse(code = 404, message = "index not found", response = RemoteResultSet.class, responseContainer = "List") })
    public Response retrieve(@ApiParam(value = "Id of the index to be queried",required=true) @PathParam("indexId") String indexId
,@Context SecurityContext securityContext)
    throws NotFoundException {
        return delegate.retrieve(indexId,securityContext);
    }
    @GET
    @Path("/{indexId}/stats")
    
    @Produces({ "application/json" })
    @io.swagger.annotations.ApiOperation(value = "view stats about the specified index", notes = "View stats about the index ", response = IndexStats.class, tags={  })
    @io.swagger.annotations.ApiResponses(value = { 
        @io.swagger.annotations.ApiResponse(code = 200, message = "stats object for the specified index", response = IndexStats.class),
        
        @io.swagger.annotations.ApiResponse(code = 404, message = "index not found", response = IndexStats.class) })
    public Response stats(@ApiParam(value = "",required=true) @PathParam("indexId") String indexId
,@Context SecurityContext securityContext)
    throws NotFoundException {
        return delegate.stats(indexId,securityContext);
    }
}
