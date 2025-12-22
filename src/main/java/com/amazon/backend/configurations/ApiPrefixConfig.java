package com.amazon.backend.configurations;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.config.annotation.PathMatchConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;




/**
 * Adds a prefix "api's" to all REST controller routes.
 * This makes it easier to group or version APIs.
 * 
 * Example: /users becomes /api's/users.
 */
@Configuration
public class ApiPrefixConfig implements WebMvcConfigurer {
	
	/**
     * Configures path matching to add the "api's" prefix
     * to all @RestController end points.
     */
	
	@Override
	public void configurePathMatch(PathMatchConfigurer configurer) {
		configurer.addPathPrefix("api",c->c.isAnnotationPresent(RestController.class));
	}

}
