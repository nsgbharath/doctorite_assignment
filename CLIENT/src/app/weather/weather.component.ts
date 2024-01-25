import { Component } from '@angular/core';
import { WeatherService } from '../Services/weather.service';
import { Weather } from '../Model/WeatherReport';
import { HttpErrorResponse } from '@angular/common/http';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
@Component({
  selector: 'app-weather',
  templateUrl: './weather.component.html',
  styleUrls: ['./weather.component.css']
})
export class WeatherComponent {

  errorMessage:String=''
  weather!: Weather|null;
  weatherForm: FormGroup
  constructor(private weatherService: WeatherService,private fb:FormBuilder) {
    this.weatherForm = this.fb.group({
      city: [null, [Validators .required, Validators.pattern('[a-zA-Z]+( [a-zA-Z]+)*')]]
    });
  }

  
  fetchWeather() {
    this.weatherService.getWeather(this.weatherForm.get("city")?.value).subscribe(
      (data: Weather) => {
        this.weather = data;
        this.errorMessage = '';
      },
      (error) => {
        if (error.status === 400) {
          this.errorMessage = 'City does not exist';
        } else {
     console.log(error);
     
          this.errorMessage = 'An error occurred while fetching weather data.';
        }
        this.weather = null;
      }
    );
  }
}
