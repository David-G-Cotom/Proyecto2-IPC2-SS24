import { CommonModule } from '@angular/common';
import { Component } from '@angular/core';
import { FormControl, FormGroup, ReactiveFormsModule } from '@angular/forms';
import { UsuarioAplicacionJava } from '../../../models/usuarioAplicacionJava';
import { RevistaService } from '../../../services/revista.service';
import { EditorService } from '../../../services/editor.service';
import { RoutingService } from '../../../services/routing.service';
import { ContenidoReporteSuscripcion } from '../../../models/contenidoReporteSuscripciones';
import { ReporteSuscripcion } from '../../../models/reporteSuscripciones';

@Component({
  selector: 'app-reporte-suscripciones',
  standalone: true,
  imports: [ReactiveFormsModule, CommonModule],
  templateUrl: './reporte-suscripciones.component.html',
  styleUrl: './reporte-suscripciones.component.css'
})
export class ReporteSuscripcionesComponent {

  formulario: FormGroup;
  usuarioLogeado: UsuarioAplicacionJava;
  errorDatos: boolean = false;
  mensajeErro: string = '';
  cantidadRevistas: string[] = [];
  contenidoReporte: ContenidoReporteSuscripcion[] = [];
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
    let datosReporteSuscriopcion = new ReporteSuscripcion(fechaInicioControl.value, fechaFinControl.value,
                                    idRevistaControl.value, this.usuarioLogeado.idUsuario);
    this.editorService.getReporteSuscripciones(datosReporteSuscriopcion).subscribe({
      next: (listado: any) => {
        console.log('Todo fue bien, procesando response...');
        if (listado.mensaje !== 'exito') {
          console.log('Error Datos');
          this.errorDatos = true;
          this.mensajeErro = listado.mensaje;
        } else if (listado.mensaje === 'exito' && listado.contenido.length === 0) {
          alert('No hay Suscripciones por Mostrar');
          this.mostrarTabla = false;
          this.errorDatos = false;
          this.routingServices.redireccionarRuta('editor/home-page/reporte-suscripciones');
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
