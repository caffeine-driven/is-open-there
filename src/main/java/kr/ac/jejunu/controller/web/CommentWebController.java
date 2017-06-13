package kr.ac.jejunu.controller.web;

import kr.ac.jejunu.exceptions.UnAuthorizedException;
import kr.ac.jejunu.model.Comment;
import kr.ac.jejunu.model.User;
import kr.ac.jejunu.service.AuthService;
import kr.ac.jejunu.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by ghost9087 on 08/06/2017.
 */
@Controller
@RequestMapping("/comment")
public class CommentWebController {
    @Autowired
    private CommentService commentService;

    @Autowired
    private AuthService authService;

    @PostMapping("/{restaurantId}")
    public String addComment(@PathVariable Integer restaurantId, @ModelAttribute Comment comment) throws UnAuthorizedException {
        User user = authService.getAuthenticatedUser();

        if (user == null)
            throw new UnAuthorizedException();

        commentService.addCommentForRestaurant(comment, user, restaurantId);

        return "redirect:/restaurant/detail/" + restaurantId;
    }

    @PostMapping("/{restaurantId}/{commentId}")
    public String deleteComment(@PathVariable Integer restaurantId, @PathVariable Integer commentId) {
        commentService.deleteComment(commentId);

        return "redirect:/restaurant/detail/" + restaurantId;
    }
}
