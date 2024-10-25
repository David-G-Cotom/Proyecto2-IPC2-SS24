import { Component } from '@angular/core';
import { AnuncioTextoComponent } from "../anuncio-texto/anuncio-texto.component";
import { AnuncioTextoImagenComponent } from "../anuncio-texto-imagen/anuncio-texto-imagen.component";
import { AnuncioVideoComponent } from "../anuncio-video/anuncio-video.component";
import { Anuncio } from '../../../models/anuncio';
import { AnuncioService } from '../../../services/anuncio.service';
import { RoutingService } from '../../../services/routing.service';
import { UsuarioAplicacionJava } from '../../../models/usuarioAplicacionJava';

@Component({
  selector: 'app-content-anunciante',
  standalone: true,
  imports: [AnuncioTextoComponent, AnuncioTextoImagenComponent, AnuncioVideoComponent],
  templateUrl: './content-anunciante.component.html',
  styleUrl: './content-anunciante.component.css'
})
export class ContentAnuncianteComponent {

  anuncios: Anuncio[] = [];
  usuarioLogeado: UsuarioAplicacionJava;

  constructor(private anuncioService: AnuncioService, private routingServices: RoutingService) {
    this.usuarioLogeado = JSON.parse(`${localStorage.getItem('Usuario-Actual')}`);
  }

  ngOnInit(): void {
    this.anuncioService.getAllAnuncios(this.usuarioLogeado.idUsuario).subscribe({
      next: (listado: Anuncio[]) => {
        console.log('Todo fue bien, procesando response...');
        if (listado.length === 0) {
          alert('No Tienes Anuncios Comprados');
          this.routingServices.redireccionarUsuario();
        } else {
          this.anuncios = listado;
          for (const anuncio of this.anuncios) {
            localStorage.removeItem(`Anuncio${anuncio.idAnuncio}`);
            localStorage.setItem(`Anuncio${anuncio.idAnuncio}`, JSON.stringify(anuncio));
          }
        }
      },
      error: (error: any) => {
        console.log(error);
      }
    });
  }

}
