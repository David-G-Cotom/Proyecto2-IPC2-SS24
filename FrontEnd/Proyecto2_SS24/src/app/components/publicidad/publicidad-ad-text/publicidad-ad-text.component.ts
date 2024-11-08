import { Component, Input } from '@angular/core';
import { Anuncio } from '../../../models/anuncio';

@Component({
  selector: 'app-publicidad-ad-text',
  standalone: true,
  imports: [],
  templateUrl: './publicidad-ad-text.component.html',
  styleUrl: './publicidad-ad-text.component.css'
})
export class PublicidadAdTextComponent {

  @Input({required: true})
  anuncio!: Anuncio;

}
