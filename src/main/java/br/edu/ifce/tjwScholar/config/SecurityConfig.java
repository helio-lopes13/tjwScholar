package br.edu.ifce.tjwScholar.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import br.edu.ifce.tjwScholar.security.ScholarUserDetailsService;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
		http.authorizeHttpRequests(
				requests -> requests
					.requestMatchers("/layout/**", "/images/**")
					.permitAll()
					.anyRequest()
					.authenticated()
		).formLogin(
				form -> form
					.loginPage("/login")
					.permitAll()
		).logout(
				logout -> logout
					.logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
					.permitAll()
		).exceptionHandling(exception -> exception
				.accessDeniedPage("/403")
		);
		
		return http.build();
	}
	
	@Bean
	public ScholarUserDetailsService customUserDetailsService() {
		return new ScholarUserDetailsService();
	}
	
	@Bean
	public BCryptPasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
}
