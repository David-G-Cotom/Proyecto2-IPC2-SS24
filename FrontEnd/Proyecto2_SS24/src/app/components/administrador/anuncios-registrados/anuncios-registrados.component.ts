import { Component } from '@angular/core';
import { Anuncio } from '../../../models/anuncio';
import { AnuncioService } from '../../../services/anuncio.service';
import { RoutingService } from '../../../services/routing.service';
import { AdTextAEditarComponent } from '../ad-text-aeditar/ad-text-aeditar.component';
import { AdImageAEditarComponent } from '../ad-image-aeditar/ad-image-aeditar.component';
import { AdVideoAEditarComponent } from '../ad-video-aeditar/ad-video-aeditar.component';

@Component({
  selector: 'app-anuncios-registrados',
  standalone: true,
  imports: [AdTextAEditarComponent, AdImageAEditarComponent, AdVideoAEditarComponent],
  templateUrl: './anuncios-registrados.component.html',
  styleUrl: './anuncios-registrados.component.css'
})
export class AnunciosRegistradosComponent {

  anuncios: Anuncio[] = [];

  constructor(private anuncioService: AnuncioService, private routingServices: RoutingService) {}

  ngOnInit(): void {
    this.anuncioService.getAllAnuncios().subscribe({
      next: (listado: Anuncio[]) => {
        console.log('Todo fue bien, procesando response...');
        if (listado.length === 0) {
          alert('No Hay Anuncios Comprados');
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
