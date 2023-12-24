 package com.config.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.servlet.util.matcher.MvcRequestMatcher;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.handler.HandlerMappingIntrospector;


@Configuration
@EnableWebSecurity
public class SpringSecurityEngine implements WebMvcConfigurer{
	
	@Autowired
	public CustomAuthSuccessHandler authSuccessHandler;
	
	@Bean
	public BCryptPasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
	
	@Bean
	public UserDetailsService getDetailsService() {
		return new CustomUserDetailsService();
	}
	
	@Bean
	public DaoAuthenticationProvider authenticationProvider() {
		DaoAuthenticationProvider daoAuthenticationProvider = new DaoAuthenticationProvider();
		daoAuthenticationProvider.setUserDetailsService(getDetailsService());
		daoAuthenticationProvider.setPasswordEncoder(passwordEncoder());
		return daoAuthenticationProvider;
	}
	
	@Bean
	MvcRequestMatcher.Builder mvc(HandlerMappingIntrospector introspector) {
		return new MvcRequestMatcher.Builder(introspector).servletPath("/spring-mvc");
	}
	
	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

//		http.csrf().disable().authorizeHttpRequests()
//		.requestMatchers("/").permitAll().anyRequest().authenticated();
//		return http.build();	
        
//		http.authorizeHttpRequests ((requests) ->
//		requests.requestMatchers (new AntPathRequestMatcher("/")).permitAll()
//		.anyRequest().authenticated())
//		
//		.csrf(csrf -> csrf.disable())
//		.httpBasic(withDefaults()); 
//		return http.build();
        
        
//			http
//				.authorizeHttpRequests((authorize) -> authorize
//					.requestMatchers("/endpoint").hasAuthority("USER")
//					.anyRequest().authenticated()
//				)
//				.csrf(csrf -> csrf.disable())
//				.httpBasic(withDefaults());
//
//			return http.build();
        
        
//		http.authorizeHttpRequests((requests) ->
//	    requests
//	        .requestMatchers(new AntPathRequestMatcher("/")).permitAll()
//	        .requestMatchers(new AntPathRequestMatcher("/logins")).permitAll()
//	        .requestMatchers(new AntPathRequestMatcher("/userDetails")).permitAll()
//	        .requestMatchers("/adminPortal").hasRole("ADMIN")
//	        .anyRequest().authenticated())
//	    .csrf(csrf -> csrf.disable())
//	    .httpBasic(withDefaults()); 
//	return http.build();
        
        
        
        http.csrf().disable()
        .authorizeHttpRequests().requestMatchers("/user/**").hasRole("USER")
        .requestMatchers("/admin/**").hasRole("ADMIN")
        .requestMatchers("/").permitAll().and()
        .formLogin().loginPage("/login").loginProcessingUrl("/userLogin")
        .successHandler(authSuccessHandler)
        .permitAll();
        return http.build();
	}
}