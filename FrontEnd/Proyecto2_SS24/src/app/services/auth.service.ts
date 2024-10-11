import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { User } from '../models/user';
import { Observable } from 'rxjs';
import { UsuarioAplicacion } from '../models/usuarioAplicacion';
import { UsuarioAplicacionJava } from '../models/usuarioAplicacionJava';

@Injectable({
  providedIn: 'root'
})
export class AuthService {

  urlBackend: string = 'http://localhost:8080/Proyecto2_SS24/resources/';

  constructor(private http: HttpClient) { }

  public validarInicioSesion(user: User): Observable<UsuarioAplicacionJava> {
    return this.http.get<UsuarioAplicacionJava>(`${this.urlBackend}InicioSesion/${user.userName}/${user.password}`);
  }

  public setLocalStorageItem(user: UsuarioAplicacionJava):void {
    localStorage.setItem('Usuario-Actual', JSON.stringify(user));
  }

  public registrarUsuario(user:UsuarioAplicacion, imagen:File): Observable<UsuarioAplicacionJava> {
    let formData = new FormData();
    formData.append('UserName', user.user.userName);
    formData.append('Password', user.user.password);
    formData.append('TipoUsuario', user.user.tipoUsuario);
    formData.append('Nombre', user.perfil.nombre);
    formData.append('Foto', imagen);
    return this.http.post<UsuarioAplicacionJava>(`${this.urlBackend}RegistroUsuario`, formData);
  }

}
