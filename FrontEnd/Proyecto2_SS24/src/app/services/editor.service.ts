import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Recarga } from '../models/recarga';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class EditorService {

  urlBackend: string = 'http://localhost:8080/Proyecto2_SS24/resources/';

  constructor(private http: HttpClient) { }

  public registrarRecarga(recarga: Recarga): Observable<any> {
    return this.http.post<any>(`${this.urlBackend}RegistroRecarga`, recarga);
  }

}
