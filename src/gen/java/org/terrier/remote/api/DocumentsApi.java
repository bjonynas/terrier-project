package org.terrier.remote.api;

import org.terrier.remote.api.factories.DocumentsApiServiceFactory;

import io.swagger.annotations.ApiParam;

import org.terrier.remote.model.Document;

import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.SecurityContext;
import javax.ws.rs.*;

@Path("/documents")


@io.swagger.annotations.Api(description = "the documents API")
@javax.annotation.Generated(value = "class io.swagger.codegen.languages.JavaJerseyServerCodegen", date = "2017-11-22T14:19:28.322Z")
public class DocumentsApi  {
   private final DocumentsApiService delegate = DocumentsApiServiceFactory.getDocumentsApi();

    @POST
    @Path("/{collection}")
    @Consumes({ "application/x-www-form-urlencoded" })
    @Produces({ "application/json" })
    @io.swagger.annotations.ApiOperation(value = "add documents to a collection", notes = "Add documents to the specified collection ", response = void.class, tags={  })
    @io.swagger.annotations.ApiResponses(value = { 
        @io.swagger.annotations.ApiResponse(code = 200, message = "successfully added documents", response = void.class),
        
        @io.swagger.annotations.ApiResponse(code = 400, message = "invalid input", response = void.class),
        
        @io.swagger.annotations.ApiResponse(code = 500, message = "server error while adding documents", response = void.class) })
    public Response addDocuments(@ApiParam(value = "path of the collection for which documents are to be shown",required=true) @PathParam("collection") String collection
,@ApiParam(value = "path to the documents to be added", required=true)  @FormParam("path")  String path
,@Context SecurityContext securityContext)
    throws NotFoundException {
        return delegate.addDocuments(collection,path,securityContext);
    }
    @DELETE
    @Path("/{collection}")
    @Consumes({ "application/json" })
    @Produces({ "application/json" })
    @io.swagger.annotations.ApiOperation(value = "delete document from specified collection", notes = "Delete document(s) from the specified collection ", response = void.class, tags={  })
    @io.swagger.annotations.ApiResponses(value = { 
        @io.swagger.annotations.ApiResponse(code = 200, message = "documents successfully deleted", response = void.class),
        
        @io.swagger.annotations.ApiResponse(code = 400, message = "invalid input", response = void.class),
        
        @io.swagger.annotations.ApiResponse(code = 500, message = "server error while deleting documents", response = void.class) })
    public Response deleteDocuments(@ApiParam(value = "path of the index from the collection of which documents are to be deleted",required=true) @PathParam("collection") String collection
,@ApiParam(value = "document to be deleted",required=true) @QueryParam("documents") String documents
,@Context SecurityContext securityContext)
    throws NotFoundException {
        return delegate.deleteDocuments(collection,documents,securityContext);
    }
    @GET
    @Path("/{collection}")
    
    @Produces({ "application/json" })
    @io.swagger.annotations.ApiOperation(value = "get list of documents in the specified collection", notes = "Get a list of all documents in specified collection\" ", response = Document.class, responseContainer = "List", tags={  })
    @io.swagger.annotations.ApiResponses(value = { 
        @io.swagger.annotations.ApiResponse(code = 200, message = "list of documents", response = Document.class, responseContainer = "List"),
        
        @io.swagger.annotations.ApiResponse(code = 404, message = "collection not found", response = Document.class, responseContainer = "List") })
    public Response getDocuments(@ApiParam(value = "path of the collection from which documents are to be shown",required=true) @PathParam("collection") String collection
,@Context SecurityContext securityContext)
    throws NotFoundException {
        return delegate.getDocuments(collection,securityContext);
    }
}
