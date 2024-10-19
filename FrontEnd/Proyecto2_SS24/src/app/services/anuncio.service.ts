import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { AnuncioTexto } from '../models/anuncioTexto';

@Injectable({
  providedIn: 'root'
})
export class AnuncioService {

  urlBackend: string = 'http://localhost:8080/Proyecto2_SS24/resources/';

  constructor(private http: HttpClient) { }

  public registrarCompraAdText(anuncio: AnuncioTexto): Observable<any> {
    return this.http.post<any>(`${this.urlBackend}RegistroAdText`, anuncio);
  }

}
