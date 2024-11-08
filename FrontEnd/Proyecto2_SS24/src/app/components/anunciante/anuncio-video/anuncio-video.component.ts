import { Component, Input, OnInit } from '@angular/core';
import { Anuncio } from '../../../models/anuncio';
import { RouterLink, RouterLinkActive } from '@angular/router';
import { ImagenService } from '../../../services/imagen.service';
import { AnuncioService } from '../../../services/anuncio.service';
import { RoutingService } from '../../../services/routing.service';

@Component({
  selector: 'app-anuncio-video',
  standalone: true,
  imports: [RouterLink, RouterLinkActive],
  templateUrl: './anuncio-video.component.html',
  styleUrl: './anuncio-video.component.css'
})
export class AnuncioVideoComponent implements OnInit{

  @Input({required: true})
  anuncio!: Anuncio;

  videoSrc!: string;

  constructor(private imageService: ImagenService,
              private anuncioService: AnuncioService,
              private routingServices: RoutingService) {}

  ngOnInit(): void {
    this.imageService.getVideoAnuncio(this.anuncio.idAnuncioEspecifico).subscribe({
      next: (imagen: Blob) => {
        const url = window.URL.createObjectURL(imagen);
        this.videoSrc = url;
      }
    });
  }

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
