import { Component, Input } from '@angular/core';
import { Anuncio } from '../../../models/anuncio';
import { ImagenService } from '../../../services/imagen.service';

@Component({
  selector: 'app-publicidad-ad-video',
  standalone: true,
  imports: [],
  templateUrl: './publicidad-ad-video.component.html',
  styleUrl: './publicidad-ad-video.component.css'
})
export class PublicidadAdVideoComponent {

  @Input({required: true})
  anuncio!: Anuncio;

  videoSrc!: string;

  constructor(private imageService: ImagenService) {}

  ngOnInit(): void {
    this.imageService.getVideoAnuncio(this.anuncio.idAnuncioEspecifico).subscribe({
      next: (imagen: Blob) => {
        const url = window.URL.createObjectURL(imagen);
        this.videoSrc = url;
      }
    });
  }

}
