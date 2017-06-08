package kr.ac.jejunu.controller;

import kr.ac.jejunu.model.Comment;
import kr.ac.jejunu.model.User;
import kr.ac.jejunu.service.CommentService;
import kr.ac.jejunu.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by ghost9087 on 08/06/2017.
 */
@Controller
@RequestMapping("/comment")
public class CommentController {
    @Autowired
    private CommentService commentService;

    @Autowired
    private UserService userService;

    @GetMapping("/{id}")
    public void getCommentOfRestaurant(@PathVariable Integer restaurantId, Model model){
        List<Comment> comments = commentService.getCommentOfRestaurant(restaurantId);

        model.addAttribute("comments", comments);
        model.addAttribute("restaurant_id", restaurantId);
    }

    @PostMapping("/{id}")
    public void addComment(@PathVariable Integer restaurantId, @ModelAttribute Comment comment, Model model){
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();

        User user = userService.getUserByUsername(auth.getName());

        commentService.addCommentForRestaurant(comment, user, restaurantId);

        model.addAttribute("result", true);
    }

    @DeleteMapping("/{id}")
    public void deleteComment(@PathVariable Integer commentId, Model model){
        commentService.deleteComment(commentId);

        model.addAttribute("deleted", true);
    }
}
