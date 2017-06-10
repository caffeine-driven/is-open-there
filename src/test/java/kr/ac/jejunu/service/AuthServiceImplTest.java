package kr.ac.jejunu.service;

import kr.ac.jejunu.model.User;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentMatchers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.CoreMatchers.nullValue;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

/**
 * Created by ghost9087 on 10/06/2017.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration
public class AuthServiceImplTest {
    @Configuration
    static class AuthServiceTestContextConfiguration {
        @Bean
        public AuthService authService() {
            return new AuthServiceImpl();
        }

        @Bean
        public UserDetailsService userDetailsService(){
            return mock(UserDetailsService.class);
        }

        @Bean
        public AuthenticationManager authenticationManager() {
            return mock(AuthenticationManager.class);
        }

        @Bean
        public UserService userService() {
            return mock(UserService.class);
        }
    }

    @Autowired
    private AuthService sut;

    @Autowired
    private UserDetailsService userDetailsService;

    @Autowired
    private AuthenticationManager authenticationManager;

    private User user;

    @Before
    public void setUp() throws Exception {
        user = new User();
        user.setId(1);
        user.setName("test_user");
        user.setPassword("1234");
        reset(userDetailsService, authenticationManager);
    }

    @Test
    public void testValidLogin() throws Exception {
        UserDetails mockUserDetails = mock(UserDetails.class);
        when(userDetailsService.loadUserByUsername(anyString())).thenReturn(mockUserDetails);
        when(authenticationManager.authenticate(ArgumentMatchers.any(Authentication.class))).then(invocation -> {
            Authentication authentication = (Authentication) invocation.getArguments()[0];
            Authentication spy = spy(authentication);
            when(spy.isAuthenticated()).thenReturn(true);
            return null;
        });

        sut.authByUser(user);

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        assertThat(authentication, notNullValue());
    }

    @Test
    public void testLogout() throws Exception {
        sut.deauthorize();

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        assertThat(authentication, nullValue());
    }
}
