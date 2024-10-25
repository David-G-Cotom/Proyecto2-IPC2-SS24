import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { AnuncioTexto } from '../models/anuncioTexto';
import { AnuncioImagen } from '../models/anuncioImagen';
import { AnuncioVideo } from '../models/anuncioVideo';

@Injectable({
  providedIn: 'root'
})
export class AnuncioService {

  urlBackend: string = 'http://localhost:8080/Proyecto2_SS24/resources/';

  constructor(private http: HttpClient) { }

  public registrarCompraAdText(anuncio: AnuncioTexto): Observable<any> {
    return this.http.post<any>(`${this.urlBackend}RegistroAd/text`, anuncio);
  }

  public registrarCompraAdImage(anuncio: AnuncioImagen, imagen: File): Observable<any> {
    let formData = new FormData();
    formData.append('Fecha', anuncio.fechaCompra);
    formData.append('Duracion', anuncio.vigenciaDias.toString());
    formData.append('Titulo', anuncio.titulo);
    formData.append('Contenido', anuncio.contenido);
    formData.append('IdUsuario', anuncio.idInversionista.toString());
    formData.append('Imagen', imagen);
    return this.http.post<any>(`${this.urlBackend}RegistroAd/image`, formData);
  }

  public registrarCompraAdVideo(anuncio: AnuncioVideo, video: File): Observable<any> {
    let formData = new FormData();
    formData.append('Fecha', anuncio.fechaCompra);
    formData.append('Duracion', anuncio.vigenciaDias.toString());
    formData.append('Titulo', anuncio.titulo);
    formData.append('IdUsuario', anuncio.idInversionista.toString());
    formData.append('Video', video);
    return this.http.post<any>(`${this.urlBackend}RegistroAd/video`, formData);
  }

}
