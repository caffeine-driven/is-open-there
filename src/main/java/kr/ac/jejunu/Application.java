package kr.ac.jejunu;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.thymeleaf.spring4.view.ThymeleafViewResolver;

/**
 * Created by ghost9087 on 06/06/2017.
 */
@SpringBootApplication
public class Application extends WebMvcConfigurerAdapter {
    @Autowired
    private ThymeleafViewResolver thymeleafViewResolver;

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
