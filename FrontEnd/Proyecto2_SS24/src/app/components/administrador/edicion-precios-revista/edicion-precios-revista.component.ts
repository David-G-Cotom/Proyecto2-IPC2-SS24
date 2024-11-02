import { Component } from '@angular/core';
import { RevistaAEditarComponent } from "../revista-aeditar/revista-aeditar.component";
import { Revista } from '../../../models/revista';
import { RevistaService } from '../../../services/revista.service';
import { RoutingService } from '../../../services/routing.service';

@Component({
  selector: 'app-edicion-precios-revista',
  standalone: true,
  imports: [RevistaAEditarComponent],
  templateUrl: './edicion-precios-revista.component.html',
  styleUrl: './edicion-precios-revista.component.css'
})
export class EdicionPreciosRevistaComponent {

  revistas: Revista[] = [];

  constructor(private revistaServices: RevistaService, private routingServices: RoutingService) {}

  ngOnInit(): void {
    this.revistaServices.getAllRevistas().subscribe({
      next: (listado: Revista[]) => {
        console.log('Todo fue bien, procesando response...');
        if (listado.length === 0) {
          alert('No Hay Revistas Publicadas');
          this.routingServices.redireccionarUsuario();
        } else {
          this.revistas = listado;
        }
      },
      error: (error: any) => {
        console.log(error);
      }
    });
  }

}
