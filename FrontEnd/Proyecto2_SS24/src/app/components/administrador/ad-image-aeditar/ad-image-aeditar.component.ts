import { Component, Input } from '@angular/core';
import { Anuncio } from '../../../models/anuncio';
import { ImagenService } from '../../../services/imagen.service';
import { FormGroup, ReactiveFormsModule } from '@angular/forms';
import { AnuncioService } from '../../../services/anuncio.service';
import { RoutingService } from '../../../services/routing.service';

@Component({
  selector: 'app-ad-image-aeditar',
  standalone: true,
  imports: [ReactiveFormsModule],
  templateUrl: './ad-image-aeditar.component.html',
  styleUrl: './ad-image-aeditar.component.css'
})
export class AdImageAEditarComponent {

  @Input({required: true})
  anuncio!: Anuncio;

  imagenSrc!: string;

  formulario: FormGroup;

  constructor(private imageService: ImagenService,
              private anuncioService: AnuncioService,
              private routingServices: RoutingService
  ) {
    this.formulario = new FormGroup({});
  }

  ngOnInit(): void {
    this.imageService.getImagenAnuncio(this.anuncio.idAnuncioEspecifico).subscribe({
      next: (imagen: Blob) => {
        const url = window.URL.createObjectURL(imagen);
        this.imagenSrc = url;
      }
    });
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
