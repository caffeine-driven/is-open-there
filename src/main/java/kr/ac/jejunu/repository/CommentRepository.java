package kr.ac.jejunu.repository;

import kr.ac.jejunu.model.Comment;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

/**
 * Created by ghost9087 on 08/06/2017.
 */
public interface CommentRepository  extends CrudRepository<Comment, Integer> {
    List<Comment> findCommentsByRestaurantId(Integer restaurantId);
}
