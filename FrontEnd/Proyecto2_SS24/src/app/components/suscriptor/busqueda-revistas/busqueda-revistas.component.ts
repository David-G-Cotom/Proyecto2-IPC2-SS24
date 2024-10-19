import { Component } from '@angular/core';
import { FormGroup, ReactiveFormsModule } from '@angular/forms';
import { UsuarioAplicacionJava } from '../../../models/usuarioAplicacionJava';
import { RoutingService } from '../../../services/routing.service';
import { FiltroBusquedaRevista } from '../../../models/filtroBusquedaRevista';
import { RevistaService } from '../../../services/revista.service';
import { Revista } from '../../../models/revista';
import { RevistaComponent } from '../revista/revista.component';
import { CommonModule } from '@angular/common';

@Component({
  selector: 'app-busqueda-revistas',
  standalone: true,
  imports: [ReactiveFormsModule, RevistaComponent, CommonModule],
  templateUrl: './busqueda-revistas.component.html',
  styleUrl: './busqueda-revistas.component.css'
})
export class BusquedaRevistasComponent {

  formulario: FormGroup;
  etiquetas: string[];
  categorias: string[];
  usuarioLogeado: UsuarioAplicacionJava;
  revistas!: Revista[];
  mostrarRevistas: boolean;

  constructor(private routingServices: RoutingService, private revistaService: RevistaService) {
    this.formulario = new FormGroup({});
    this.etiquetas = [];
    this.categorias = [];
    this.usuarioLogeado = JSON.parse(`${localStorage.getItem('Usuario-Actual')}`);
    this.mostrarRevistas = false;
  }

  public agregarEtiqueta(valorEtiqueta: string) {
    let posicionEtiqueta = this.etiquetas.indexOf(valorEtiqueta);
    if (posicionEtiqueta === -1) {
      this.etiquetas.push(valorEtiqueta);
    } else {
      this.etiquetas.splice(posicionEtiqueta, 1);
    }
  }

  public agregarCategoria(valorCategoria: string) {
    let posicionCategoria = this.categorias.indexOf(valorCategoria);
    if (posicionCategoria === -1) {
      this.categorias.push(valorCategoria);
    } else {
      this.categorias.splice(posicionCategoria, 1);
    }
  }

  public hacerBusqueda() {
    let filtro = new FiltroBusquedaRevista(this.etiquetas, this.categorias, this.usuarioLogeado.idUsuario);
    this.revistaService.getRevistasSuscritas(filtro).subscribe({
      next: (listado: Revista[]) => {
        console.log('Todo fue bien, procesando response...');
        if (listado.length === 0) {
          alert('No hay Revistas por Mostrar');
          this.mostrarRevistas = false;
          this.routingServices.redireccionarRuta('suscriptor/home-page/busqueda');
        } else {
          this.revistas = listado;
          this.mostrarRevistas = true;
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
