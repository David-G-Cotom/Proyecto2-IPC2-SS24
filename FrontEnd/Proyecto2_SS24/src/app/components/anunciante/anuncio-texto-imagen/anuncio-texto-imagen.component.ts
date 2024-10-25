import { Component, Input, OnInit } from '@angular/core';
import { Anuncio } from '../../../models/anuncio';
import { RouterLink, RouterLinkActive } from '@angular/router';
import { ImagenService } from '../../../services/imagen.service';

@Component({
  selector: 'app-anuncio-texto-imagen',
  standalone: true,
  imports: [RouterLink, RouterLinkActive],
  templateUrl: './anuncio-texto-imagen.component.html',
  styleUrl: './anuncio-texto-imagen.component.css'
})
export class AnuncioTextoImagenComponent implements OnInit{

  @Input({required: true})
  anuncio!: Anuncio;

  imagenSrc!: string;

  constructor(private imageService: ImagenService) { }

  ngOnInit(): void {
    this.imageService.getImagenAnuncio(this.anuncio.idAnuncioEspecifico).subscribe({
      next: (imagen: Blob) => {
        const url = window.URL.createObjectURL(imagen);
        this.imagenSrc = url;
      }
    });
  }

}
