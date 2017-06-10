package kr.ac.jejunu.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import javax.naming.AuthenticationException;

/**
 * Created by ghost9087 on 10/06/2017.
 */
@ResponseStatus(value = HttpStatus.UNAUTHORIZED, reason = "unauthorized")
public class UnAuthorizedException extends AuthenticationException {
}
