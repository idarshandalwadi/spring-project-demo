package com.darshan.config;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

import com.darshan.util.ApplicationConstants;

@SpringBootApplication(scanBasePackages = ApplicationConstants.BASE_PACKAGE)
@EnableJpaRepositories(basePackages = ApplicationConstants.JPA_PACKAGE)
@EntityScan(basePackages = ApplicationConstants.ENTITY_PACKAGE)
public class CrudApplication extends SpringBootServletInitializer {

	public static void main(String[] args) {
		SpringApplication.run(CrudApplication.class, args);
	}

	/**
	 * This <code>SpringApplicationBuilder</code> method configures Spring for current
	 * application.
	 * 
	 * @param builder
	 * @Return
	 */
	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
		return builder.sources(CrudApplication.class);
	}

	/**
	 * This <code>getResolver</code> method configures view resolver for the current
	 * application.
	 * 
	 * @Return
	 */
	@Bean
	public ViewResolver getResolver() {
		final InternalResourceViewResolver resolver = new InternalResourceViewResolver();
		resolver.setPrefix("/WEB-INF/jsp/");
		resolver.setSuffix(".jsp");
		return resolver;
	}
}
