import { CommonModule, formatDate } from '@angular/common';
import { Component } from '@angular/core';
import { FormControl, FormGroup, ReactiveFormsModule, Validators } from '@angular/forms';
import { UsuarioAplicacionJava } from '../../../models/usuarioAplicacionJava';
import { RoutingService } from '../../../services/routing.service';
import { SuscriptorService } from '../../../services/suscriptor.service';
import { ActivatedRoute } from '@angular/router';
import { Like } from '../../../models/like';

@Component({
  selector: 'app-form-like',
  standalone: true,
  imports: [ReactiveFormsModule, CommonModule],
  templateUrl: './form-like.component.html',
  styleUrl: './form-like.component.css'
})
export class FormLikeComponent {

  formulario: FormGroup;
  revistaID!: string | null;
  usuarioLogeado: UsuarioAplicacionJava;
  errorDatos: boolean = false;
  mensajeErro: string = '';

  constructor(private routingServices: RoutingService,
              private suscriptorService: SuscriptorService,
              private routParam: ActivatedRoute) {
    this.routParam.paramMap.subscribe(paramMap => {
      this.revistaID = paramMap.get('id');
    });
    this.formulario = new FormGroup({
      fechaLike: new FormControl('', Validators.required)
    });
    this.usuarioLogeado = JSON.parse(`${localStorage.getItem('Usuario-Actual')}`);
  }

  public darLike() {
    if (this.formulario.valid) {
      const fechaControl: FormControl = this.formulario.get('fechaLike') as FormControl;
      let fecha: string = fechaControl.value;
      if (fechaControl.value === '') {
        fecha = formatDate(new Date(), 'yyyy-MM-dd', 'en-US');
      }
      let like = new Like(this.usuarioLogeado.idUsuario, fecha, parseInt(this.revistaID!));
      this.suscriptorService.registrarLike(like).subscribe({
        next: (nuevoLike: any) => {
          console.log(nuevoLike);
          if (nuevoLike.mensaje === 'exito') {
            alert('LIKE REALIZADO CON EXITO');
            this.errorDatos = false;
            this.routingServices.redireccionarUsuario();
          } else if (nuevoLike.mensaje === 'error') {
            alert('No se pudo hacer like, vuelva mas tarde');
          } else {
            this.errorDatos = true;
            this.mensajeErro = nuevoLike.mensaje;
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
