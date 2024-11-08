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

  //Servicio que trae TODOS los Anuncios que son del ANUNCIANTE, SIN importar que esten o no activos
  //SIN importar que esten o no vigentes
  //Lo Utiliza el Anunciante
  public getAllAnunciosAnunciante(idUsuarioAnunciante: number): Observable<Anuncio[]> {
    return this.http.get<Anuncio[]>(`${this.urlBackend}ObtenerAnuncio/${idUsuarioAnunciante}`)
  }

  //Servicio que trae TODOS los anuncios que estan REGISTRADOS, SIN importar que esten o no activos
  //SIN importar que esten o no vigentes
  //Lo Utiliza el Administrador
  public getAllAnuncios(): Observable<Anuncio[]> {
    return this.http.get<Anuncio[]>(`${this.urlBackend}ObtenerAnuncio`);
  }

  //Servicio que trae TODOS los anuncios REGISTRADOS que SI esten activos y que SI esten vigentes
  //Es Utilizado para Mostrar los Anuncios en la Aplicacion en General
  public getAllAnunciosActivos(): Observable<any> {
    return this.http.get<any>(`${this.urlBackend}ObtenerAnuncio/publicidad`);
  }

  public actualizarAnuncioAnunciante(anuncio: Anuncio): Observable<any> {
    return this.http.put<any>(`${this.urlBackend}RegistroAd/anunciante`, anuncio);
  }

  public actualizarAnuncioAdmin(anuncio: Anuncio): Observable<any> {
    return this.http.put<any>(`${this.urlBackend}RegistroAd/administrador`, anuncio);
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

  public deleteAnuncio(idAnuncio: number): Observable<any> {
    return this.http.delete<any>(`${this.urlBackend}EliminarAnuncio/${idAnuncio}`);
  }

}
