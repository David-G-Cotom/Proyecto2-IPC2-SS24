import { Component, OnInit } from '@angular/core';
import { RevistaComponent } from "../revista/revista.component";
import { Revista } from '../../../models/revista';
import { RevistaService } from '../../../services/revista.service';
import { UsuarioAplicacionJava } from '../../../models/usuarioAplicacionJava';
import { RoutingService } from '../../../services/routing.service';
import { RouterOutlet } from '@angular/router';

@Component({
  selector: 'app-content-editor',
  standalone: true,
  imports: [RevistaComponent],
  templateUrl: './content-editor.component.html',
  styleUrl: './content-editor.component.css'
})
export class ContentEditorComponent implements OnInit{

  revistas: Revista[] = [];
  usuarioLogeado: UsuarioAplicacionJava;

  constructor(private revistaServices: RevistaService, private routingServices: RoutingService) {
    this.usuarioLogeado = JSON.parse(`${localStorage.getItem('Usuario-Actual')}`);
  }
  
  ngOnInit(): void {
    this.revistaServices.getAllRevistasEditor(this.usuarioLogeado.idUsuario).subscribe({
      next: (listado: Revista[]) => {
        console.log('Todo fue bien, procesando response...');
        if (listado.length === 0) {
          alert('No Tienes Revistas Publicadas');
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
