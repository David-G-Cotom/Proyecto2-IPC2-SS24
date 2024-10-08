import { Injectable } from '@angular/core';
import { Router } from '@angular/router';
import { User } from '../models/user';

@Injectable({
  providedIn: 'root'
})
export class RoutingService {

  user: User | null = null;

  constructor(private redireccionar: Router) { }

  enviarPagina(direccion: string) {
    this.redireccionar.navigate([direccion]);
  }

  redireccionarUsuario(): void {
    this.user = JSON.parse(`${localStorage.getItem('Usuario-Actual')}`);
    switch (this.user?.tipoUsuario) {
      case "Editor":
        this.redireccionar.navigate(['editor/home-page']);
        break;
      case "Suscriptor":
        this.redireccionar.navigate(['suscriptor/home-page']);
        break;
      case "Anunciante":
        this.redireccionar.navigate(['anunciante/home-page']);
        break;
      case "Administrador":
        this.redireccionar.navigate(['administrador/home-page']);
        break;
      default:
        this.redireccionar.navigate(['page-not-found']);
        break;
    }
  }

}
