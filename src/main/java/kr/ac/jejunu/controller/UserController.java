package kr.ac.jejunu.controller;

import kr.ac.jejunu.model.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

/**
 * Created by ghost9087 on 07/06/2017.
 */
@Controller
@RequestMapping("/user")
public class UserController {
    @GetMapping("/{id}")
    public void userInfo(@PathVariable Integer id, Model model){
        System.out.println(id);
    }

    @PostMapping
    public void addUser(@ModelAttribute User user, Model model){
        System.out.println(user);
    }

    @GetMapping("/login")
    public void login(Model model, String error, String logout){
        System.out.println(error);
        System.out.println(logout);
    }
}
