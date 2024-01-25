import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Weather } from '../Model/WeatherReport';

@Injectable({
  providedIn: 'root'
})
export class WeatherService {
  constructor(private http: HttpClient) { }
  getWeather(city: string): Observable<Weather> {

    const backend: BackEndURL = new BackEndURL();
    const url = backend.fetchreport;
    return this.http.get<Weather>(url+city);

  }


}

export class BackEndURL{
  readonly server:string="http://localhost:"
  readonly port:string="8080"
  readonly fetchreport=this.server+this.port+"/weather-api/weather/city/"
}