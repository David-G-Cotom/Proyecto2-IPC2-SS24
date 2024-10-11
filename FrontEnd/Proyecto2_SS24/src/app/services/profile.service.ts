import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { UsuarioAplicacionJava } from '../models/usuarioAplicacionJava';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class ProfileService {

  urlBackend: string = 'http://localhost:8080/Proyecto2_SS24/resources/';

  constructor(private http: HttpClient) { }

  public actualizarPerfil(user: UsuarioAplicacionJava): Observable<UsuarioAplicacionJava> {
    return this.http.put<UsuarioAplicacionJava>(`${this.urlBackend}PerfilUsuario`, user);
  }

  public actualizarLocalStorage(nuevoUsuario: UsuarioAplicacionJava) {
    localStorage.removeItem('Usuario-Actual')
    localStorage.setItem('Usuario-Actual', JSON.stringify(nuevoUsuario));
  }

  public getPerfil() {

  }

}
