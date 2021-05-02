package com.dzeru.springloginformandoauth2tutorial.config;

import com.dzeru.springloginformandoauth2tutorial.services.AuthProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableOAuth2Client;

@Configuration
@EnableWebSecurity
@EnableOAuth2Client
public class SecurityConfig extends WebSecurityConfigurerAdapter {
	@Autowired
	private AuthProvider authProvider;

	@Bean
	PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http
				.authorizeRequests()
				.antMatchers( "/login**", "/registration", "/resources/**", "/registration", "/").permitAll()
				.anyRequest().authenticated()
				.and().formLogin().loginPage("/login")
				.defaultSuccessUrl("/ykw").failureUrl("/login?error").permitAll()
				.and().logout().logoutSuccessUrl("/").permitAll()
				.and().logout().logoutUrl("/logout");

		http
				.authorizeRequests().antMatchers("/resources/**").permitAll().anyRequest().permitAll();

		http
				.csrf().disable();

	}

	@Override
	protected void configure(AuthenticationManagerBuilder auth) {
		auth.authenticationProvider(authProvider);
	}
}
