import { Component } from '@angular/core';
import { MenuAnuncianteComponent } from "../menu-anunciante/menu-anunciante.component";
import { ContentAnuncianteComponent } from "../content-anunciante/content-anunciante.component";
import { RouterOutlet } from '@angular/router';

@Component({
  selector: 'app-home-page-anunciante',
  standalone: true,
  imports: [MenuAnuncianteComponent, ContentAnuncianteComponent, RouterOutlet],
  templateUrl: './home-page-anunciante.component.html',
  styleUrl: './home-page-anunciante.component.css'
})
export class HomePageAnuncianteComponent {

}
