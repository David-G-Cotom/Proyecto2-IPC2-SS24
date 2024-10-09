import { Component } from '@angular/core';
import { MenuSuscriptorComponent } from "../menu-suscriptor/menu-suscriptor.component";
import { ContentSuscriptorComponent } from "../content-suscriptor/content-suscriptor.component";

@Component({
  selector: 'app-home-page-suscriptor',
  standalone: true,
  imports: [MenuSuscriptorComponent, ContentSuscriptorComponent],
  templateUrl: './home-page-suscriptor.component.html',
  styleUrl: './home-page-suscriptor.component.css'
})
export class HomePageSuscriptorComponent {

}
