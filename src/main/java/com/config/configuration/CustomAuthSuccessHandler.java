package com.config.configuration;

import java.io.IOException;
import java.util.Set;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
@Service
// annotation because of anyone can create object easily
public class CustomAuthSuccessHandler implements AuthenticationSuccessHandler{

	@Override
	public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
			Authentication authentication) throws IOException, ServletException {
		
		//Set<String> roles = AuthorityUtils.authorityListToSet(authentication.getAuthorities());
		
		var authourities = authentication.getAuthorities();
		var roles = authourities.stream().map(r -> r.getAuthority()).findFirst();
		
		if (roles.orElse("").equals("ROLE_ADMIN")) {
			response.sendRedirect("/admin");
		}
		else if(roles.orElse("").equals("ROLE_USER")) {
			response.sendRedirect("/user/userProfile");
		}else {
			response.sendRedirect("/error");
		}
	
	}
}