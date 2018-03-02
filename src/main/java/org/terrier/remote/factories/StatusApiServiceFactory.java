package org.terrier.remote.factories;

import org.terrier.remote.api.StatusApiService;
import org.terrier.remote.impl.StatusApiServiceImpl;

@javax.annotation.Generated(value = "io.swagger.codegen.languages.JavaJerseyServerCodegen", date = "2018-02-23T03:14:17.958Z")
public class StatusApiServiceFactory {
    private final static StatusApiService service = new StatusApiServiceImpl();

    public static StatusApiService getStatusApi() {
        return service;
    }
}
