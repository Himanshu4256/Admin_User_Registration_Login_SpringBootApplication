 package com.config.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.servlet.util.matcher.MvcRequestMatcher;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.handler.HandlerMappingIntrospector;


@Configuration
@EnableWebSecurity
public class SpringSecurityEngine implements WebMvcConfigurer{
	
	@Autowired
	public CustomAuthSuccessHandler customAuthSuccessHandler;
	
	@Autowired
	CustomUserDetailsService customUserDetailsService;
	
	@Bean
	public static BCryptPasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}  

	
	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

			http.csrf(c -> c.disable())
			.authorizeHttpRequests(request -> request.requestMatchers("/admin")
					.hasAuthority("ADMIN").requestMatchers("/user").hasAuthority("USER")
					.requestMatchers("/registration").permitAll().requestMatchers("/").permitAll()					
					.anyRequest().authenticated())
			
			.formLogin(form -> form.loginPage("/login").loginProcessingUrl("/login")
					.successHandler(customAuthSuccessHandler).permitAll())
			
			.logout(form -> form.invalidateHttpSession(true).clearAuthentication(true)
					.logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
					.logoutSuccessUrl("/login?logout").permitAll());
			
			return http.build();
	}
	
	@Autowired
	public void configure (AuthenticationManagerBuilder auth) throws Exception{
		auth.userDetailsService(customUserDetailsService).passwordEncoder(passwordEncoder());
	}
}