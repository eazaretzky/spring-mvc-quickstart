package ar.com.orangebit.springMvcQuickstart.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@Configuration
@EnableWebMvc
@ComponentScan(basePackages="ar.com.orangebit.springMvcQuickstart")
public class WebConfiguration {

}
