import { Component, Input } from '@angular/core';
import { Anuncio } from '../../../models/anuncio';
import { FormGroup, ReactiveFormsModule } from '@angular/forms';
import { AnuncioService } from '../../../services/anuncio.service';
import { RoutingService } from '../../../services/routing.service';

@Component({
  selector: 'app-ad-text-aeditar',
  standalone: true,
  imports: [ReactiveFormsModule],
  templateUrl: './ad-text-aeditar.component.html',
  styleUrl: './ad-text-aeditar.component.css'
})
export class AdTextAEditarComponent {

  @Input({required: true})
  anuncio!: Anuncio;

  formulario: FormGroup;

  constructor(private anuncioService: AnuncioService, private routingServices: RoutingService) {
    this.formulario = new FormGroup({});
  }

  public editarAnuncio() {
    let anuncioNuevo = new Anuncio(0, 0, this.anuncio.isActivo, 0, 0, 0, this.anuncio.idAnuncio,
      '', '', 0, '');

    this.anuncioService.actualizarAnuncioAdmin(anuncioNuevo).subscribe({
      next: (nuevoAnuncio: any) => {
        console.log(nuevoAnuncio);
        if (nuevoAnuncio.mensaje === 'exito') {
          alert('ACTUALIZACION REALIZADA CON EXITO');
          this.routingServices.redireccionarRuta('administrador/home-page/editar-anuncio');
        } else if (nuevoAnuncio.mensaje === 'error') {
          alert('NO SE PUDO REALIZAR LA ACTUALIZACION, vuelva mas tarde')
          this.routingServices.redireccionarUsuario();
        }
      }, error: (error: any) => {
        console.log('HUBO ERROR');
        console.log(error);
      }
    });
  }

  public cambiarEstado() {
    this.anuncio.isActivo = !this.anuncio.isActivo;
    console.log(this.anuncio.isActivo);
    console.log(this.anuncio.idAnuncio);
  }

}
