package org.terrier.remote.factories;

import org.terrier.remote.api.IndexApiService;
import org.terrier.remote.impl.IndexApiServiceImpl;

@javax.annotation.Generated(value = "class io.swagger.codegen.languages.JavaJerseyServerCodegen", date = "2017-11-22T14:19:28.322Z")
public class IndexApiServiceFactory {
    private final static IndexApiService service = new IndexApiServiceImpl();

    public static IndexApiService getIndexApi() {
        return service;
    }
}
