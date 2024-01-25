package com.bharath.doctoritetask.repository;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import com.bharath.doctoritetask.entity.Weather;

public interface WeatherRepository extends CrudRepository<Weather, Long> {
	Optional<Weather> findByCity(String city);

}
