package com.bharath.doctoritetask.service;

import com.bharath.doctoritetask.dto.WeatherDTO;
import com.bharath.doctoritetask.exception.WeatherReportException;

public interface WeatherService {
	
public WeatherDTO	fetchWeatherReport(String city) throws WeatherReportException;

}
