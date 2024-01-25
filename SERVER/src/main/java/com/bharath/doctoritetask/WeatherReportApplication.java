package com.bharath.doctoritetask;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.PropertySource;

import com.bharath.doctoritetask.config.RsaKeyProperties;

@SpringBootApplication
@PropertySource(value = { "messages.properties" })
@EnableConfigurationProperties(RsaKeyProperties.class)
public class WeatherReportApplication {

	public static void main(String[] args) {
		SpringApplication.run(WeatherReportApplication.class, args);
	}

}
