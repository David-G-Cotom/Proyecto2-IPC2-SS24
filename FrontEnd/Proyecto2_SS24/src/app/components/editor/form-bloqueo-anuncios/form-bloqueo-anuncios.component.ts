import { CommonModule } from '@angular/common';
import { Component } from '@angular/core';
import { FormControl, FormGroup, ReactiveFormsModule, Validators } from '@angular/forms';
import { EditorService } from '../../../services/editor.service';
import { RoutingService } from '../../../services/routing.service';
import { ActivatedRoute } from '@angular/router';

@Component({
  selector: 'app-form-bloqueo-anuncios',
  standalone: true,
  imports: [ReactiveFormsModule, CommonModule],
  templateUrl: './form-bloqueo-anuncios.component.html',
  styleUrl: './form-bloqueo-anuncios.component.css'
})
export class FormBloqueoAnunciosComponent {

  formulario: FormGroup;
  idRevista!: string | null;
  errorDatos: boolean = false;
  mensajeErro: string = '';

  constructor(private editorServices: EditorService,
              private routingServices: RoutingService,
              private routParam: ActivatedRoute) {
      this.routParam.paramMap.subscribe(paramMap => {
        this.idRevista = paramMap.get('id');
      });
      this.formulario = new FormGroup({
      cantidadDias: new FormControl('', Validators.required)
    });
  }

  public registrarBloque() {
    if (this.formulario.valid) {
      const cantidadControl: FormControl = this.formulario.get('cantidadDias') as FormControl;

      this.editorServices.registrarBloqueo(this.idRevista!, cantidadControl.value).subscribe({
        next: (nuevaRecarga: any) => {
          console.log(nuevaRecarga);
          if (nuevaRecarga.mensaje === 'nuevo') {
            alert('CAMBIO REALIZADO CON EXITO');
            this.routingServices.redireccionarRuta('editor/home-page/revistas');
          } else if (nuevaRecarga.mensaje === 'error') {
            alert('DEBE DE RECARGAR CREDITO ANTES DE EJECUTAR LA OPERACION');
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
