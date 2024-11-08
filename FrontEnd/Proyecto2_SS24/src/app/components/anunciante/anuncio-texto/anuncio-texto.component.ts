import { Component, Input } from '@angular/core';
import { Anuncio } from '../../../models/anuncio';
import { RouterLink, RouterLinkActive } from '@angular/router';
import { AnuncioService } from '../../../services/anuncio.service';
import { RoutingService } from '../../../services/routing.service';

@Component({
  selector: 'app-anuncio-texto',
  standalone: true,
  imports: [RouterLink, RouterLinkActive],
  templateUrl: './anuncio-texto.component.html',
  styleUrl: './anuncio-texto.component.css'
})
export class AnuncioTextoComponent {

  @Input({required: true})
  anuncio!: Anuncio;

  constructor(private anuncioService: AnuncioService, private routingServices: RoutingService) {}

  public eliminarAnuncio() {
    this.anuncioService.deleteAnuncio(this.anuncio.idAnuncio).subscribe({
      next: (mensaje: any) => {
        console.log(mensaje);
        if (mensaje.mensaje === 'exito') {
          alert('ELIMINACION REALIZADA CON EXITO');
        } else if (mensaje.mensaje === 'error') {
          alert('NO SE PUDO REALIZAR LA ELIMINACION, vuelva mas tarde')
        }
        this.routingServices.redireccionarUsuario();
      }, error: (error: any) => {
        console.log('HUBO ERROR');
        console.log(error);
      }
    });
  }

}
