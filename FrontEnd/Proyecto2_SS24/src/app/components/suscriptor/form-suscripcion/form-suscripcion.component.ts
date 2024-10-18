import { Component } from '@angular/core';
import { FormControl, FormGroup, ReactiveFormsModule, Validators } from '@angular/forms';
import { UsuarioAplicacionJava } from '../../../models/usuarioAplicacionJava';
import { CommonModule, formatDate, JsonPipe } from '@angular/common';
import { RoutingService } from '../../../services/routing.service';
import { SuscripcionService } from '../../../services/suscripcion.service';
import { Suscripcion } from '../../../models/suscripcion';
import { ActivatedRoute } from '@angular/router';

@Component({
  selector: 'app-form-suscripcion',
  standalone: true,
  imports: [ReactiveFormsModule, CommonModule, JsonPipe],
  templateUrl: './form-suscripcion.component.html',
  styleUrl: './form-suscripcion.component.css'
})
export class FormSuscripcionComponent {

  formulario: FormGroup;
  revistaID!: string | null;
  usuairoLogeado: UsuarioAplicacionJava;
  errorDatos: boolean = false;
  mensajeErro: string = '';

  constructor(private routingServices: RoutingService,
    private suscripcionService: SuscripcionService,
    private routParam: ActivatedRoute) {
    this.routParam.paramMap.subscribe(paramMap => {
      this.revistaID = paramMap.get('id');
    });
    this.formulario = new FormGroup({
      fechaSuscripcion: new FormControl('', Validators.required)
    });
    this.usuairoLogeado = JSON.parse(`${localStorage.getItem('Usuario-Actual')}`);
  }

  public suscribirse() {
    if (this.formulario.valid) {
      const fechaControl: FormControl = this.formulario.get('fechaSuscripcion') as FormControl;
      let fecha: string = fechaControl.value;
      if (fechaControl.value === '') {
        fecha = formatDate(new Date(), 'yyyy-MM-dd', 'en-US');
      }
      let suscripcion = new Suscripcion(this.usuairoLogeado.idUsuario, fecha, parseInt(this.revistaID!));
      this.suscripcionService.registrarSuscripcion(suscripcion).subscribe({
        next: (nuevaSuscripcion: any) => {
          console.log(nuevaSuscripcion);
          if (nuevaSuscripcion.mensaje === 'exito') {
            alert('SUSCRIPCION REALIZADA CON EXITO');
            this.routingServices.redireccionarRuta('suscriptor/home-page/suscribir');
          } else {
            this.errorDatos = true;
            this.mensajeErro = nuevaSuscripcion.mensaje;
          }
        }, error: (error: any) => {
          console.log('HUBO ERROR');
          console.log(error);
        }
      });
    } else {
      alert('Debe Ingresar una Fecha valida');
    }
  }

}
