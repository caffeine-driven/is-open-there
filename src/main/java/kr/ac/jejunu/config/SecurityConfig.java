package kr.ac.jejunu.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

/**
 * Created by ghost9087 on 07/06/2017.
 */
@EnableGlobalMethodSecurity(securedEnabled = true)
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter{
    @Autowired
    private UserDetailsService userDetailsService;

    @Bean
    public PasswordEncoder passwordEncoder() {
        return NoOpPasswordEncoder.getInstance();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .authorizeRequests()
                    .antMatchers("/css/**", "/index").permitAll()
                .mvcMatchers(HttpMethod.OPTIONS, "/**").permitAll()
                    .mvcMatchers(HttpMethod.GET, "/**").permitAll()
                    .mvcMatchers(HttpMethod.POST, "/user").permitAll()
                .mvcMatchers(HttpMethod.POST, "/api/auth/login**").permitAll()
                .mvcMatchers(HttpMethod.POST, "/auth/sign-up").permitAll()
                    .anyRequest().authenticated()
                .and()
                .formLogin()
                .loginPage("/auth/login").failureUrl("/auth/login-error")
                .and()
                .logout()
                .logoutUrl("/auth/logout")
                .logoutSuccessUrl("/")
                .and()
                .csrf().disable();
    }

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder());
    }
}
