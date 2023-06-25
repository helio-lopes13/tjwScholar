package br.edu.ifce.tjwScholar.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import br.edu.ifce.tjwScholar.thymeleaf.ScholarDialect;

@Configuration
public class WebConfig {
	
	@Bean
	public ScholarDialect scholarDialect() {
		return new ScholarDialect();
	}
}
