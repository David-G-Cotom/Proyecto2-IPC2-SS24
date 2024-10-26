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

  public getImagenAnuncio(idAnuncioImagen: number): Observable<Blob> {
    return this.http.get(`${this.urlBackend}Imagen/anuncio/${idAnuncioImagen}`, {responseType: 'blob'});
  }

  public getVideoAnuncio(idAnuncioVideo: number): Observable<Blob> {
    return this.http.get(`${this.urlBackend}Media/anuncio/${idAnuncioVideo}`, {responseType: 'blob'});
  }

  public getPdfPublicacion(idPublicacion: number): Observable<Blob> {
    return this.http.get(`${this.urlBackend}Media/publicacion/${idPublicacion}`, {responseType: 'blob'});
  }

}
