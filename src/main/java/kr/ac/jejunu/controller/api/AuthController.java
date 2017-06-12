package kr.ac.jejunu.controller.api;

import kr.ac.jejunu.model.User;
import kr.ac.jejunu.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by ghost9087 on 09/06/2017.
 */
@RestController
@RequestMapping("/api/auth")
public class AuthController {
    @Autowired
    private AuthService authService;

    @PostMapping("/login")
    public Map<String, Boolean> login(@RequestBody User user){
        authService.authByUser(user);
        System.out.println(authService);

        Map<String, Boolean> resultMap = new HashMap<>();
        resultMap.put("result", true);

        return resultMap;
    }

    @PostMapping("logout")
    public Map<String, Boolean> logout(){
        authService.deauthorize();

        Map<String, Boolean> resultMap = new HashMap<>();
        resultMap.put("result", true);

        return resultMap;
    }

    @ExceptionHandler(AuthenticationException.class)
    @ResponseStatus(code = HttpStatus.UNAUTHORIZED)
    public String authenticationErrorHandle(){
        return "authentication error";
    }
}
