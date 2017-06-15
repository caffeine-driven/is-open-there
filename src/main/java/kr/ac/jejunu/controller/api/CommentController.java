package kr.ac.jejunu.controller.api;

import kr.ac.jejunu.exceptions.UnAuthorizedException;
import kr.ac.jejunu.model.ActionResult;
import kr.ac.jejunu.model.Comment;
import kr.ac.jejunu.model.User;
import kr.ac.jejunu.service.AuthService;
import kr.ac.jejunu.service.CommentService;
import kr.ac.jejunu.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by ghost9087 on 08/06/2017.
 */
@RestController
@RequestMapping("/api/comment")
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
    public ActionResult addComment(@PathVariable Integer restaurantId, @RequestBody Comment comment) throws UnAuthorizedException {
        User user = authService.getAuthenticatedUser();

        if (user == null)
            throw new UnAuthorizedException();

        commentService.addCommentForRestaurant(comment, user, restaurantId);

        return new ActionResult(true);
    }

    @DeleteMapping("/{id}")
    public ActionResult deleteComment(@PathVariable Integer commentId) {
        commentService.deleteComment(commentId);

        return new ActionResult(true);
    }
}
