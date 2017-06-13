package kr.ac.jejunu.controller.web;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by ghost9087 on 12/06/2017.
 */
@Controller
@RequestMapping("/auth")
public class UserWebController {
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
}
