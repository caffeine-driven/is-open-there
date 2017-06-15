package kr.ac.jejunu.service;

import kr.ac.jejunu.exceptions.ObjectDuplicatedException;
import kr.ac.jejunu.model.User;
import kr.ac.jejunu.repository.UserRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

/**
 * Created by ghost9087 on 08/06/2017.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration
public class UserServiceImplTest {
    @Configuration
    static class UserServiceTestContextConfiguration {
        @Bean
        public UserService userService() {
            return new UserServiceImpl();
        }

        @Bean
        public UserRepository userRepository(){
            return mock(UserRepository.class);
        }

        @Bean
        public PasswordEncoder passwordEncoder(){
            return mock(PasswordEncoder.class);
        }
    }

    @Autowired
    private UserService sut;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    private String fakePassword;

    private User user;

    @Before
    public void setUp() throws Exception {
        fakePassword = "0912";
        user = new User();

        reset(userRepository, passwordEncoder);
        when(passwordEncoder.encode(any(CharSequence.class))).thenReturn(fakePassword);
        when(userRepository.save(user)).thenReturn(user);
    }

    @Test
    public void testCreateUser() throws Exception {
        when(userRepository.findByName(anyString())).thenReturn(null);
        String passwordInPlainText = "1234";

        user.setName("test");
        user.setPassword(passwordInPlainText);

        User savedUser = sut.addUser(user);

        assertThat(savedUser.getPassword(), is(fakePassword));
        verify(userRepository, times(1)).save(user);
        verify(passwordEncoder, times(1)).encode(passwordInPlainText);
    }

    @Test(expected = ObjectDuplicatedException.class)
    public void testCreateUserWithDuplicatedUserName() throws Exception {
        User user = new User();
        user.setName("test");
        user.setPassword("1234");
        when(userRepository.findByName(anyString())).thenReturn(mock(User.class));

        sut.addUser(user);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testCreateWithInvalidUsername() throws Exception {
        User user = new User();
        user.setPassword("1234");

        sut.addUser(user);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testCreateWithInvalidPassword() throws Exception {
        User user = new User();
        user.setName("test");

        sut.addUser(user);
    }

    @Test
    public void testUpdateUser() throws Exception{
        Integer id = 1;
        user.setId(id);
        user.setName("test");

        when(userRepository.findOne(anyInt())).thenReturn(user);

        User userForUpdate = new User();

        String passwordForChange = "test-password";
        userForUpdate.setId(id);
        userForUpdate.setName("blahblahblah");
        userForUpdate.setPassword(passwordForChange);

        sut.updateUser(userForUpdate);

        assertThat(userForUpdate.getId(), is(user.getId()));
        assertThat(userForUpdate.getName(), is(user.getName()));
        verify(userRepository, times(1)).findOne(id);
        verify(userRepository, times(1)).save(userForUpdate);
        verify(passwordEncoder, times(1)).encode(passwordForChange);
    }
}
