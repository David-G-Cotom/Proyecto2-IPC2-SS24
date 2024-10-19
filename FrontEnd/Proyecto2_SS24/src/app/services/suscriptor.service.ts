import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Comentario } from '../models/comentario';
import { Observable } from 'rxjs';
import { Like } from '../models/like';

@Injectable({
  providedIn: 'root'
})
export class SuscriptorService {

  urlBackend: string = 'http://localhost:8080/Proyecto2_SS24/resources/';

  constructor(private http: HttpClient) { }

  public registrarComentario(comentario: Comentario): Observable<any> {
    return this.http.post<any>(`${this.urlBackend}RegistroComentario`, comentario);
  }

  public registrarLike(like: Like): Observable<any> {
    return this.http.post<any>(`${this.urlBackend}RegistroLike`, like);
  }

}
