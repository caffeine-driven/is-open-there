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
@Controller
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService userService;

    @Autowired
    private AuthService authService;

    @GetMapping(value = {"/me", ""})
    public void selfInfo(Model model){
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        if (principal instanceof org.springframework.security.core.userdetails.User){
            org.springframework.security.core.userdetails.User userDetail = (org.springframework.security.core.userdetails.User)principal;
            User user = userService.getUserByUsername(userDetail.getUsername());
            model.addAttribute("user", user);
        }
        else {
            model.addAttribute("user", "Anonymous");
        }
    }
    @GetMapping("/{id}")
    public void userInfo(@PathVariable Integer id, Model model){
        User user = userService.getUserById(id);

        model.addAttribute("user", user);
    }
    @PutMapping("/{id}")
    public void updateUser(@PathVariable Integer id, @ModelAttribute User user, Model model){
        user.setId(id);
        User savedUser = userService.updateUser(user);

        model.addAttribute("user", savedUser);

    }

    @PostMapping
    public void addUser(@ModelAttribute User user, Model model){
        User savedUser = userService.addUser(user);

        authService.authByUser(user);

        model.addAttribute("user", savedUser);
    }
}
