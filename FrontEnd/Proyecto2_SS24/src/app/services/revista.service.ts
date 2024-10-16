import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Revista } from '../models/revista';
import { Publicacion } from '../models/publicacion';

@Injectable({
  providedIn: 'root'
})
export class RevistaService {

  urlBackend: string = 'http://localhost:8080/Proyecto2_SS24/resources/';

  constructor(private http: HttpClient) { }

  public registrarRevista(revista: Revista): Observable<any> {
    return this.http.post<any>(`${this.urlBackend}RegistroRevista`, revista);
  }

  public registrarPublicacion(publicacion: Publicacion): Observable<Publicacion> {
    let formData = new FormData;
    formData.append('numeroPublicacion', publicacion.numeroPublicacion);
    formData.append('PDF', publicacion.pdf);
    return this.http.post<Publicacion>(`${this.urlBackend}RegistroPublicacion`, formData);
  }

  public actualizarRevista(revista: Revista): Observable<Revista> {
    return this.http.put<Revista>(`${this.urlBackend}RegistroRevista`, revista)
  }

}
