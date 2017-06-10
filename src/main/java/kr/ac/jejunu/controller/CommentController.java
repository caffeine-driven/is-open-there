package kr.ac.jejunu.controller;

import kr.ac.jejunu.model.Comment;
import kr.ac.jejunu.model.User;
import kr.ac.jejunu.service.CommentService;
import kr.ac.jejunu.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by ghost9087 on 08/06/2017.
 */
@RestController
@RequestMapping("/comment")
public class CommentController {
    @Autowired
    private CommentService commentService;

    @Autowired
    private UserService userService;

    @GetMapping("/{restaurantId}")
    public List<Comment> getCommentOfRestaurant(@PathVariable Integer restaurantId){
        return commentService.getCommentOfRestaurant(restaurantId);
    }

    @PostMapping("/{restaurantId}")
    public Map<String, Boolean> addComment(@PathVariable Integer restaurantId, @RequestBody Comment comment) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();

        User user = userService.getUserByUsername(auth.getName());

        commentService.addCommentForRestaurant(comment, user, restaurantId);

        Map<String, Boolean> resultMap = new HashMap<>();
        resultMap.put("result", true);

        return resultMap;
    }

    @DeleteMapping("/{id}")
    public Map<String, Boolean> deleteComment(@PathVariable Integer commentId){
        commentService.deleteComment(commentId);

        Map<String, Boolean> resultMap = new HashMap<>();
        resultMap.put("result", true);

        return resultMap;
    }
}
