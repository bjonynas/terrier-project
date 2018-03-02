package org.terrier.remote.impl;

import org.terrier.remote.api.ApiResponseMessage;
import org.terrier.remote.api.StatusApiService;
import org.terrier.remote.api.NotFoundException;
import org.terrier.remote.model.ServerStatus;

import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.SecurityContext;

@javax.annotation.Generated(value = "io.swagger.codegen.languages.JavaJerseyServerCodegen", date = "2018-02-23T03:14:17.958Z")
public class StatusApiServiceImpl extends StatusApiService {
    @Override
    public Response status(SecurityContext securityContext) throws NotFoundException {

        ServerStatus status = new ServerStatus();
        status.setAvailableMemory(Long.toString(Runtime.getRuntime().totalMemory()/1000000));
        status.setUsedMemory(Long.toString(Runtime.getRuntime().freeMemory()/1000000));
        status.setNumberOfIndexes(Integer.toString(ImportedIndexes.getIndexes().size()));

        return Response.ok(status, MediaType.APPLICATION_JSON).build();
    }
}
