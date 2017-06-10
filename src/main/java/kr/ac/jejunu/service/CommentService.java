package kr.ac.jejunu.service;

import kr.ac.jejunu.model.Comment;
import kr.ac.jejunu.model.User;

import java.util.List;

/**
 * Created by ghost9087 on 08/06/2017.
 */
public interface CommentService {
    void addCommentForRestaurant(Comment comment, User user, Integer restaurantId);
    List<Comment> getCommentOfRestaurant(Integer restaurantId);
    void deleteComment(Integer commentId);
}
