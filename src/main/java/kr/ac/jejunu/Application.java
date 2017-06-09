package kr.ac.jejunu;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

/**
 * Created by ghost9087 on 06/06/2017.
 */
@SpringBootApplication
public class Application extends WebMvcConfigurerAdapter {
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                .allowedOrigins("http://localhost:3000")
                .allowedMethods("GET", "POST", "DELETE", "PUT", "OPTIONS")
                .allowCredentials(true);
    }

    public static void main(String[] args){
        SpringApplication.run(Application.class, args);
    }
}
