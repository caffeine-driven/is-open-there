package kr.ac.jejunu.controller;

import kr.ac.jejunu.model.User;
import kr.ac.jejunu.service.AuthService;
import kr.ac.jejunu.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
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

    @RequestMapping(value = {"/me"}, method = {RequestMethod.POST, RequestMethod.GET})
    public User selfInfo(){
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        if (principal instanceof org.springframework.security.core.userdetails.User){
            org.springframework.security.core.userdetails.User userDetail = (org.springframework.security.core.userdetails.User)principal;
            User user = userService.getUserByUsername(userDetail.getUsername());
//            model.addAttribute("user", user);
            return user;
        }
        else {
//            model.addAttribute("user", "Anonymous");
            return null;
        }
    }
    @GetMapping("/{id}")
    public User userInfo(@PathVariable Integer id){
        return userService.getUserById(id);
    }

    @PutMapping("/{id}")
    public User updateUser(@PathVariable Integer id, @RequestBody User user){
        user.setId(id);

        return userService.updateUser(user);
    }

    @PostMapping
    public User addUser(@RequestBody User user){
        User savedUser = userService.addUser(user);

        authService.authByUser(user);

        return savedUser;
    }
}
