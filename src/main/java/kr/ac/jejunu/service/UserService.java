package kr.ac.jejunu.service;

import kr.ac.jejunu.model.User;

/**
 * Created by ghost9087 on 08/06/2017.
 */
public interface UserService {
    User addUser(User user);
    User updateUser(User user);
    User getUserByUsername(String username);
    User getUserById(Integer id);
    void deleteUser(Integer id);

}
