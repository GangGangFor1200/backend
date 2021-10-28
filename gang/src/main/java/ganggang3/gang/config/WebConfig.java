package ganggang3.gang.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                .allowedOrigins("http://uspot.cf.s3-website.ap-northeast-2.amazonaws.com")
                .allowedOrigins("http://uspot.cf")
                .allowedOrigins("https://uspot.cf")
                .allowCredentials(true);
    }
}
