import { Component, OnInit } from '@angular/core';
import { MenuEditorComponent } from "../menu-editor/menu-editor.component";
import { ContentEditorComponent } from "../content-editor/content-editor.component";
import { RouterOutlet } from '@angular/router';
import { UsuarioAplicacionJava } from '../../../models/usuarioAplicacionJava';
import { ProfileService } from '../../../services/profile.service';
import { Anuncio } from '../../../models/anuncio';
import { AnuncioLateralComponent } from '../../suscriptor/anuncio-lateral/anuncio-lateral.component';
import { AnuncioService } from '../../../services/anuncio.service';

@Component({
  selector: 'app-home-page-editor',
  standalone: true,
  imports: [MenuEditorComponent, ContentEditorComponent, RouterOutlet, AnuncioLateralComponent],
  templateUrl: './home-page-editor.component.html',
  styleUrl: './home-page-editor.component.css'
})
export class HomePageEditorComponent implements OnInit{

  creditoActual: number = 0;
  usuarioLogeado: UsuarioAplicacionJava;
  anunciosMostrarIzquierda: Anuncio[] = [];
  anunciosMostrarDeracha: Anuncio[] = [];

  constructor(private profileService: ProfileService, private anuncioService: AnuncioService) {
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
    this.anuncioService.getAllAnunciosActivos().subscribe({
      next: (mensaje: any) => {
        console.log('Todo fue bien, procesando response...');
        if (mensaje.mensaje === 'exito') {
          console.log(mensaje);
          console.log('Exito');
          this.anunciosMostrarDeracha = mensaje.contenidoDerecho;
          this.anunciosMostrarIzquierda = mensaje.contenidoIzquierdo;
          console.log(this.anunciosMostrarDeracha);
          console.log(this.anunciosMostrarIzquierda);
        }
      }, error: (error: any) => {
        console.log('HUBO ERROR');
        console.log(error);
      }
    });
  }

}
