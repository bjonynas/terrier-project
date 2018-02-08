package org.terrier.remote.factories;

import org.terrier.remote.api.DocumentsApiService;
import org.terrier.remote.impl.DocumentsApiServiceImpl;

@javax.annotation.Generated(value = "class io.swagger.codegen.languages.JavaJerseyServerCodegen", date = "2017-11-22T14:19:28.322Z")
public class DocumentsApiServiceFactory {
    private final static DocumentsApiService service = new DocumentsApiServiceImpl();

    public static DocumentsApiService getDocumentsApi() {
        return service;
    }
}
