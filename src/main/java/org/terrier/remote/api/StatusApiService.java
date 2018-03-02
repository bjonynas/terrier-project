package org.terrier.remote.api;

import javax.ws.rs.core.Response;
import javax.ws.rs.core.SecurityContext;

@javax.annotation.Generated(value = "io.swagger.codegen.languages.JavaJerseyServerCodegen", date = "2018-02-23T03:14:17.958Z")
public abstract class StatusApiService {
    public abstract Response status(SecurityContext securityContext) throws NotFoundException;
}
