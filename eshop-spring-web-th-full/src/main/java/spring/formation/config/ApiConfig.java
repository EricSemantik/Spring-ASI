package spring.formation.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@Configuration
@EnableWebMvc // Activation des annotations @Controller, @RequestMapping, ...
@ComponentScan("spring.formation.rest") // Scanner le package où se situe les Controller
public class ApiConfig {

}
