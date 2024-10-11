import { Component } from '@angular/core';
import { FormControl, FormGroup, Validators } from '@angular/forms';
import { Recarga } from '../../../models/recarga';
import { EditorService } from '../../../services/editor.service';
import { RoutingService } from '../../../services/routing.service';

@Component({
  selector: 'app-form-recarga-credito',
  standalone: true,
  imports: [],
  templateUrl: './form-recarga-credito.component.html',
  styleUrl: './form-recarga-credito.component.css'
})
export class FormRecargaCreditoComponent {

  formulario: FormGroup;

  constructor(private editorServices: EditorService, private routingServices: RoutingService) {
    this.formulario = new FormGroup({
      cantidad: new FormControl('', Validators.required),
      fecha: new FormControl('', Validators.required),
    });
  }

  public registrarRecarga() {
    if (this.formulario.valid) {
      const cantidadControl: FormControl = this.formulario.get('cantidad') as FormControl;
      const fechaControl: FormControl = this.formulario.get('fecha') as FormControl;
      let recarga = new Recarga(cantidadControl.value, fechaControl.value);
      this.editorServices.registrarRecarga(recarga).subscribe({
        next: (nuevaRecarga: Recarga) => {
          console.log('RECARGA CREADA CON EXITO');
          console.log(nuevaRecarga);
          this.routingServices.redireccionarUsuario;
        }, error: (error: any) => {
          console.log('HUBO ERROR');
          console.log(error);
        }
      });
    }
  }

}
