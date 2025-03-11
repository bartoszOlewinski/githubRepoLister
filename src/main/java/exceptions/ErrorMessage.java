package exceptions;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class ErrorMessage {

    private String message;
    private Boolean status;

    public ErrorMessage(String message, Boolean status) {
        super();
        this.message = message;
        this.status = status;
    }

    public ErrorMessage() {
        super();
    }

}