import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Anuncio } from '../models/anuncio';

@Injectable({
  providedIn: 'root'
})
export class AnuncioService {

  urlBackend: string = 'http://localhost:8080/Proyecto2_SS24/resources/';

  constructor(private http: HttpClient) { }

  public registrarCompraAdText(anuncio: Anuncio): Observable<any> {
    return this.http.post<any>(`${this.urlBackend}RegistroAd/text`, anuncio);
  }

  public registrarCompraAdImage(anuncio: Anuncio, imagen: File): Observable<any> {
    let formData = new FormData();
    formData.append('Fecha', anuncio.fechaCompra);
    formData.append('Duracion', anuncio.vigenciaDias.toString());
    formData.append('Titulo', anuncio.titulo);
    formData.append('Contenido', anuncio.contenido);
    formData.append('IdUsuario', anuncio.idInversionista.toString());
    formData.append('Imagen', imagen);
    return this.http.post<any>(`${this.urlBackend}RegistroAd/image`, formData);
  }

  public registrarCompraAdVideo(anuncio: Anuncio, video: File): Observable<any> {
    let formData = new FormData();
    formData.append('Fecha', anuncio.fechaCompra);
    formData.append('Duracion', anuncio.vigenciaDias.toString());
    formData.append('Titulo', anuncio.titulo);
    formData.append('IdUsuario', anuncio.idInversionista.toString());
    formData.append('Video', video);
    return this.http.post<any>(`${this.urlBackend}RegistroAd/video`, formData);
  }

  public getAllAnuncios(idUsuarioAnunciante: number): Observable<Anuncio[]> {
    return this.http.get<Anuncio[]>(`${this.urlBackend}ObtenerAnuncio/${idUsuarioAnunciante}`)
  }

  public actualizarAnuncio(anuncio: Anuncio): Observable<any> {
    return this.http.put<any>(`${this.urlBackend}RegistroAd`, anuncio);
  }

  public getrPreciosTipoAnuncio(): Observable<number[]> {
    return this.http.get<number[]>(`${this.urlBackend}ObtenerPrecio/tipoAnuncio`);
  }

  public registrarPreciosTipoAnuncio(precios: string[]): Observable<any> {
    return this.http.post<any>(`${this.urlBackend}RegistroPrecio/tipoAnuncio`, precios);
  }

  public getrPreciosTiempoAnuncio(): Observable<number[]> {
    return this.http.get<number[]>(`${this.urlBackend}ObtenerPrecio/tiempoAnuncio`);
  }
  
  public registrarPreciosTiempoAnuncio(precios: string[]): Observable<any> {
    return this.http.post<any>(`${this.urlBackend}RegistroPrecio/tiempoAnuncio`, precios);
  }

}
