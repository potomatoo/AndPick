package com.ssafy.filter;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.ssafy.config.JwtProperties;
import com.ssafy.model.dto.LoginRequest;
import com.ssafy.security.UserPrincipal;

public class JwtAuthenticationFilter extends UsernamePasswordAuthenticationFilter {
	@Autowired
	private AuthenticationManager authenticationManager;

	@Override
	public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response)
			throws AuthenticationException {
		// TODO Auto-generated method stub

		LoginRequest loginreq = new LoginRequest();

		String id = request.getParameter("user_id");
		String password = request.getParameter("user_password");

		if (id == null)
			id = "";
		if (password == null)
			password = "";

		loginreq.setUsername(id);
		loginreq.setPassword(password);

		UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(
				loginreq.getUsername(), loginreq.getPassword(), new ArrayList<>());

		return super.attemptAuthentication(request, response);
	}

	@Override
	protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain,
			Authentication authResult) throws IOException, ServletException {
		// TODO Auto-generated method stub

		UserPrincipal principal = (UserPrincipal) authResult.getPrincipal();

		String token = JWT.create().withSubject(Long.toString(principal.getUserNo()))
				.withExpiresAt(new Date(System.currentTimeMillis() + JwtProperties.EXPIRATION_TIME))
				.sign(Algorithm.HMAC512(JwtProperties.SECRET.getBytes()));

		response.addHeader(JwtProperties.HEADER_STRING, JwtProperties.TOKEN_PREFIX + token);
	}
}
