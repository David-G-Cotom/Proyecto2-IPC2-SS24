import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Suscripcion } from '../models/suscripcion';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class SuscripcionService {

  urlBackend: string = 'http://localhost:8080/Proyecto2_SS24/resources/';

  constructor(private http: HttpClient) { }

public registrarSuscripcion(suscripcion: Suscripcion): Observable<any> {
  return this.http.post<any>(`${this.urlBackend}RegistroSuscripcion`, suscripcion);
}

}
