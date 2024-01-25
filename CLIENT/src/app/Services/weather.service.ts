import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable, switchMap } from 'rxjs';
import { Weather } from '../Model/WeatherReport';

@Injectable({
  providedIn: 'root'
})
export class WeatherService {

  private authToken!: string;

  constructor(private http: HttpClient) { }

  // Method to fetch the authentication token
  private fetchAuthToken(): Observable<any> {
   // console.log("fetch token called");
  
    const tokenUrl = 'http://localhost:8080/token';
    const username = 'bharath';
    const password = 'reddy';
  
    const credentials = username + ':' + password;
    const base64Credentials = btoa(credentials);
  
    const httpOptions = {
      headers: new HttpHeaders({
        'Authorization': 'Basic ' + base64Credentials,
      }),
      observe: 'body' as 'body',  // Set the observe option to 'body'
      responseType: 'text' as 'text'
    };
  
    // Make the HTTP request to fetch the token
    return this.http.post(tokenUrl, {}, httpOptions);
  }
  getWeather(city: string): Observable<Weather> {

    // const backend: BackEndURL = new BackEndURL();
    // const url = backend.fetchreport;
    // return this.http.get<Weather>(url+city);

    //console.log("get weather called");

    if (!this.authToken) {
      return this.fetchAuthToken().pipe(
        switchMap((tokenResponse) => {
          // Assuming the token is in the 'accessToken' property of the response
          this.authToken = tokenResponse;
          console.log(this.authToken);

          // Use the fetched token in the subsequent request
          const backend: BackEndURL = new BackEndURL();
          const url = backend.fetchreport;

          // Set up headers with the fetched token
          const headers = new HttpHeaders().set('Authorization', `Bearer ${this.authToken}`);

          // Make the HTTP request with the headers
          return this.http.get<Weather>(url + city, { headers });
        })
      );
    } else {
      // Use the existing token for the request
      const backend: BackEndURL = new BackEndURL();
      const url = backend.fetchreport;

      // Set up headers with the existing token
      const headers = new HttpHeaders().set('Authorization', `Bearer ${this.authToken}`);

      // Make the HTTP request with the headers
      return this.http.get<Weather>(url + city, { headers });
    }
  }

}




export class BackEndURL {
  readonly server: string = "http://localhost:"
  readonly port: string = "8080"
  readonly fetchreport = this.server + this.port + "/weather-api/weather/city/"
}