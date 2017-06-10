package kr.ac.jejunu.controller;

import kr.ac.jejunu.model.User;
import kr.ac.jejunu.service.AuthService;
import kr.ac.jejunu.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * Created by ghost9087 on 07/06/2017.
 */
@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService userService;

    @Autowired
    private AuthService authService;

    @GetMapping(value = {"/me"})
    public User selfInfo(){
        return authService.getAuthenticatedUser();
    }
    @GetMapping("/{id}")
    public User userInfo(@PathVariable Integer id){
        return userService.getUserById(id);
    }

    //FIXME: 로그인된 사용자만 변경되도록 변경
    @PutMapping("/{id}")
    public User updateUser(@PathVariable Integer id, @RequestBody User user){
        user.setId(id);

        return userService.updateUser(user);
    }

    //FIXME: 사용자 ID가 중복일때 처리!
    @PostMapping
    public User addUser(@RequestBody User user){
        return userService.addUser(user);
    }
}
