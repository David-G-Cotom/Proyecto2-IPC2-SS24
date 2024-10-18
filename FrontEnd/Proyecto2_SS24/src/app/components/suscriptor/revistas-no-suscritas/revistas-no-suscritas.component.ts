import { Component, OnInit } from '@angular/core';
import { RevistaNoSuscritaComponent } from '../revista-no-suscrita/revista-no-suscrita.component';
import { Revista } from '../../../models/revista';
import { UsuarioAplicacionJava } from '../../../models/usuarioAplicacionJava';
import { RoutingService } from '../../../services/routing.service';
import { RevistaService } from '../../../services/revista.service';

@Component({
  selector: 'app-revistas-no-suscritas',
  standalone: true,
  imports: [RevistaNoSuscritaComponent],
  templateUrl: './revistas-no-suscritas.component.html',
  styleUrl: './revistas-no-suscritas.component.css'
})
export class RevistasNoSuscritasComponent implements OnInit{

  revistas: Revista[] = [];
  usuarioLogeado: UsuarioAplicacionJava;

  constructor(private revistaServices: RevistaService, private routingServices: RoutingService) {
    this.usuarioLogeado = JSON.parse(`${localStorage.getItem('Usuario-Actual')}`);
  }

  ngOnInit(): void {
    this.revistaServices.getRevistasNOSuscritas(this.usuarioLogeado.idUsuario).subscribe({
      next: (listado: Revista[]) => {
        console.log('Todo fue bien, procesando response...');
        if (listado.length === 0) {
          alert('No hay Revistas por Suscribirse');
          this.routingServices.redireccionarUsuario();
        } else {
          this.revistas = listado;
          for (const revista of this.revistas) {
            localStorage.removeItem(`Revista${revista.idRevista}`);
            localStorage.setItem(`Revista${revista.idRevista}`, JSON.stringify(revista));
          }
        }
      },
      error: (error: any) => {
        console.log(error);
      }
    });
  }

}
