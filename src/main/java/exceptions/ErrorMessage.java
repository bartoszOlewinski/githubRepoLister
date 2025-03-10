package exceptions;

import lombok.AllArgsConstructor;
import lombok.ToString;

import java.io.Serializable;

@ToString
@AllArgsConstructor
public class ErrorMessage implements Serializable {

    private Integer status;
    private String message;

}