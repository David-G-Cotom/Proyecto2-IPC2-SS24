import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { ReporteCompraAnuncio } from '../models/reporteCompraAnuncios';
import { Observable } from 'rxjs';
import { ReporteRevistaPopular } from '../models/reporteRevistasPopulares';
import { ReporteRevistaComentada } from '../models/reporteRevistasComentadas';

@Injectable({
  providedIn: 'root'
})
export class AdministradorService {

  urlBackend: string = 'http://localhost:8080/Proyecto2_SS24/resources/';

  constructor(private http: HttpClient) { }

  public getReporteAnunciosComprados(datosReporteAnunciosComprados: ReporteCompraAnuncio): Observable<any> {
    return this.http.post<any>(`${this.urlBackend}ReporteAdmin/compra-anuncios`, datosReporteAnunciosComprados);
  }

  public getReporteRevistasPopulares(datosReporte: ReporteRevistaPopular): Observable<any> {
    return this.http.post<any>(`${this.urlBackend}ReporteAdmin/revistas-populares`, datosReporte);
  }

  public getReporteRevistasComentadas(datosReporte: ReporteRevistaComentada): Observable<any> {
    return this.http.post<any>(`${this.urlBackend}ReporteAdmin/revistas-comentadas`, datosReporte);
  }

}
