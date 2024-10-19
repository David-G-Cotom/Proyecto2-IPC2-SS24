import { Component } from '@angular/core';
import { FormControl, FormGroup, ReactiveFormsModule, Validators } from '@angular/forms';
import { UsuarioAplicacionJava } from '../../../models/usuarioAplicacionJava';
import { RoutingService } from '../../../services/routing.service';
import { CommonModule, formatDate } from '@angular/common';
import { AnuncioTexto } from '../../../models/anuncioTexto';
import { AnuncioService } from '../../../services/anuncio.service';

@Component({
  selector: 'app-form-compra-text-ad',
  standalone: true,
  imports: [ReactiveFormsModule, CommonModule],
  templateUrl: './form-compra-text-ad.component.html',
  styleUrl: './form-compra-text-ad.component.css'
})
export class FormCompraTextAdComponent {

  formulario: FormGroup;
  usuairoLogeado: UsuarioAplicacionJava;
  errorDatos: boolean = false;
  mensajeErro: string = '';

  constructor(private routingServices: RoutingService, private anuncioService: AnuncioService) {
    this.formulario = new FormGroup({
      titulo: new FormControl('', Validators.required),
      contenido: new FormControl('', Validators.required),
      fecha: new FormControl('', Validators.required),
      duracion: new FormControl('', Validators.required),
    });
    this.usuairoLogeado = JSON.parse(`${localStorage.getItem('Usuario-Actual')}`);
  }

  public hacerCompra() {
    if (this.formulario.valid) {
      const tituloControl: FormControl = this.formulario.get('titulo') as FormControl;
      const contenidoControl: FormControl = this.formulario.get('contenido') as FormControl;
      const fechaControl: FormControl = this.formulario.get('fecha') as FormControl;
      const duracionControl: FormControl = this.formulario.get('duracion') as FormControl;

      let fecha: string = fechaControl.value;
      if (fechaControl.value === '') {
        fecha = formatDate(new Date(), 'yyyy-MM-dd', 'en-US');
      }
      let duracion: string = duracionControl.value;
      if (duracion === '') {
        duracion = '0';
      }
      
      let anuncioTexto = new AnuncioTexto(0, parseInt(duracion), true, this.usuairoLogeado.idUsuario,
        0, 1, 0, tituloControl.value, contenidoControl.value, 0, fecha);

      this.anuncioService.registrarCompraAdText(anuncioTexto).subscribe({
        next: (mensajeCreacion: any) => {
          console.log(mensajeCreacion);
          if (mensajeCreacion.mensaje  === 'nuevo') {
            alert('ANUNCIO CREADO CON EXITO');
            this.routingServices.redireccionarUsuario();
          } else if (mensajeCreacion.mensaje  === 'error') {
            alert('NO TIENS EL CREDITO SUFUCIENTE PARA LA COMPRA');
            this.routingServices.redireccionarUsuario();
          } else {
            this.errorDatos = true;
            this.mensajeErro = mensajeCreacion.mensaje;
          }
        }, error: (error: any) => {
          console.log('HUBO ERROR');
          console.log(error);
        }
      });
    } else {
      alert('Debe completar TODOS los campos');
    }
  }

}
