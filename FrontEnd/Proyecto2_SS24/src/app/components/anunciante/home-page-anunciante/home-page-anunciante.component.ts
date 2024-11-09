import { Component, OnInit } from '@angular/core';
import { MenuAnuncianteComponent } from "../menu-anunciante/menu-anunciante.component";
import { ContentAnuncianteComponent } from "../content-anunciante/content-anunciante.component";
import { RouterOutlet } from '@angular/router';
import { UsuarioAplicacionJava } from '../../../models/usuarioAplicacionJava';
import { ProfileService } from '../../../services/profile.service';

@Component({
  selector: 'app-home-page-anunciante',
  standalone: true,
  imports: [MenuAnuncianteComponent, ContentAnuncianteComponent, RouterOutlet],
  templateUrl: './home-page-anunciante.component.html',
  styleUrl: './home-page-anunciante.component.css'
})
export class HomePageAnuncianteComponent implements OnInit{

  creditoActual: number = 0;
  usuarioLogeado: UsuarioAplicacionJava;

  constructor(private profileService: ProfileService) {
    this.usuarioLogeado = JSON.parse(`${localStorage.getItem('Usuario-Actual')}`);
  }

  ngOnInit(): void {
    this.profileService.getCredito(this.usuarioLogeado.idUsuario, this.usuarioLogeado.idTipoUsuario).subscribe({
      next: (mensaje: number) => {
        console.log('Todo fue bien con creditor, procesando response...');
        this.creditoActual = mensaje;
      },
      error: (error: any) => {
        console.log(error);
      }
    });
  }

}
