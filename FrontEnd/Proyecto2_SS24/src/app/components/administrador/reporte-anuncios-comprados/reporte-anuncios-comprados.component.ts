import { Component } from '@angular/core';
import { FormControl, FormGroup, ReactiveFormsModule } from '@angular/forms';
import { RoutingService } from '../../../services/routing.service';
import { CommonModule } from '@angular/common';
import { ContenidoReporteCompraAnuncio } from '../../../models/contenidoReporteCompraAnuncios';
import { ReporteCompraAnuncio } from '../../../models/reporteCompraAnuncios';
import { AdministradorService } from '../../../services/administrador.service';

@Component({
  selector: 'app-reporte-anuncios-comprados',
  standalone: true,
  imports: [ReactiveFormsModule, CommonModule],
  templateUrl: './reporte-anuncios-comprados.component.html',
  styleUrl: './reporte-anuncios-comprados.component.css'
})
export class ReporteAnunciosCompradosComponent {

  formulario: FormGroup;
  errorDatos: boolean = false;
  mensajeErro: string = '';
  contenidoReporte: ContenidoReporteCompraAnuncio[] = [];
  mostrarTabla: boolean;

  constructor(private routingServices: RoutingService, private adminService: AdministradorService) {
    this.formulario = new FormGroup({
    fechaInicio: new FormControl(''),
    fechaFin: new FormControl(''),
    tipoAnuncio: new FormControl(''),
    periodoTiempo : new FormControl('')
    });
    this.mostrarTabla = false;
  }

  public hacerBusqueda() {
    const fechaInicioControl: FormControl = this.formulario.get('fechaInicio') as FormControl;
    const fechaFinControl: FormControl = this.formulario.get('fechaFin') as FormControl;
    const tipoAnuncioControl: FormControl = this.formulario.get('tipoAnuncio') as FormControl;
    const periodoTiempoControl: FormControl = this.formulario.get('periodoTiempo') as FormControl;

    let datosReporte = new ReporteCompraAnuncio(fechaInicioControl.value, fechaFinControl.value,
                            tipoAnuncioControl.value, periodoTiempoControl.value)
    
    this.adminService.getReporteAnunciosComprados(datosReporte).subscribe({
      next: (listado: any) => {
        console.log('Todo fue bien, procesando response...');
        if (listado.mensaje !== 'exito') {
          console.log('Error Datos');
          this.errorDatos = true;
          this.mensajeErro = listado.mensaje;
        } else if (listado.mensaje === 'exito' && listado.contenido.length === 0) {
          alert('No hay Anuncios Comprados por Mostrar');
          this.mostrarTabla = false;
          this.errorDatos = false;
          this.routingServices.redireccionarRuta('administrador/home-page/reporte-compra-anuncios');
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
