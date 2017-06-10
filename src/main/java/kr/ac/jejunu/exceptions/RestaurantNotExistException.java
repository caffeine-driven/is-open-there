package kr.ac.jejunu.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Created by ghost9087 on 09/06/2017.
 */
@ResponseStatus(value = HttpStatus.NOT_FOUND, reason = "restaurant not exist")
public class RestaurantNotExistException extends RuntimeException {
    public RestaurantNotExistException() {
        super("restaurant not exist");
    }
}
