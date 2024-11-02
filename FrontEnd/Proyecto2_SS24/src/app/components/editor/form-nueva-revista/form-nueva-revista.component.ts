import { Component } from '@angular/core';
import { FormControl, FormGroup, ReactiveFormsModule, Validators } from '@angular/forms';
import { Revista } from '../../../models/revista';
import { RevistaService } from '../../../services/revista.service';
import { RoutingService } from '../../../services/routing.service';
import { CommonModule, formatDate, JsonPipe } from '@angular/common';
import { UsuarioAplicacionJava } from '../../../models/usuarioAplicacionJava';

@Component({
  selector: 'app-form-nueva-revista',
  standalone: true,
  imports: [ReactiveFormsModule, JsonPipe, CommonModule],
  templateUrl: './form-nueva-revista.component.html',
  styleUrl: './form-nueva-revista.component.css'
})
export class FormNuevaRevistaComponent {

  formulario: FormGroup;
  etiquetas: string[];
  usuairoLogeado: UsuarioAplicacionJava;
  errorDatos: boolean = false;
  mensajeErro: string = '';

  constructor(private revistaServices: RevistaService, private routingServices: RoutingService) {
    this.formulario = new FormGroup({
      nombre: new FormControl('', Validators.required),
      descripcion: new FormControl('', Validators.required),
      fecha: new FormControl('', Validators.required),
      categoria: new FormControl('', Validators.required),
      puedeRecibirComentarios: new FormControl(),
      puedeRecibirLikes: new FormControl(),
      puedeRecibirSuscripciones: new FormControl()
    });
    this.etiquetas = [];
    this.usuairoLogeado = JSON.parse(`${localStorage.getItem('Usuario-Actual')}`);
  }

  public agregarEtiqueta(valorEtiqueta: string) {
    let posicionEtiqueta = this.etiquetas.indexOf(valorEtiqueta);
    if (posicionEtiqueta === -1) {
      this.etiquetas.push(valorEtiqueta);
    } else {
      this.etiquetas.splice(posicionEtiqueta, 1);
    }
  }

  public crearRevista() {
    if (this.formulario.valid) {
      const nombreControl: FormControl = this.formulario.get('nombre') as FormControl;
      const descripcionControl: FormControl = this.formulario.get('descripcion') as FormControl;
      const fechaControl: FormControl = this.formulario.get('fecha') as FormControl;
      const categoriaControl: FormControl = this.formulario.get('categoria') as FormControl;
      const puedeRecibirComentariosControl: FormControl = this.formulario.get('puedeRecibirComentarios') as FormControl;
      const puedeRecibirLikesControl: FormControl = this.formulario.get('puedeRecibirLikes') as FormControl;
      const puedeRecibirSuscripcionesControl: FormControl = this.formulario.get('puedeRecibirSuscripciones') as FormControl;

      let puedeRecibirComentarios: boolean = puedeRecibirComentariosControl.value;
      let puedeRecibirLikes: boolean = puedeRecibirLikesControl.value;
      let puedeRecibirSuscripciones: boolean = puedeRecibirSuscripcionesControl.value;
      let fecha: string = fechaControl.value;
      if (puedeRecibirComentarios === null) {
        puedeRecibirComentarios = false;
      }
      if (puedeRecibirLikes === null) {
        puedeRecibirLikes = false;
      }
      if (puedeRecibirSuscripciones === null) {
        puedeRecibirSuscripciones = false;
      }
      if (fechaControl.value === '') {
        fecha = formatDate(new Date(), 'yyyy-MM-dd', 'en-US');
      }
      
      let revista = new Revista(puedeRecibirComentarios, puedeRecibirLikes, puedeRecibirSuscripciones, descripcionControl.value,
        categoriaControl.value, this.etiquetas, this.usuairoLogeado.idUsuario, 0, [''], 0.0, fecha, [''], nombreControl.value, 0, 0, '', 0, false, [], 0);
      
      this.revistaServices.registrarRevista(revista).subscribe({
        next: (mensajeCreacion: any) => {
          console.log(mensajeCreacion);
          if (mensajeCreacion.mensaje  === 'nuevo') {
            alert('REVISTA CREADA CON EXITO');
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
