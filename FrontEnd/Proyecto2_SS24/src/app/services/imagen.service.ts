import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class ImagenService {

  urlBackend: string = 'http://localhost:8080/Proyecto2_SS24/resources/';

  constructor(private http: HttpClient) { }

  public getImagenPerfil(idUsuario: number): Observable<Blob> {
    return this.http.get(`${this.urlBackend}Imagen/usuario/${idUsuario}`, {responseType: 'blob'});
  }

  public getImagenPerfilEditor(idEditor: number): Observable<Blob> {
    return this.http.get(`${this.urlBackend}Imagen/editor/${idEditor}`, {responseType: 'blob'});
  }

}
