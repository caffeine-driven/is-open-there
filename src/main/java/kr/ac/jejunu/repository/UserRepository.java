package kr.ac.jejunu.repository;

import kr.ac.jejunu.model.User;
import org.springframework.data.repository.CrudRepository;

/**
 * Created by ghost9087 on 06/06/2017.
 */
public interface UserRepository extends CrudRepository<User, Integer> {
    User findByName(String name);
}
