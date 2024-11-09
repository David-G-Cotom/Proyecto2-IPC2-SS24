import { Component, OnInit } from '@angular/core';
import { MenuSuscriptorComponent } from "../menu-suscriptor/menu-suscriptor.component";
import { RouterOutlet } from '@angular/router';
import { AnuncioLateralComponent } from '../anuncio-lateral/anuncio-lateral.component';
import { Anuncio } from '../../../models/anuncio';
import { AnuncioService } from '../../../services/anuncio.service';

@Component({
  selector: 'app-home-page-suscriptor',
  standalone: true,
  imports: [MenuSuscriptorComponent, RouterOutlet, AnuncioLateralComponent],
  templateUrl: './home-page-suscriptor.component.html',
  styleUrl: './home-page-suscriptor.component.css'
})
export class HomePageSuscriptorComponent implements OnInit{

  anunciosMostrarIzquierda: Anuncio[] = [];
  anunciosMostrarDeracha: Anuncio[] = [];

  constructor(private anuncioService: AnuncioService) {}

  ngOnInit(): void {
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
