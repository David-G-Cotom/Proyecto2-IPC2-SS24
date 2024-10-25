import { Component, Input, OnInit } from '@angular/core';
import { Anuncio } from '../../../models/anuncio';
import { RouterLink, RouterLinkActive } from '@angular/router';
import { ImagenService } from '../../../services/imagen.service';

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
