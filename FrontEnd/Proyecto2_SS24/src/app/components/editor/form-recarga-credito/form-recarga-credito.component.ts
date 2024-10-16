import { Component } from '@angular/core';
import { FormControl, FormGroup, ReactiveFormsModule, Validators } from '@angular/forms';
import { Recarga } from '../../../models/recarga';
import { EditorService } from '../../../services/editor.service';
import { RoutingService } from '../../../services/routing.service';
import { CommonModule, formatDate, JsonPipe } from '@angular/common';
import { UsuarioAplicacionJava } from '../../../models/usuarioAplicacionJava';

@Component({
  selector: 'app-form-recarga-credito',
  standalone: true,
  imports: [ReactiveFormsModule, JsonPipe, CommonModule],
  templateUrl: './form-recarga-credito.component.html',
  styleUrl: './form-recarga-credito.component.css'
})
export class FormRecargaCreditoComponent {

  formulario: FormGroup;
  usuairoLogeado: UsuarioAplicacionJava;
  errorDatos: boolean = false;
  mensajeErro: string = '';

  constructor(private editorServices: EditorService, private routingServices: RoutingService) {
    this.formulario = new FormGroup({
      cantidad: new FormControl('', Validators.required),
      fecha: new FormControl('', Validators.required),
    });
    this.usuairoLogeado = JSON.parse(`${localStorage.getItem('Usuario-Actual')}`);
  }

  public registrarRecarga() {
    if (this.formulario.valid) {
      const cantidadControl: FormControl = this.formulario.get('cantidad') as FormControl;
      const fechaControl: FormControl = this.formulario.get('fecha') as FormControl;
      
      let fecha: string = fechaControl.value;
      if (fecha === '') {
        fecha = formatDate(new Date(), 'yyyy-MM-dd', 'en-US');
      }

      let recarga = new Recarga(cantidadControl.value, fecha, this.usuairoLogeado.idUsuario, this.usuairoLogeado.idTipoUsuario);

      this.editorServices.registrarRecarga(recarga).subscribe({
        next: (nuevaRecarga: any) => {
          console.log(nuevaRecarga);
          if (nuevaRecarga.mensaje === 'nuevo') {
            alert('RECARGA REALIZADA CON EXITO');
            this.routingServices.redireccionarUsuario();
          } else {
            this.errorDatos = true;
            this.mensajeErro = nuevaRecarga.mensaje;
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
