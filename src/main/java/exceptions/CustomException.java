package exceptions;

import java.io.Serial;
import java.io.Serializable;

public class CustomException extends
        Exception implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;


    public CustomException() {
    }

    public CustomException(String message) {
        super(message);
    }

    public CustomException(String message, Exception e) {
        super(message, e);
    }
}