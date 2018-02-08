package org.terrier.remote.api;

import org.terrier.remote.api.*;

import javax.ws.rs.core.Response;
import javax.ws.rs.core.SecurityContext;

@javax.annotation.Generated(value = "class io.swagger.codegen.languages.JavaJerseyServerCodegen", date = "2017-11-22T14:19:28.322Z")
public abstract class DocumentsApiService {
    public abstract Response addDocuments(String collection,String path,SecurityContext securityContext) throws NotFoundException;
    public abstract Response deleteDocuments(String collection,String documents,SecurityContext securityContext) throws NotFoundException;
    public abstract Response getDocuments(String collection,SecurityContext securityContext) throws NotFoundException;
}
