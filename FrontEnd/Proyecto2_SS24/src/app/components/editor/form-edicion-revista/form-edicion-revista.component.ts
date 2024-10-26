import { Component } from '@angular/core';
import { FormControl, FormGroup, ReactiveFormsModule } from '@angular/forms';
import { RevistaService } from '../../../services/revista.service';
import { RoutingService } from '../../../services/routing.service';
import { Revista } from '../../../models/revista';
import { ActivatedRoute } from '@angular/router';
import { CommonModule } from '@angular/common';

@Component({
  selector: 'app-form-edicion-revista',
  standalone: true,
  imports: [ReactiveFormsModule, CommonModule],
  templateUrl: './form-edicion-revista.component.html',
  styleUrl: './form-edicion-revista.component.css'
})
export class FormEdicionRevistaComponent {

  formulario: FormGroup;
  revistaID!: string | null;
  revistaAntigua!: Revista;
  estadoComentarios: boolean;
  estadoLikes: boolean;
  estadoSuscripciones: boolean;
  errorDatos: boolean = false;
  mensajeErro: string = '';

  constructor(private revistaServices: RevistaService, private routingServices: RoutingService, private routParam: ActivatedRoute) {
    this.routParam.paramMap.subscribe(paramMap => {
      this.revistaID = paramMap.get('id');
    });
    this.revistaAntigua = JSON.parse(`${localStorage.getItem('Revista'+ this.revistaID)}`);
    this.estadoComentarios = this.revistaAntigua.puedeComentarse;
    this.estadoLikes = this.revistaAntigua.puedeTenerLikes;
    this.estadoSuscripciones = this.revistaAntigua.puedeSuscribirse;
    this.formulario = new FormGroup({
      nombre: new FormControl(this.revistaAntigua.nombre),
      descripcion: new FormControl(this.revistaAntigua.descripcion),
      categoria: new FormControl(this.revistaAntigua.categoria),
      puedeRecibirComentarios: new FormControl(this.estadoComentarios),
      puedeRecibirLikes: new FormControl(this.estadoLikes),
      puedeRecibirSuscripciones: new FormControl(this.estadoSuscripciones)
    });
  }

  public editarRevista() {
    const nombreControl: FormControl = this.formulario.get('nombre') as FormControl;
    const descripcionControl: FormControl = this.formulario.get('descripcion') as FormControl;
    const categoriaControl: FormControl = this.formulario.get('categoria') as FormControl;
    
    let revista = new Revista(this.estadoComentarios, this.estadoLikes, this.estadoSuscripciones, descripcionControl.value,
      categoriaControl.value, [''], 0, 0, [''], 0, '', [''], nombreControl.value, parseInt(this.revistaID!), 0, '', 0, false, [])

    this.revistaServices.actualizarRevista(revista).subscribe({
      next: (nuevaRevista: any) => {
        console.log(nuevaRevista);
        if (nuevaRevista.mensaje === 'exito') {
          alert('ACTUALIZACION REALIZADA CON EXITO');
          this.routingServices.redireccionarRuta('editor/home-page/revistas');
        } else if (nuevaRevista.mensaje === 'error') {
          alert('NO SE PUDO REALIZAR LA ACTUALIZACION, vuelva mas tarde')
          this.routingServices.redireccionarRuta('editor/home-page/revistas');
        } else {
          this.errorDatos = true;
          this.mensajeErro = nuevaRevista.mensaje;
        }
      }, error: (error: any) => {
        console.log('HUBO ERROR');
        console.log(error);
      }
    });
  }

  public cambiarEstadoComentarios() {
    this.estadoComentarios = !this.estadoComentarios;
  }

  public cambiarEstadoLikes() {
    this.estadoLikes = !this.estadoLikes;
  }

  public cambiarEstadoSuscripciones() {
    this.estadoSuscripciones = !this.estadoSuscripciones;
  }

}
