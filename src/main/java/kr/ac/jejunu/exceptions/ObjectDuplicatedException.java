package kr.ac.jejunu.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Created by ghost9087 on 10/06/2017.
 */
@ResponseStatus(value = HttpStatus.BAD_REQUEST, reason = "object duplicated")
public class ObjectDuplicatedException extends RuntimeException {
    public ObjectDuplicatedException(String msg) {
        super(msg);
    }
}
