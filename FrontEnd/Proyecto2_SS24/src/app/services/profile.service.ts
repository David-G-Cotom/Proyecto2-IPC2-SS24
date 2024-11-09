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

  public actualizarPerfil1(user: UsuarioAplicacionJava, imagenNueva: File): Observable<any> {
    let formData = new FormData();
    formData.append('UserName', user.userName);
    formData.append('Password', user.password);
    formData.append('Nombre', user.nombre);
    formData.append('Hobbies', user.hobbies);
    formData.append('TemasInteres', user.temasInteres);
    formData.append('Desripcion', user.descripcion);
    formData.append('Gustos', user.gustos);
    formData.append('IDusuario', user.idUsuario.toString());
    formData.append('IDtipoUsuario', user.idTipoUsuario.toString());
    formData.append('Foto', imagenNueva);
    return this.http.put<any>(`${this.urlBackend}PerfilUsuario/v1`, formData);
  }

  public actualizarPerfil2(user: UsuarioAplicacionJava): Observable<any> {
    return this.http.put<any>(`${this.urlBackend}PerfilUsuario/v2`, user);
  }

  public actualizarLocalStorage(nuevoUsuario: UsuarioAplicacionJava) {
    localStorage.removeItem('Usuario-Actual')
    localStorage.setItem('Usuario-Actual', JSON.stringify(nuevoUsuario));
  }

  public getPerfilEditor(idEditor: number): Observable<UsuarioAplicacionJava> {
    return this.http.get<UsuarioAplicacionJava>(`${this.urlBackend}PerfilUsuario/${idEditor}`);
  }

  public getCredito(idUsuario: number, idTipoUsuario: number): Observable<number> {
    return this.http.get<number>(`${this.urlBackend}PerfilUsuario/credito/${idUsuario}/${idTipoUsuario}`);
  }

}
