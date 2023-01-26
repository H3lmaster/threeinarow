package threeinarow;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@SpringBootApplication
public class ThreeInARow implements WebMvcConfigurer {
	
	public static void main(String[] args) {
        SpringApplication.run(ThreeInARow.class, args);
	}
	

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        String[] origins = {"http://localhost:3000"};
        registry.addMapping("/**").allowedOrigins(origins).allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS")
                .allowCredentials(true).allowedHeaders("Access-Control-Allow-Origin", "Content-Type", "Access-Control-Allow-Headers", "Access-Control-Expose-Headers", "Content-Disposition", "Authorization", "X-Requested-With")
                .exposedHeaders("Access-Control-Allow-Origin", "Content-Type", "Access-Control-Allow-Headers", "Access-Control-Expose-Headers", "Content-Disposition", "Authorization", "X-Requested-With");
    }

}
