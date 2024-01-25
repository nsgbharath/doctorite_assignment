package com.bharath.doctoritetask.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bharath.doctoritetask.service.TokenService;

@RestController
@CrossOrigin
public class AuthController {
	private static final Logger logger = LoggerFactory.getLogger(AuthController.class);
	@Autowired
	private TokenService tokenService;

	@PostMapping(value = "/token")
	public ResponseEntity<String> token(Authentication authentication) {
		logger.debug("token requested for user :{}", authentication.getName());
		String token = tokenService.generateToken(authentication);
		logger.debug("Token granted {}", token);
		return new ResponseEntity<String>(token,HttpStatus.OK);
	}
}
