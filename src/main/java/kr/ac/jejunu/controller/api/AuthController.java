package kr.ac.jejunu.controller.api;

import kr.ac.jejunu.model.ActionResult;
import kr.ac.jejunu.model.User;
import kr.ac.jejunu.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.*;

/**
 * Created by ghost9087 on 09/06/2017.
 */
@RestController
@RequestMapping("/api/auth")
public class AuthController {
    @Autowired
    private AuthService authService;

    @PostMapping("/login")
    public ActionResult login(@RequestBody User user) {
        authService.authByUser(user);

        return new ActionResult(true);
    }

    @PostMapping("logout")
    public ActionResult logout() {
        authService.deauthorize();

        return new ActionResult(true);
    }

    @ExceptionHandler(AuthenticationException.class)
    @ResponseStatus(code = HttpStatus.UNAUTHORIZED)
    public String authenticationErrorHandle(){
        return "authentication error";
    }
}
