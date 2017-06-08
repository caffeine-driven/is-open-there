package kr.ac.jejunu.service;

import kr.ac.jejunu.model.User;
import kr.ac.jejunu.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

/**
 * Created by ghost9087 on 08/06/2017.
 */
@Service
public class UserServiceImpl implements UserService{
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public User addUser(User user) {
        return saveUser(user);
    }

    @Override
    public User updateUser(User user) {
        User originalUser = getUserById(user.getId());

        user.setName(originalUser.getName());

        return saveUser(user);
    }

    @Override
    public User getUserByUsername(String username) {
        return userRepository.findByName(username);
    }

    @Override
    public User getUserById(Integer id) {
        return userRepository.findOne(id);
    }

    @Override
    public void deleteUser(Integer id) {
        userRepository.delete(id);
    }

    private User saveUser(User user) {
        String encodedPassword = passwordEncoder.encode(user.getPassword());
        user.setPassword(encodedPassword);

        return userRepository.save(user);
    }
}
