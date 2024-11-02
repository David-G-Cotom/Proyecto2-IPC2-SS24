import { Component, Input } from '@angular/core';
import { Revista } from '../../../models/revista';
import { FormControl, FormGroup, ReactiveFormsModule, Validators } from '@angular/forms';
import { RoutingService } from '../../../services/routing.service';
import { RevistaService } from '../../../services/revista.service';
import { CommonModule } from '@angular/common';

@Component({
  selector: 'app-revista-aeditar',
  standalone: true,
  imports: [ReactiveFormsModule, CommonModule],
  templateUrl: './revista-aeditar.component.html',
  styleUrl: './revista-aeditar.component.css'
})
export class RevistaAEditarComponent {

  @Input({required: true})
  revista!: Revista;

  formulario!: FormGroup;
  errorDatos: boolean = false;
  mensajeErro: string = '';

  constructor(private revistaService: RevistaService,private routingServices: RoutingService) {
    this.formulario = new FormGroup({});
  }

  public editarPrecios() {
    const costoDia: HTMLInputElement = document.getElementById('precio' + this.revista.idRevista) as HTMLInputElement;
    const costoGlobal: HTMLInputElement = document.getElementById('precioGlobal' + this.revista.idRevista) as HTMLInputElement;
    const costoOcultacion: HTMLInputElement = document.getElementById('precioOcultacion' + this.revista.idRevista) as HTMLInputElement;
    let precios: string[] = [costoDia.value, costoGlobal.value, costoOcultacion.value];
    this.revistaService.registrarPreciosNuevos(precios, this.revista.idRevista).subscribe({
      next: (mensaje: any) => {
        console.log(mensaje);
        if (mensaje.mensaje === 'exito') {
          alert('ACTUALIZACION REALIZADA CON EXITO');
          this.errorDatos = false;
          this.routingServices.redireccionarRuta('administrador/home-page/editar-precio-revista');
        } else {
          this.errorDatos = true;
          this.mensajeErro = mensaje.mensaje;
        }
      }, error: (error: any) => {
        console.log('HUBO ERROR');
        console.log(error);
      }
    });
  }

}
