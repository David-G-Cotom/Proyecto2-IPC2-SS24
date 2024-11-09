import { CommonModule } from '@angular/common';
import { Component } from '@angular/core';
import { FormControl, FormGroup, ReactiveFormsModule } from '@angular/forms';
import { RoutingService } from '../../../services/routing.service';
import { AdministradorService } from '../../../services/administrador.service';
import { ContenidoReporteRevistaComentada } from '../../../models/contenidoReporteRevistasComentadas';
import { ReporteRevistaComentada } from '../../../models/reporteRevistasComentadas';

@Component({
  selector: 'app-reporte-revistas-comentadas',
  standalone: true,
  imports: [ReactiveFormsModule, CommonModule],
  templateUrl: './reporte-revistas-comentadas.component.html',
  styleUrl: './reporte-revistas-comentadas.component.css'
})
export class ReporteRevistasComentadasComponent {

  formulario: FormGroup;
  errorDatos: boolean = false;
  mensajeErro: string = '';
  contenidoReporte: ContenidoReporteRevistaComentada[] = [];
  mostrarTabla: boolean;

  constructor(private routingServices: RoutingService, private adminService: AdministradorService) {
    this.formulario = new FormGroup({
    fechaInicio: new FormControl(''),
    fechaFin: new FormControl('')
    });
    this.mostrarTabla = false;
  }

  public hacerBusqueda() {
    const fechaInicioControl: FormControl = this.formulario.get('fechaInicio') as FormControl;
    const fechaFinControl: FormControl = this.formulario.get('fechaFin') as FormControl;
    let datosReporte = new ReporteRevistaComentada(fechaInicioControl.value, fechaFinControl.value);
    this.adminService.getReporteRevistasComentadas(datosReporte).subscribe({
      next: (listado: any) => {
        console.log('Todo fue bien, procesando response...');
        if (listado.mensaje !== 'exito') {
          console.log('Error Datos');
          this.errorDatos = true;
          this.mensajeErro = listado.mensaje;
        } else if (listado.mensaje === 'exito' && listado.contenido.length === 0) {
          alert('No Revistas Populares por Mostrar');
          this.mostrarTabla = false;
          this.errorDatos = false;
          this.routingServices.redireccionarRuta('administrador/home-page/reporte-revistas-populares');
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
