package kr.ac.jejunu.service;

import kr.ac.jejunu.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

/**
 * Created by ghost9087 on 08/06/2017.
 */
@Service
public class AuthServiceImpl implements AuthService {
    @Autowired
    private UserDetailsService userDetailsService;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private UserService userService;

    @Override
    public void authByUser(User user) {
        UserDetails userDetails = userDetailsService.loadUserByUsername(user.getName());
        UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(userDetails, user.getPassword(), userDetails.getAuthorities());

        authenticationManager.authenticate(usernamePasswordAuthenticationToken);

        if (usernamePasswordAuthenticationToken.isAuthenticated()) {
            SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
        }
    }

    @Override
    public User getAuthenticatedUser() {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        if (principal instanceof org.springframework.security.core.userdetails.User){
            org.springframework.security.core.userdetails.User userDetail = (org.springframework.security.core.userdetails.User)principal;
            return userService.getUserByUsername(userDetail.getUsername());
        }
        else {
            return null;
        }
    }

    @Override
    public void deauthorize() {
        SecurityContextHolder.getContext().setAuthentication(null);
    }
}
