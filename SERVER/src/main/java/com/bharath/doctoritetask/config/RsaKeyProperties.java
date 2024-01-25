package com.bharath.doctoritetask.config;

import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
@ConfigurationProperties(prefix="rsa")

public record RsaKeyProperties(RSAPublicKey publickey,RSAPrivateKey privateKey) {

}
