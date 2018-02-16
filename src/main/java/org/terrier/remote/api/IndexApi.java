package org.terrier.remote.api;

import org.terrier.remote.model.*;
import org.terrier.remote.api.IndexApiService;
import org.terrier.remote.factories.IndexApiServiceFactory;

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


import javax.servlet.ServletConfig;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.SecurityContext;
import javax.ws.rs.*;

@Path("/index")


@io.swagger.annotations.Api(description = "the index API")
@javax.annotation.Generated(value = "io.swagger.codegen.languages.JavaJerseyServerCodegen", date = "2018-02-16T10:01:36.412Z")
public class IndexApi  {
 private final IndexApiService delegate;

 public IndexApi(@Context ServletConfig servletContext) {
  IndexApiService delegate = null;

  if (servletContext != null) {
   String implClass = servletContext.getInitParameter("IndexApi.implementation");
   if (implClass != null && !"".equals(implClass.trim())) {
    try {
     delegate = (IndexApiService) Class.forName(implClass).newInstance();
    } catch (Exception e) {
     throw new RuntimeException(e);
    }
   }
  }

  if (delegate == null) {
   delegate = IndexApiServiceFactory.getIndexApi();
  }

  this.delegate = delegate;
 }

 @DELETE
 @Path("/{indexName}")

 @Produces({ "application/json" })
 @io.swagger.annotations.ApiOperation(value = "remove specified index from the list of imported indexes", notes = "Remove the specified index from the list of imported indexes ", response = String.class, tags={  })
 @io.swagger.annotations.ApiResponses(value = {
         @io.swagger.annotations.ApiResponse(code = 200, message = "index successfuly deleted", response = String.class),

         @io.swagger.annotations.ApiResponse(code = 404, message = "specified index not found", response = Void.class) })
 public Response deleteIndex(@ApiParam(value = "",required=true) @PathParam("indexName") String indexName
         ,@Context SecurityContext securityContext)
         throws NotFoundException {
  return delegate.deleteIndex(indexName,securityContext);
 }
 @GET


 @Produces({ "application/json" })
 @io.swagger.annotations.ApiOperation(value = "get all available indexes", notes = "Get all available indexes ", response = RemoteIndex.class, responseContainer = "List", tags={  })
 @io.swagger.annotations.ApiResponses(value = {
         @io.swagger.annotations.ApiResponse(code = 200, message = "list of available indexes", response = RemoteIndex.class, responseContainer = "List"),

         @io.swagger.annotations.ApiResponse(code = 500, message = "server error while retrieving indexes", response = Void.class) })
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

         @io.swagger.annotations.ApiResponse(code = 400, message = "incorrect input", response = Void.class) })
 public Response importIndex(@ApiParam(value = "Index object to be imported. Returns the name of the imported index" ,required=true) RemoteIndex index
         ,@Context SecurityContext securityContext)
         throws NotFoundException {
  return delegate.importIndex(index,securityContext);
 }
 @GET
 @Path("/{indexName}/retrieve")
 @Consumes({ "application/json" })
 @Produces({ "application/json" })
 @io.swagger.annotations.ApiOperation(value = "run a query", notes = "Runs a query ", response = RemoteResultSet.class, tags={  })
 @io.swagger.annotations.ApiResponses(value = {
         @io.swagger.annotations.ApiResponse(code = 200, message = "query results", response = RemoteResultSet.class),

         @io.swagger.annotations.ApiResponse(code = 403, message = "incorrect input", response = Void.class),

         @io.swagger.annotations.ApiResponse(code = 404, message = "index not found", response = Void.class) })
 public Response retrieve(@ApiParam(value = "Id of the index to be queried",required=true) @PathParam("indexName") String indexName
         ,@ApiParam(value = "",required=true) @QueryParam("queryString") String queryString
         ,@ApiParam(value = "") @QueryParam("queryId") String queryId
         ,@ApiParam(value = "") @QueryParam("matchingModel") String matchingModel
         ,@ApiParam(value = "") @QueryParam("weightingModel") String weightingModel
         ,@ApiParam(value = "") @QueryParam("queryControlNames") List<String> queryControlNames
         ,@ApiParam(value = "") @QueryParam("queryControlValues") List<String> queryControlValues
         ,@Context SecurityContext securityContext)
         throws NotFoundException {
  return delegate.retrieve(indexName,queryString,queryId,matchingModel,weightingModel,queryControlNames,queryControlValues,securityContext);
 }
 @GET
 @Path("/{indexName}")

 @Produces({ "application/json" })
 @io.swagger.annotations.ApiOperation(value = "view stats about the specified index", notes = "View stats about the index ", response = IndexStats.class, tags={  })
 @io.swagger.annotations.ApiResponses(value = {
         @io.swagger.annotations.ApiResponse(code = 200, message = "stats object for the specified index", response = IndexStats.class),

         @io.swagger.annotations.ApiResponse(code = 404, message = "index not found", response = Void.class) })
 public Response stats(@ApiParam(value = "",required=true) @PathParam("indexName") String indexName
         ,@Context SecurityContext securityContext)
         throws NotFoundException {
  return delegate.stats(indexName,securityContext);
 }
}
