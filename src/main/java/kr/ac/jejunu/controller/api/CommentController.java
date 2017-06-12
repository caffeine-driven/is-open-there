package kr.ac.jejunu.controller.api;

import kr.ac.jejunu.exceptions.UnAuthorizedException;
import kr.ac.jejunu.model.Comment;
import kr.ac.jejunu.model.User;
import kr.ac.jejunu.service.AuthService;
import kr.ac.jejunu.service.CommentService;
import kr.ac.jejunu.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by ghost9087 on 08/06/2017.
 */
@RestController
@RequestMapping("/api//comment")
public class CommentController {
    @Autowired
    private CommentService commentService;

    @Autowired
    private UserService userService;

    @Autowired
    private AuthService authService;

    @GetMapping("/{restaurantId}")
    public List<Comment> getCommentOfRestaurant(@PathVariable Integer restaurantId){
        return commentService.getCommentOfRestaurant(restaurantId);
    }

    @PostMapping("/{restaurantId}")
    public Map<String, Boolean> addComment(@PathVariable Integer restaurantId, @RequestBody Comment comment) throws UnAuthorizedException {
        User user = authService.getAuthenticatedUser();

        if (user == null)
            throw new UnAuthorizedException();

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
