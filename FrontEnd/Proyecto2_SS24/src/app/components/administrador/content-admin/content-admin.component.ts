import { Component } from '@angular/core';
import { EdicionPreciosTipoAnuncioComponent } from "../edicion-precios-tipo-anuncio/edicion-precios-tipo-anuncio.component";
import { EdicionPreciosTiempoAnuncioComponent } from "../edicion-precios-tiempo-anuncio/edicion-precios-tiempo-anuncio.component";
import { EdicionPreciosRevistaComponent } from "../edicion-precios-revista/edicion-precios-revista.component";

@Component({
  selector: 'app-content-admin',
  standalone: true,
  imports: [EdicionPreciosTipoAnuncioComponent, EdicionPreciosTiempoAnuncioComponent, EdicionPreciosRevistaComponent],
  templateUrl: './content-admin.component.html',
  styleUrl: './content-admin.component.css'
})
export class ContentAdminComponent {

}
