import { Component } from '@angular/core';
import { BusquedaRevistasComponent } from "../busqueda-revistas/busqueda-revistas.component";
import { RevistasSuscritasComponent } from "../revistas-suscritas/revistas-suscritas.component";

@Component({
  selector: 'app-content-suscriptor',
  standalone: true,
  imports: [BusquedaRevistasComponent, RevistasSuscritasComponent],
  templateUrl: './content-suscriptor.component.html',
  styleUrl: './content-suscriptor.component.css'
})
export class ContentSuscriptorComponent {

}
