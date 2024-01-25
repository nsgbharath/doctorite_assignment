package com.bharath.doctoritetask.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.oauth2.server.resource.OAuth2ResourceServerConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.jwt.JwtDecoder;
import org.springframework.security.oauth2.jwt.JwtEncoder;
import org.springframework.security.oauth2.jwt.NimbusJwtDecoder;
import org.springframework.security.oauth2.jwt.NimbusJwtEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.provisioning.UserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

import com.nimbusds.jose.jwk.JWK;
import com.nimbusds.jose.jwk.JWKSet;
import com.nimbusds.jose.jwk.RSAKey;
import com.nimbusds.jose.jwk.source.ImmutableJWKSet;
import com.nimbusds.jose.jwk.source.JWKSource;
import com.nimbusds.jose.proc.SecurityContext;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

	private  RsaKeyProperties rsaKeys;
	@Autowired
	public SecurityConfig(RsaKeyProperties rsaKeys) {
		this.rsaKeys = rsaKeys;
		// TODO Auto-generated constructor stub
	}

	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
		return http.csrf(csrf -> csrf.disable()).oauth2ResourceServer(OAuth2ResourceServerConfigurer::jwt)
				.authorizeRequests(req -> req.anyRequest().authenticated())
				.sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
				.httpBasic(Customizer.withDefaults()).build();
	}

	@Bean
	public UserDetailsManager userDetails() {
		return new InMemoryUserDetailsManager(
				User.withUsername("bharath").password("{noop}reddy").authorities("read").build());
	}

//	@Bean
//	public PasswordEncoder encoder() {
//		//return new BCryptPasswordEncoder();
//		return new  NoOpPasswordEncoder();
//	}

	@Bean
	public JwtDecoder jwtdecoder() {
		return NimbusJwtDecoder.withPublicKey(rsaKeys.publickey()).build();
	}
	
	@Bean
	public JwtEncoder jwtEncoder() {
		JWK jwk=new RSAKey.Builder(rsaKeys.publickey()).privateKey(rsaKeys.privateKey()).build();
		JWKSource<SecurityContext> jwks=new ImmutableJWKSet<>(new JWKSet(jwk));
		return new  NimbusJwtEncoder(jwks);
	}
	
}
