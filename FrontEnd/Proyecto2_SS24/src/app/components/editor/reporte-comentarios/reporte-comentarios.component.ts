import { CommonModule } from '@angular/common';
import { Component } from '@angular/core';
import { Form, FormControl, FormGroup, ReactiveFormsModule, Validators } from '@angular/forms';
import { RevistaService } from '../../../services/revista.service';
import { UsuarioAplicacionJava } from '../../../models/usuarioAplicacionJava';
import { ReporteComentario } from '../../../models/reporteComentarios';
import { EditorService } from '../../../services/editor.service';
import { ContenidoReporteComentario } from '../../../models/contenidoReporteComentarios';
import { RoutingService } from '../../../services/routing.service';

@Component({
  selector: 'app-reporte-comentarios',
  standalone: true,
  imports: [ReactiveFormsModule, CommonModule],
  templateUrl: './reporte-comentarios.component.html',
  styleUrl: './reporte-comentarios.component.css'
})
export class ReporteComentariosComponent {

  formulario: FormGroup;
  usuarioLogeado: UsuarioAplicacionJava;
  errorDatos: boolean = false;
  mensajeErro: string = '';
  cantidadRevistas: string[] = [];
  contenidoReporte: ContenidoReporteComentario[] = [];
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
    let datosReporteComentario = new ReporteComentario(fechaInicioControl.value, fechaFinControl.value,
                                  idRevistaControl.value, this.usuarioLogeado.idUsuario);
    this.editorService.getReporteComentarios(datosReporteComentario).subscribe({
      next: (listado: any) => {
        console.log('Todo fue bien, procesando response...');
        if (listado.mensaje !== 'exito') {
          console.log('Error Datos');
          this.errorDatos = true;
          this.mensajeErro = listado.mensaje;
        } else if (listado.mensaje === 'exito' && listado.contenido.length === 0) {
          alert('No hay Comentarios por Mostrar');
          this.mostrarTabla = false;
          this.routingServices.redireccionarRuta('editor/home-page/reporte-comentarios');
        } else {
          console.log('Exito');
          this.contenidoReporte = listado.contenido;
          console.log(this.contenidoReporte);
          this.mostrarTabla = true;
        }
      },
      error: (error: any) => {
        console.log(error);
      }
    });
  }

}
