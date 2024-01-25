package com.bharath.doctoritetask.service;

import java.time.LocalDateTime;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.bharath.doctoritetask.dto.OpenAPIResponse;
import com.bharath.doctoritetask.dto.WeatherDTO;
import com.bharath.doctoritetask.entity.Weather;
import com.bharath.doctoritetask.exception.WeatherReportException;
import com.bharath.doctoritetask.repository.WeatherRepository;

@Service(value = "weatherService")
public class WeatherServiceImpl implements WeatherService {

	@Autowired
	private WeatherRepository weatherRepository;
@Value("${weather.api.key}")
	private String weather_app_key;
	@Override
	public WeatherDTO fetchWeatherReport(String city) throws WeatherReportException {
		Optional<Weather> optional = weatherRepository.findByCity(city);

		Weather weather = optional.orElseGet(() -> getWeatherFromOpenAPI(city));
		if (weather == null)
			throw new WeatherReportException("temperature not fetched");
		if (weather.getFetchedAt().isBefore(LocalDateTime.now().plusHours(2))) {
			Weather updatedWeather = getWeatherFromOpenAPI(city);
			weather.setHumidity(updatedWeather.getHumiditiy());
			weather.setMinTemp(updatedWeather.getMinTemp());
			weather.setTemperature(updatedWeather.getTemperature());
			weather.setPressure(updatedWeather.getPressure());
			weather.setFetchedAt(LocalDateTime.now());
			updatedWeather = weatherRepository.save(weather);
			return entityToDTO(updatedWeather);
		} else {
			return entityToDTO(weather);
		}

	}

	private Weather getWeatherFromOpenAPI(String city) {
		RestTemplate restTemplate = new RestTemplate();
		String url = String.format(
				"https://api.openweathermap.org/data/2.5/weather?q=%s&appid=%s", city,weather_app_key);
		OpenAPIResponse response = restTemplate.getForObject(url, OpenAPIResponse.class);
		Weather weather = new Weather();
		weather.setCity(city);

		weather.setTemperature(convert(response.getMain().getTemp()));
		weather.setMaxTemp(convert(response.getMain().getTemp_max()));
		weather.setMinTemp(convert(response.getMain().getTemp_min()));
		weather.setLon(response.getCoord().getLon());
		weather.setLat(response.getCoord().getLat());
		weather.setFetchedAt(LocalDateTime.now());
		weather.setTime(LocalDateTime.now());
		weather.setType(response.getWeather().get(0).getMain());
		weather.setPressure(response.getMain().getPressure());
		weather.setHumidity(response.getMain().getHumidity());
		return weather;

	}

	private String convert(Double temp) {
		temp = temp - 273;
		String st = temp.toString();
		return temp.toString().substring(0, st.indexOf('.') +3 );
	}

	private WeatherDTO entityToDTO(Weather weather) {

		WeatherDTO dto = new WeatherDTO();
	
		dto.setLat(weather.getLat().toString());
		dto.setLon(weather.getLon().toString());
		dto.setCity(weather.getCity());
		dto.setMaxTemp(weather.getMaxTemp());
		dto.setMinTemp(weather.getMinTemp());
		dto.setTemp(weather.getTemperature());
		dto.setType(weather.getType());
		dto.setLat(weather.getLat().toString());
		dto.setLon(weather.getLon().toString());
		dto.setPressure(weather.getPressure());
		dto.setHumidity(weather.getHumiditiy());
		dto.setTime(weather.getTime());
		return dto;
	}
}
