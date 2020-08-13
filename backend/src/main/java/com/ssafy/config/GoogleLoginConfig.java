package com.ssafy.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.social.google.connect.GoogleConnectionFactory;
import org.springframework.social.oauth2.OAuth2Parameters;

@Configuration
public class GoogleLoginConfig {
	public static final String GOOGLE_CLIENT_ID = "476248660063-e2gk89ukcim2la7mbttisi10pq9ck5r6.apps.googleusercontent.com";
	public static final String GOOGLE_SECRIT_ID = "FAmYp5PiisGr6g0kNVLtoELC";

	@Bean
	GoogleConnectionFactory googleConnectionFactory() {
		return new GoogleConnectionFactory(GOOGLE_CLIENT_ID, GOOGLE_SECRIT_ID);
	}

	@Bean
	OAuth2Parameters oAuth2Parameters() {
		OAuth2Parameters oAuth2Parameters = new OAuth2Parameters();
		oAuth2Parameters.setRedirectUri("http://i3b107.p.ssafy.io:8080/api/public/google/redirect");
		oAuth2Parameters.setScope(
				"https://www.googleapis.com/auth/userinfo.profile https://www.googleapis.com/auth/userinfo.email openid");
		return oAuth2Parameters;
	}

}
