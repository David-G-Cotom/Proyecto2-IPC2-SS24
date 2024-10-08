import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { User } from '../models/user';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class AuthService {

  urlBackend: string = 'http://localhost:8080/Proyecto2_SS24/resources/';

  constructor(private http: HttpClient) { }

  validarInicioSesion(user: User): Observable<User> {
    return this.http.get<User>(`${this.urlBackend}InicioSesion/${user.userName}/${user.password}`);
  }

  setLocalStorageItem(user: User):void {
    localStorage.setItem('Usuario-Actual', JSON.stringify(user));
  }

}
