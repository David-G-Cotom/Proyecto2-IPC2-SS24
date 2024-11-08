import { Component, Input } from '@angular/core';
import { Anuncio } from '../../../models/anuncio';
import { ImagenService } from '../../../services/imagen.service';

@Component({
  selector: 'app-publicidad-ad-image',
  standalone: true,
  imports: [],
  templateUrl: './publicidad-ad-image.component.html',
  styleUrl: './publicidad-ad-image.component.css'
})
export class PublicidadAdImageComponent {

  @Input({required: true})
  anuncio!: Anuncio;

  imagenSrc!: string;

  constructor(private imageService: ImagenService) {}

  ngOnInit(): void {
    this.imageService.getImagenAnuncio(this.anuncio.idAnuncioEspecifico).subscribe({
      next: (imagen: Blob) => {
        const url = window.URL.createObjectURL(imagen);
        this.imagenSrc = url;
      }
    });
  }

}
