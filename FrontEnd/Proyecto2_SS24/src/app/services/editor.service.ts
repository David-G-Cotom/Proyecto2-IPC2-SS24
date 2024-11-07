import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Recarga } from '../models/recarga';
import { Observable } from 'rxjs';
import { ReporteComentario } from '../models/reporteComentarios';
import { ContenidoReporteComentario } from '../models/contenidoReporteComentarios';
import { ReporteSuscripcion } from '../models/reporteSuscripciones';
import { ContenidoReporteSuscripcion } from '../models/contenidoReporteSuscripciones';
import { ReporteRevistasTop } from '../models/reporteRevistasTop';
import { ContenidoReporteRevistasTop } from '../models/contenidoReporteRevistasTop';

@Injectable({
  providedIn: 'root'
})
export class EditorService {

  urlBackend: string = 'http://localhost:8080/Proyecto2_SS24/resources/';

  constructor(private http: HttpClient) { }

  public registrarRecarga(recarga: Recarga): Observable<any> {
    return this.http.post<any>(`${this.urlBackend}RegistroRecarga`, recarga);
  }

  public registrarBloqueo(idRevista: string, cantidadDias: string): Observable<any> {
    return this.http.post<any>(`${this.urlBackend}RegistroBloque/${idRevista}/${cantidadDias}`, null);
  }

  public getReporteComentarios(datosReporteComentarios: ReporteComentario): Observable<ContenidoReporteComentario[]> {
    return this.http.post<ContenidoReporteComentario[]>(`${this.urlBackend}ReporteEditor/comentarios`, datosReporteComentarios);
  }

  public getReporteSuscripciones(datosReporteSuscripciones: ReporteSuscripcion): Observable<ContenidoReporteSuscripcion[]> {
    return this.http.post<ContenidoReporteSuscripcion[]>(`${this.urlBackend}ReporteEditor/suscripciones`, datosReporteSuscripciones);
  }

  public getReporteRevistasTop(datosReporteRevistasTop: ReporteRevistasTop): Observable<ContenidoReporteRevistasTop[]> {
    return this.http.post<ContenidoReporteRevistasTop[]>(`${this.urlBackend}ReporteEditor/revistas-top`, datosReporteRevistasTop);
  }

}
