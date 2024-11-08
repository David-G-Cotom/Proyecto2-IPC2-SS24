import { Component } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { ImagenService } from '../../../services/imagen.service';
import { CommonModule } from '@angular/common';
import { DomSanitizer, SafeResourceUrl } from '@angular/platform-browser';
import { AnuncioLateralComponent } from "../anuncio-lateral/anuncio-lateral.component";
import { Anuncio } from '../../../models/anuncio';
import { AnuncioService } from '../../../services/anuncio.service';

@Component({
  selector: 'app-publicacion',
  standalone: true,
  imports: [CommonModule, AnuncioLateralComponent],
  templateUrl: './publicacion.component.html',
  styleUrl: './publicacion.component.css'
})
export class PublicacionComponent {

  archivoSrc!: SafeResourceUrl;
  idPublicacion!: string | null;
  anuncios!: string | null;
  hayAnuncios: boolean;
  anunciosMostrarIzquierda: Anuncio[] = [];
  anunciosMostrarDeracha: Anuncio[] = [];

  constructor(private routParam: ActivatedRoute,
              private imageService: ImagenService,
              private d: DomSanitizer,
              private anuncioService: AnuncioService) {
    this.routParam.paramMap.subscribe(paramMap => {
      this.idPublicacion = paramMap.get('id');
    });
    this.routParam.paramMap.subscribe(paramMap => {
      this.anuncios = paramMap.get('hayAnuncios');
    });
    if (this.anuncios === 'true') {
      this.hayAnuncios = true;
    } else {
      this.hayAnuncios = false;
    }
  }

  ngOnInit(): void {
    if (this.idPublicacion != null) {
      this.imageService.getPdfPublicacion(parseInt(this.idPublicacion)).subscribe({
        next: (archivo: Blob) => {
          console.log(archivo);
          const url = URL.createObjectURL(archivo);
          this.archivoSrc = this.d.bypassSecurityTrustResourceUrl(url);
        }
      });
    }
    if (this.hayAnuncios != null && this.hayAnuncios) {
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

}
