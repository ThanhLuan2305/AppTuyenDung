package com.doan.AppTuyenDung.Config;


import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.doan.AppTuyenDung.Services.JWTUtils;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

// @Component
// public class JWTAuthFilter extends OncePerRequestFilter {
// 	// @Autowired 
// 	// private JWTUtils jwtUtils;
	
// 	// @Autowired
// 	// private org.springframework.security.core.userdetails.UserDetailsService UserDetailsService;

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		
// 		final String autheHeader = request.getHeader("Authorization"); 
// 		final String jwtToken;
// 		final String userName;
		
// 		if(autheHeader == null || autheHeader.isBlank()) {
// 			filterChain.doFilter(request, response);
// 			return;
// 		}
		
// 		jwtToken = autheHeader.substring(7);
// 		userName = jwtUtils.extractUserName(jwtToken);
		
// 		if(userName != null && SecurityContextHolder.getContext().getAuthentication()==null) {
// 			UserDetails userDetails = UserDetailsService.loadUserByUsername(userName);
			
// 			if(jwtUtils.isTokenValid(jwtToken, userDetails)) {
// 				SecurityContext securityContext = SecurityContextHolder.createEmptyContext();
// 				UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
// 				token.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
// 				securityContext.setAuthentication(token);
// 				SecurityContextHolder.setContext(securityContext);
// 			}
// 		}
// 		filterChain.doFilter(request, response);
// 	}
	
// }
