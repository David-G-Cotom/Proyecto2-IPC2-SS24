import { Component } from '@angular/core';
import { FormControl, FormGroup, ReactiveFormsModule } from '@angular/forms';
import { RoutingService } from '../../../services/routing.service';
import { AdministradorService } from '../../../services/administrador.service';
import { CommonModule } from '@angular/common';
import { ContenidoReporteRevistaPopular } from '../../../models/contenidoReporteRevistasPopulares';
import { ReporteRevistaPopular } from '../../../models/reporteRevistasPopulares';

@Component({
  selector: 'app-reporte-revistas-populares',
  standalone: true,
  imports: [ReactiveFormsModule, CommonModule],
  templateUrl: './reporte-revistas-populares.component.html',
  styleUrl: './reporte-revistas-populares.component.css'
})
export class ReporteRevistasPopularesComponent {

  formulario: FormGroup;
  errorDatos: boolean = false;
  mensajeErro: string = '';
  contenidoReporte: ContenidoReporteRevistaPopular[] = [];
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
    let datosReporte = new ReporteRevistaPopular(fechaInicioControl.value, fechaFinControl.value);
    this.adminService.getReporteRevistasPopulares(datosReporte).subscribe({
      next: (listado: any) => {
        console.log('Todo fue bien, procesando response...');
        if (listado.mensaje !== 'exito') {
          console.log('Error Datos');
          this.errorDatos = true;
          this.mensajeErro = listado.mensaje;
        } else if (listado.mensaje === 'exito' && listado.contenido.length === 0) {
          alert('No hay Revistas Populares por Mostrar');
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
