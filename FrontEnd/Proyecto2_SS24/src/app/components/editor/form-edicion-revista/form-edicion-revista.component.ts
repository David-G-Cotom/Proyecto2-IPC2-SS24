import { Component } from '@angular/core';
import { FormControl, FormGroup, ReactiveFormsModule } from '@angular/forms';
import { RevistaService } from '../../../services/revista.service';
import { RoutingService } from '../../../services/routing.service';
import { Revista } from '../../../models/revista';

@Component({
  selector: 'app-form-edicion-revista',
  standalone: true,
  imports: [ReactiveFormsModule],
  templateUrl: './form-edicion-revista.component.html',
  styleUrl: './form-edicion-revista.component.css'
})
export class FormEdicionRevistaComponent {

  formulario: FormGroup;

  constructor(private revistaServices: RevistaService, private routingServices: RoutingService) {
    this.formulario = new FormGroup({
      nombre: new FormControl(''),
      descripcion: new FormControl(''),
      categoria: new FormControl(''),
      etiqutas: new FormControl(''),
      puedeRecibirComentarios: new FormControl(''),
      puedeRecibirLikes: new FormControl(''),
      puedeRecibirSuscripciones: new FormControl('')
    });
  }

  public editarRevista() {
    const nombreControl: FormControl = this.formulario.get('nombre') as FormControl;
    const descripcionControl: FormControl = this.formulario.get('descripcion') as FormControl;
    const categoriaControl: FormControl = this.formulario.get('categoria') as FormControl;
    const etiqutasControl: FormControl = this.formulario.get('etiqutas') as FormControl;
    const puedeRecibirComentariosControl: FormControl = this.formulario.get('puedeRecibirComentarios') as FormControl;
    const puedeRecibirLikesControl: FormControl = this.formulario.get('puedeRecibirLikes') as FormControl;
    const puedeRecibirSuscripcionesControl: FormControl = this.formulario.get('puedeRecibirSuscripciones') as FormControl;

    const puedeRecibirComentarios: boolean = (puedeRecibirComentariosControl.value === 'SI'? true : false);
    const puedeRecibirLikes: boolean = (puedeRecibirLikesControl.value === 'SI' ? true : false);
    const puedeRecibirSuscripciones: boolean = (puedeRecibirSuscripcionesControl.value === 'SI' ? true : false);
    
    let revista = new Revista(puedeRecibirComentarios, puedeRecibirLikes, puedeRecibirSuscripciones, descripcionControl.value,
      categoriaControl.value, etiqutasControl.value, 0, 0, [''], 0, '', [''], nombreControl.value, 0, 0)

    this.revistaServices.actualizarRevista(revista).subscribe({
      next: (nuevaRevista: Revista) => {
        console.log('REVISTA ACTUALIZADA CON EXITO');
        console.log(nuevaRevista);
        this.routingServices.redireccionarUsuario;
      }, error: (error: any) => {
        console.log('HUBO ERROR');
        console.log(error);
      }
    });
  }

}
