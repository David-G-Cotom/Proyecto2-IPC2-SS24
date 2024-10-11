import { Injectable } from '@angular/core';
import { Router } from '@angular/router';
import { User } from '../models/user';
import { UsuarioAplicacionJava } from '../models/usuarioAplicacionJava';

@Injectable({
  providedIn: 'root'
})
export class RoutingService {

  user: UsuarioAplicacionJava | null = null;

  constructor(private redireccionar: Router) { }

  enviarPagina(direccion: string) {
    this.redireccionar.navigate([direccion]);
  }

  redireccionarUsuario(): void {
    this.user = JSON.parse(`${localStorage.getItem('Usuario-Actual')}`);
    switch (this.user?.idTipoUsuario) {
      case 1:
        this.redireccionar.navigate(['editor/home-page']);
        break;
      case 2:
        this.redireccionar.navigate(['suscriptor/home-page']);
        break;
      case 3:
        this.redireccionar.navigate(['anunciante/home-page']);
        break;
      case 4:
        this.redireccionar.navigate(['administrador/home-page']);
        break;
      default:
        this.redireccionar.navigate(['page-not-found']);
        break;
    }
  }

}
