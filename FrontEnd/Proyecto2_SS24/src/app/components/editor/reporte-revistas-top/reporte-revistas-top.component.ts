import { CommonModule } from '@angular/common';
import { Component } from '@angular/core';
import { FormControl, FormGroup, ReactiveFormsModule } from '@angular/forms';
import { UsuarioAplicacionJava } from '../../../models/usuarioAplicacionJava';
import { ContenidoReporteRevistasTop } from '../../../models/contenidoReporteRevistasTop';
import { RevistaService } from '../../../services/revista.service';
import { EditorService } from '../../../services/editor.service';
import { RoutingService } from '../../../services/routing.service';
import { ReporteRevistasTop } from '../../../models/reporteRevistasTop';

@Component({
  selector: 'app-reporte-revistas-top',
  standalone: true,
  imports: [ReactiveFormsModule, CommonModule],
  templateUrl: './reporte-revistas-top.component.html',
  styleUrl: './reporte-revistas-top.component.css'
})
export class ReporteRevistasTopComponent {

  formulario: FormGroup;
  usuarioLogeado: UsuarioAplicacionJava;
  errorDatos: boolean = false;
  mensajeErro: string = '';
  cantidadRevistas: string[] = [];
  contenidoReporte: ContenidoReporteRevistasTop[] = [];
  mostrarTabla: boolean;

  constructor(private revistaService: RevistaService,
              private editorService: EditorService,
              private routingServices: RoutingService) {
    this.usuarioLogeado = JSON.parse(`${localStorage.getItem('Usuario-Actual')}`);
    this.revistaService.getCantidadRevistasEditor(this.usuarioLogeado.idUsuario).subscribe({
      next: (cantidad: string[]) => {
      this.cantidadRevistas = cantidad;
      },
      error: (error: any) => {
      console.log(error);
      }
    });
    this.formulario = new FormGroup({
      fechaInicio: new FormControl(''),
      fechaFin: new FormControl(''),
      revista: new FormControl('')
    });
    this.mostrarTabla = false;
  }

  public hacerBusqueda() {
    const fechaInicioControl: FormControl = this.formulario.get('fechaInicio') as FormControl;
    const fechaFinControl: FormControl = this.formulario.get('fechaFin') as FormControl;
    const idRevistaControl: FormControl = this.formulario.get('revista') as FormControl;
    let datosReporteRevistasTop = new ReporteRevistasTop(fechaInicioControl.value, fechaFinControl.value,
      idRevistaControl.value, this.usuarioLogeado.idUsuario);
    this.editorService.getReporteRevistasTop(datosReporteRevistasTop).subscribe({
      next: (listado: any) => {
        console.log('Todo fue bien, procesando response...');
        if (listado.mensaje !== 'exito') {
          console.log('Error Datos');
          this.errorDatos = true;
          this.mensajeErro = listado.mensaje;
        } else if (listado.mensaje === 'exito' && listado.contenido.length === 0) {
          alert('No hay Likes por Mostrar');
          this.mostrarTabla = false;
          this.errorDatos = false;
          this.routingServices.redireccionarRuta('editor/home-page/reporte-revistas-top');
        } else {
          console.log('Exito');
          this.contenidoReporte = listado.contenido;
          console.log(this.contenidoReporte);
          this.mostrarTabla = true;
          this.errorDatos = false;
        }
      },
      error: (error: any) => {
        console.log(error);
      }
    });
  }

}
