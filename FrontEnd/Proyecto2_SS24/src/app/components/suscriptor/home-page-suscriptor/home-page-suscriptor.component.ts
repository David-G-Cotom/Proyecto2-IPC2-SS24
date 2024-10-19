import { Component } from '@angular/core';
import { MenuSuscriptorComponent } from "../menu-suscriptor/menu-suscriptor.component";
import { RouterOutlet } from '@angular/router';

@Component({
  selector: 'app-home-page-suscriptor',
  standalone: true,
  imports: [MenuSuscriptorComponent, RouterOutlet],
  templateUrl: './home-page-suscriptor.component.html',
  styleUrl: './home-page-suscriptor.component.css'
})
export class HomePageSuscriptorComponent {

}
