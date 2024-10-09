import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { User } from '../models/user';
import { Observable } from 'rxjs';
import { UsuarioAplicacion } from '../models/usuarioAplicacion';

@Injectable({
  providedIn: 'root'
})
export class AuthService {

  urlBackend: string = 'http://localhost:8080/Proyecto2_SS24/resources/';

  constructor(private http: HttpClient) { }

  validarInicioSesion(user: User): Observable<User> {
    return this.http.get<User>(`${this.urlBackend}InicioSesion/${user.userName}/${user.password}`);
  }

  setLocalStorageItem(user: User):void {
    localStorage.setItem('Usuario-Actual', JSON.stringify(user));
  }

  registrarUsuario(user:UsuarioAplicacion, imagen:File) {
    let formData = new FormData();
    formData.append("UsuarioAplicacion", JSON.stringify(user));
    formData.append("Foto", imagen, imagen.name);
    return this.http.post<UsuarioAplicacion>(this.urlBackend + "RegistroUsuario", formData);
  }

}
