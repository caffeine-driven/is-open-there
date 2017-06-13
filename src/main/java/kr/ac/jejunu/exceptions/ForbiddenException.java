package kr.ac.jejunu.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Created by ghost9087 on 13/06/2017.
 */
@ResponseStatus(value = HttpStatus.UNAUTHORIZED, reason = "not enough permission to do this action")
public class ForbiddenException extends RuntimeException {
}
