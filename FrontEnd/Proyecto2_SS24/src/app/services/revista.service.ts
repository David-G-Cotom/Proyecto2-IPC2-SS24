import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Revista } from '../models/revista';
import { Publicacion } from '../models/publicacion';
import { FiltroBusquedaRevista } from '../models/filtroBusquedaRevista';

@Injectable({
  providedIn: 'root'
})
export class RevistaService {

  urlBackend: string = 'http://localhost:8080/Proyecto2_SS24/resources/';

  constructor(private http: HttpClient) { }

  public registrarRevista(revista: Revista): Observable<any> {
    return this.http.post<any>(`${this.urlBackend}RegistroRevista`, revista);
  }

  public registrarPublicacion(publicacion: Publicacion, idRevista: string): Observable<any> {
    let formData = new FormData;
    formData.append('numeroPublicacion', publicacion.numeroPublicacion);
    formData.append('idRevista', idRevista);
    formData.append('PDF', publicacion.pdf);
    return this.http.post<any>(`${this.urlBackend}RegistroPublicacion`, formData);
  }

  public actualizarRevista(revista: Revista): Observable<any> {
    return this.http.put<any>(`${this.urlBackend}RegistroRevista`, revista)
  }

  public getAllRevistasEditor(idUsuario: number): Observable<Revista[]> {
    return this.http.get<Revista[]>(`${this.urlBackend}ObtenerRevista/${idUsuario}`);
  }

  public getRevistasNOSuscritas(idUsuario: number): Observable<Revista[]> {
    return this.http.get<Revista[]>(`${this.urlBackend}RevistaNoSuscrita/${idUsuario}`);
  }

  public getRevistasSuscritas(filtro: FiltroBusquedaRevista): Observable<Revista[]> {
    return this.http.post<Revista[]>(`${this.urlBackend}ObtenerRevista`, filtro);
  }

  public getAllRevistas(): Observable<Revista[]> {
    return this.http.get<Revista[]>(`${this.urlBackend}ObtenerRevista`);
  }

  public registrarPreciosNuevos(precios: string[], idRevista: number): Observable<any> {
    return this.http.post<any>(`${this.urlBackend}RegistroPrecio/revista/${idRevista}`, precios);
  }

}
