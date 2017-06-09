package kr.ac.jejunu.service;

import kr.ac.jejunu.exceptions.RestaurantNotExistException;
import kr.ac.jejunu.model.Comment;
import kr.ac.jejunu.model.Restaurant;
import kr.ac.jejunu.model.User;
import kr.ac.jejunu.repository.CommentRepository;
import kr.ac.jejunu.repository.RestaurantRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.InvalidDataAccessResourceUsageException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ghost9087 on 08/06/2017.
 */
@Service
public class CommentServiceImpl implements CommentService {
    @Autowired
    private CommentRepository commentRepository;

    @Autowired
    private RestaurantRepository restaurantRepository;

    @Override
    public void addCommentForRestaurant(Comment comment, User user, Integer restaurantId) throws RestaurantNotExistException {
        Restaurant restaurant = restaurantRepository.findOne(restaurantId);

        if(restaurant == null || user == null)
            throw new RestaurantNotExistException();

        comment.setWriter(user);
        comment.setRestaurant(restaurant);

        commentRepository.save(comment);
    }

    @Override
    public List<Comment> getCommentOfRestaurant(Integer restaurantId) {
        List<Comment> comments = null;
        try {
             comments = commentRepository.findCommentsByRestaurantId(restaurantId);
        }
        catch (InvalidDataAccessResourceUsageException e){
            comments = new ArrayList<>();
        }

        return comments;
    }

    @Override
    public void deleteComment(Integer commentId) {
        commentRepository.delete(commentId);
    }
}
