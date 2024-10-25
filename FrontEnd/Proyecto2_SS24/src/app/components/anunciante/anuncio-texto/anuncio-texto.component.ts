import { Component, Input } from '@angular/core';
import { Anuncio } from '../../../models/anuncio';
import { RouterLink, RouterLinkActive } from '@angular/router';

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

}
