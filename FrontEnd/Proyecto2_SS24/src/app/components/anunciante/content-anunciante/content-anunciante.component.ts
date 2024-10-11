import { Component } from '@angular/core';
import { AnuncioTextoComponent } from "../anuncio-texto/anuncio-texto.component";
import { AnuncioTextoImagenComponent } from "../anuncio-texto-imagen/anuncio-texto-imagen.component";
import { AnuncioVideoComponent } from "../anuncio-video/anuncio-video.component";

@Component({
  selector: 'app-content-anunciante',
  standalone: true,
  imports: [AnuncioTextoComponent, AnuncioTextoImagenComponent, AnuncioVideoComponent],
  templateUrl: './content-anunciante.component.html',
  styleUrl: './content-anunciante.component.css'
})
export class ContentAnuncianteComponent {

}
