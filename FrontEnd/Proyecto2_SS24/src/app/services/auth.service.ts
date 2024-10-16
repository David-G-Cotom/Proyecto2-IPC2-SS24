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

  public validarInicioSesion(user: User): Observable<any> {
    return this.http.get<any>(`${this.urlBackend}InicioSesion/${user.userName}/${user.password}`);
  }

  public setLocalStorageItem(user: any): void {
    localStorage.setItem('Usuario-Actual', JSON.stringify(user));
  }

  public setSessionStorage(token: any): void {
    sessionStorage.setItem('token', token);
  }

  public registrarUsuario(user:UsuarioAplicacion, imagen:File): Observable<any> {
    let formData = new FormData();
    formData.append('UserName', user.user.userName);
    formData.append('Password', user.user.password);
    formData.append('TipoUsuario', user.user.tipoUsuario);
    formData.append('Nombre', user.perfil.nombre);
    formData.append('Foto', imagen);
    return this.http.post<any>(`${this.urlBackend}RegistroUsuario`, formData);
  }

  public removeLocalStorageItem(): void {
    localStorage.removeItem('Usuario-Actual');
    localStorage.clear();
  }

}
