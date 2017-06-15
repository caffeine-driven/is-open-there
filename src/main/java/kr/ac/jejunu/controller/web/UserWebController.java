package kr.ac.jejunu.controller.web;

import kr.ac.jejunu.exceptions.ObjectDuplicatedException;
import kr.ac.jejunu.model.User;
import kr.ac.jejunu.service.AuthService;
import kr.ac.jejunu.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

/**
 * Created by ghost9087 on 12/06/2017.
 */
@Controller
@RequestMapping("/auth")
public class UserWebController {
    @Autowired
    private UserService userService;

    @Autowired
    private AuthService authService;

    @RequestMapping("/login")
    public String login() {
        return "login";
    }

    @RequestMapping("/login-error")
    public String loginError(Model model) {
        model.addAttribute("loginError", true);
        return "login";
    }

    @GetMapping("/sign-up")
    public String signUp() {
        return "signup";
    }

    @PostMapping("/sign-up")
    public String signUp(@ModelAttribute User user) {
        User savedUser = userService.addUser(user);

        authService.authByUser(savedUser);

        return "redirect:/";
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public String illegalSignUp(Model model, IllegalArgumentException ex) {
        model.addAttribute("error", true);
        model.addAttribute("message", ex.getMessage());
        return "signup";
    }

    @ExceptionHandler(ObjectDuplicatedException.class)
    public String illegalSignUp(Model model, ObjectDuplicatedException ex) {
        model.addAttribute("error", true);
        model.addAttribute("message", ex.getMessage());
        return "signup";
    }
}
