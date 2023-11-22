package asc.com.agenda.domain.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.UNPROCESSABLE_ENTITY)
public class ComumException extends RuntimeException{
    public ComumException(String message){
        super(message);
    }
}
