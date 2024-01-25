package com.bharath.doctoritetask.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bharath.doctoritetask.dto.WeatherDTO;
import com.bharath.doctoritetask.exception.WeatherReportException;
import com.bharath.doctoritetask.service.WeatherService;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

@RequestMapping(value = "weather-api")
@RestController
@Validated
@CrossOrigin
public class WeatherAPI {
	@Autowired
	private WeatherService weatherService;

	@GetMapping(value = "/weather/city/{city}")
	public ResponseEntity<WeatherDTO> getWeather(
			@PathVariable(name = "city") @Valid @NotEmpty(message="please provide city") @Pattern(regexp = "([a-zA-Z]+)( [a-zA-Z]+)*", message = "please provide a valid city name") String city) throws WeatherReportException {
		return new ResponseEntity<WeatherDTO>(weatherService.fetchWeatherReport(city), HttpStatus.OK);
	}
}
