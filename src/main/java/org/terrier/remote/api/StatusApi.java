package org.terrier.remote.api;

import org.terrier.remote.factories.StatusApiServiceFactory;
import org.terrier.remote.model.ServerStatus;
import javax.servlet.ServletConfig;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.SecurityContext;
import javax.ws.rs.*;

@Path("/status")


@io.swagger.annotations.Api(description = "the status API")
@javax.annotation.Generated(value = "io.swagger.codegen.languages.JavaJerseyServerCodegen", date = "2018-02-23T03:14:17.958Z")
public class StatusApi  {
   private final StatusApiService delegate;

   public StatusApi(@Context ServletConfig servletContext) {
      StatusApiService delegate = null;

      if (servletContext != null) {
         String implClass = servletContext.getInitParameter("StatusApi.implementation");
         if (implClass != null && !"".equals(implClass.trim())) {
            try {
               delegate = (StatusApiService) Class.forName(implClass).newInstance();
            } catch (Exception e) {
               throw new RuntimeException(e);
            }
         } 
      }

      if (delegate == null) {
         delegate = StatusApiServiceFactory.getStatusApi();
      }

      this.delegate = delegate;
   }

    @GET
    
    
    @Produces({ "application/json" })
    @io.swagger.annotations.ApiOperation(value = "get current server status", notes = "Get the current server status, e.g. memory usage\" ", response = ServerStatus.class, tags={  })
    @io.swagger.annotations.ApiResponses(value = { 
        @io.swagger.annotations.ApiResponse(code = 200, message = "received server status", response = ServerStatus.class) })
    public Response status(@Context SecurityContext securityContext)
    throws NotFoundException {
        return delegate.status(securityContext);
    }
}
