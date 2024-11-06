import { Component } from '@angular/core';
import { FormControl, FormGroup, ReactiveFormsModule, Validators } from '@angular/forms';
import { RoutingService } from '../../../services/routing.service';
import { SuscriptorService } from '../../../services/suscriptor.service';
import { UsuarioAplicacionJava } from '../../../models/usuarioAplicacionJava';
import { ActivatedRoute } from '@angular/router';
import { Comentario } from '../../../models/comentario';
import { CommonModule, formatDate } from '@angular/common';

@Component({
  selector: 'app-form-comentar',
  standalone: true,
  imports: [ReactiveFormsModule, CommonModule],
  templateUrl: './form-comentar.component.html',
  styleUrl: './form-comentar.component.css'
})
export class FormComentarComponent {

  formulario: FormGroup;
  usuarioLogeado: UsuarioAplicacionJava;
  idRevista!: string | null;
  errorDatos: boolean = false;
  mensajeErro: string = '';

  constructor(private routingServices: RoutingService,
    private suscriptorService: SuscriptorService,
    private routParam: ActivatedRoute) {
    this.formulario = new FormGroup({
      descripcion: new FormControl(''),
      fechaComentario: new FormControl('', Validators.required)
    });
    this.usuarioLogeado = JSON.parse(`${localStorage.getItem('Usuario-Actual')}`);
    this.routParam.paramMap.subscribe(paramMap => {
      this.idRevista = paramMap.get('id');
    });
  }

  public comentar() {
    if (this.formulario.valid) {
      const fechaControl: FormControl = this.formulario.get('fechaComentario') as FormControl;
      let fecha: string = fechaControl.value;
      if (fechaControl.value === '') {
        fecha = formatDate(new Date(), 'yyyy-MM-dd', 'en-US');
      }
      const descripcionControl: FormControl = this.formulario.get('descripcion') as FormControl;
      let comentario = new Comentario(descripcionControl.value, this.usuarioLogeado.idUsuario,
                                    parseInt(this.idRevista!), fecha);
      this.suscriptorService.registrarComentario(comentario).subscribe({
        next: (nuevoComentario: any) => {
          console.log(nuevoComentario);
          if (nuevoComentario.mensaje === 'exito') {
            alert('COMENTARIO REALIZADO CON EXITO');
            this.routingServices.redireccionarRuta('suscriptor/home-page/busqueda');
          } else {
            this.errorDatos = true;
            this.mensajeErro = nuevoComentario.mensaje;
          }
        }, error: (error: any) => {
          console.log('HUBO ERROR');
          console.log(error);
        }
      });
    } else {
      alert('Debe Ingresar una Fecha Valida');
    }
    
  }

}
