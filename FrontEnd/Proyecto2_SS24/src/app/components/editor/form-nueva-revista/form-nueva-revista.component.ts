import { Component } from '@angular/core';
import { FormControl, FormGroup, NonNullableFormBuilder, ReactiveFormsModule, Validators } from '@angular/forms';
import { Revista } from '../../../models/revista';
import { RevistaService } from '../../../services/revista.service';
import { RoutingService } from '../../../services/routing.service';

@Component({
  selector: 'app-form-nueva-revista',
  standalone: true,
  imports: [ReactiveFormsModule],
  templateUrl: './form-nueva-revista.component.html',
  styleUrl: './form-nueva-revista.component.css'
})
export class FormNuevaRevistaComponent {

  formulario: FormGroup;

  constructor(private revistaServices: RevistaService, private routingServices: RoutingService) {
    this.formulario = new FormGroup({
      nombre: new FormControl('', Validators.required),
      descripcion: new FormControl('', Validators.required),
      fecha: new FormControl('', Validators.required),
      categoria: new FormControl('', Validators.required),
      etiqutas: new FormControl('', Validators.required),
      puedeRecibirComentarios: new FormControl('', Validators.required),
      puedeRecibirLikes: new FormControl('', Validators.required),
      puedeRecibirSuscripciones: new FormControl('', Validators.required)
    });
  }

  public crearRevista() {
    if (this.formulario.valid) {
      const nombreControl: FormControl = this.formulario.get('nombre') as FormControl;
      const descripcionControl: FormControl = this.formulario.get('descripcion') as FormControl;
      const fechaControl: FormControl = this.formulario.get('fecha') as FormControl;
      const categoriaControl: FormControl = this.formulario.get('categoria') as FormControl;
      const etiqutasControl: FormControl = this.formulario.get('etiqutas') as FormControl;
      const puedeRecibirComentariosControl: FormControl = this.formulario.get('puedeRecibirComentarios') as FormControl;
      const puedeRecibirLikesControl: FormControl = this.formulario.get('puedeRecibirLikes') as FormControl;
      const puedeRecibirSuscripcionesControl: FormControl = this.formulario.get('puedeRecibirSuscripciones') as FormControl;

      const puedeRecibirComentarios: boolean = (puedeRecibirComentariosControl.value === 'SI'? true : false);
      const puedeRecibirLikes: boolean = (puedeRecibirLikesControl.value === 'SI' ? true : false);
      const puedeRecibirSuscripciones: boolean = (puedeRecibirSuscripcionesControl.value === 'SI' ? true : false);
      
      let revista = new Revista(puedeRecibirComentarios, puedeRecibirLikes, puedeRecibirSuscripciones, descripcionControl.value,
        categoriaControl.value, etiqutasControl.value, 0, 0, [''], 0, fechaControl.value, [''], nombreControl.value, 0, 0)

      this.revistaServices.registrarRevista(revista).subscribe({
        next: (nuevaRevista: Revista) => {
          console.log('REVISTA CREADA CON EXITO');
          console.log(nuevaRevista);
          this.routingServices.redireccionarUsuario;
        }, error: (error: any) => {
          console.log('HUBO ERROR');
          console.log(error);
        }
      });
    }
  }

}
