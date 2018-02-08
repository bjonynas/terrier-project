package org.terrier.remote.api;

import com.drew.lang.annotations.NotNull;
import org.terrier.remote.api.*;

import org.terrier.remote.model.RemoteIndex;
import org.terrier.remote.model.RemoteResultSet;

import java.util.List;

import javax.ws.rs.core.Response;
import javax.ws.rs.core.SecurityContext;

@javax.annotation.Generated(value = "io.swagger.codegen.languages.JavaJerseyServerCodegen", date = "2018-02-03T11:07:24.149Z")
public abstract class IndexApiService {
    public abstract Response deleteIndex(String indexName,SecurityContext securityContext) throws NotFoundException;
    public abstract Response getIndexes(SecurityContext securityContext) throws NotFoundException;
    public abstract Response importIndex(RemoteIndex index,SecurityContext securityContext) throws NotFoundException;
    public abstract Response retrieve(String indexName, @NotNull String queryString, String queryId, String matchingModel, List<String> queryControlNames, List<String> queryControlValues,SecurityContext securityContext) throws NotFoundException;
    public abstract Response stats(String indexName,SecurityContext securityContext) throws NotFoundException;
}
