package org.terrier.remote.api;

import org.terrier.remote.api.*;

import org.terrier.remote.model.RemoteIndex;
import org.terrier.remote.model.RemoteResultSet;

import java.util.List;

import javax.ws.rs.core.Response;
import javax.ws.rs.core.SecurityContext;

@javax.annotation.Generated(value = "class io.swagger.codegen.languages.JavaJerseyServerCodegen", date = "2017-11-22T14:19:28.322Z")
public abstract class IndexApiService {
    public abstract Response getIndexes(SecurityContext securityContext) throws NotFoundException;
    public abstract Response importIndex(RemoteIndex index,SecurityContext securityContext) throws NotFoundException;
    public abstract Response newIndex(String path,String documentType,SecurityContext securityContext) throws NotFoundException;
    public abstract Response retrieve(String indexId,SecurityContext securityContext) throws NotFoundException;
    public abstract Response stats(String indexId,SecurityContext securityContext) throws NotFoundException;
}
