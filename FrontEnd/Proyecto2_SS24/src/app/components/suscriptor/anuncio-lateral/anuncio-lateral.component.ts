import { Component, Input } from '@angular/core';
import { Anuncio } from '../../../models/anuncio';
import { CommonModule } from '@angular/common';
import { PublicidadAdTextComponent } from '../../publicidad/publicidad-ad-text/publicidad-ad-text.component';
import { PublicidadAdImageComponent } from '../../publicidad/publicidad-ad-image/publicidad-ad-image.component';
import { PublicidadAdVideoComponent } from '../../publicidad/publicidad-ad-video/publicidad-ad-video.component';

@Component({
  selector: 'app-anuncio-lateral',
  standalone: true,
  imports: [CommonModule, PublicidadAdTextComponent, PublicidadAdImageComponent, PublicidadAdVideoComponent],
  templateUrl: './anuncio-lateral.component.html',
  styleUrl: './anuncio-lateral.component.css'
})
export class AnuncioLateralComponent {

  @Input({required: true})
  anuncios!: Anuncio[];

}
