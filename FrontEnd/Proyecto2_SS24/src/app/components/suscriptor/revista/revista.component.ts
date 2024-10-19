import { Component, Input } from '@angular/core';
import { Revista } from '../../../models/revista';
import { RouterLink, RouterLinkActive } from '@angular/router';
import { CommonModule } from '@angular/common';
import { SuscriptorService } from '../../../services/suscriptor.service';
import { RoutingService } from '../../../services/routing.service';
import { UsuarioAplicacionJava } from '../../../models/usuarioAplicacionJava';
import { Like } from '../../../models/like';

@Component({
  selector: 'app-revista',
  standalone: true,
  imports: [RouterLink, RouterLinkActive, CommonModule],
  templateUrl: './revista.component.html',
  styleUrl: './revista.component.css'
})
export class RevistaComponent {

  @Input({required: true})
  revista!: Revista;

  usuarioLogeado: UsuarioAplicacionJava;

  constructor(private suscriptorService: SuscriptorService, private routingServices: RoutingService) {
    this.usuarioLogeado = JSON.parse(`${localStorage.getItem('Usuario-Actual')}`);
  }

  public darLike() {
    let like = new Like(this.usuarioLogeado.idUsuario, this.revista.idRevista);
    this.suscriptorService.registrarLike(like).subscribe({
      next: (nuevoLike: any) => {
        console.log(nuevoLike);
        if (nuevoLike.mensaje === 'exito') {
          alert('LIKE REALIZADO CON EXITO');
          this.routingServices.redireccionarUsuario();
        } else {
          alert('No se pudo hacer like, vuelva mas tarde');
        }
      }, error: (error: any) => {
        console.log('HUBO ERROR');
        console.log(error);
      }
    });
  }

}
