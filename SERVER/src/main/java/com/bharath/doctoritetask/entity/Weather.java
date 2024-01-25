package com.bharath.doctoritetask.entity;

import java.time.LocalDateTime;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "weather_details")
public class Weather {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String city;
	private LocalDateTime time;
	private LocalDateTime fetchedAt;
	private String temperature;
	private String minTemp;
	private String maxTemp;
	private String type;
	
	private Double lat;
	private Double lon;
	private int pressure;
	private int humidity;

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public LocalDateTime getTime() {
		return time;
	}

	public void setTime(LocalDateTime time) {
		this.time = time;
	}

	public LocalDateTime getFetchedAt() {
		return fetchedAt;
	}

	public void setFetchedAt(LocalDateTime fetchedAt) {
		this.fetchedAt = fetchedAt;
	}

	public String getTemperature() {
		return temperature;
	}

	public void setTemperature(String temperature) {
		this.temperature = temperature;
	}

	public Weather() {
		super();

	}

	public Weather(String city, Long id, LocalDateTime time, LocalDateTime fetchedAt, String temperature) {
		super();
		this.city = city;
		this.id = id;
		this.time = time;
		this.fetchedAt = fetchedAt;
		this.temperature = temperature;
	}

	public String getMinTemp() {
		return minTemp;
	}

	public void setMinTemp(String minTemp) {
		this.minTemp = minTemp;
	}

	public String getMaxTemp() {
		return maxTemp;
	}

	public void setMaxTemp(String maxTemp) {
		this.maxTemp = maxTemp;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	

	public Double getLat() {
		return lat;
	}

	public void setLat(Double lat) {
		this.lat = lat;
	}

	public Double getLon() {
		return lon;
	}

	public void setLon(Double lon) {
		this.lon = lon;
	}

	public void setPressure(int pressure) {
		this.pressure = pressure;

	}

	public void setHumidity(int humidity) {
		this.humidity = humidity;

	}

	public Integer getPressure() {
		// TODO Auto-generated method stub
		return this.pressure;
	}

	public Integer getHumiditiy() {
		// TODO Auto-generated method stub
		return this.humidity;
	}

}
