package exceptions;

import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

@Provider
public class CustomExceptionHandler implements ExceptionMapper<CustomException> {


    @Override
    public Response toResponse(CustomException exception) {

        return Response.status(Response.Status.NOT_FOUND).
                entity(exception.getMessage()).build();

    }
}