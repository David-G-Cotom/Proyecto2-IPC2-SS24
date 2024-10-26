import { Component } from '@angular/core';
import { FormControl, FormGroup, ReactiveFormsModule, Validators } from '@angular/forms';
import { Publicacion } from '../../../models/publicacion';
import { RevistaService } from '../../../services/revista.service';
import { RoutingService } from '../../../services/routing.service';
import { CommonModule } from '@angular/common';
import { ActivatedRoute } from '@angular/router';

@Component({
  selector: 'app-form-nueva-publicacion',
  standalone: true,
  imports: [ReactiveFormsModule, CommonModule],
  templateUrl: './form-nueva-publicacion.component.html',
  styleUrl: './form-nueva-publicacion.component.css'
})
export class FormNuevaPublicacionComponent {

  formulario: FormGroup;
  pdf: File | null = null;
  revistaID!: string | null;
  errorDatos: boolean = false;
  mensajeErro: string = '';

  constructor(private revistaServices: RevistaService,
              private routingServices: RoutingService,
              private routParam: ActivatedRoute) {
    this.routParam.paramMap.subscribe(paramMap => {
      this.revistaID = paramMap.get('id');
    });
    this.formulario = new FormGroup({
      numeroPublicacion: new FormControl('', Validators.required),
      pathArchivo: new FormControl('', Validators.required)
    });
  }

  public obtenerArchivo(event: Event) {
    const file = (event.target as HTMLInputElement).files;
    if (file != null) {
      this.pdf = file.item(0);
    }
  }

  public registrar() {
    if (this.formulario.valid) {
      const numeroPublicacion: FormControl = this.formulario.get('numeroPublicacion') as FormControl;
      if (this.pdf != null) {
        let publicacion = new Publicacion(numeroPublicacion.value, this.pdf);
        this.revistaServices.registrarPublicacion(publicacion, this.revistaID!).subscribe({
          next: (nuevaPublicacion: any) => {
            console.log(nuevaPublicacion);
            if (nuevaPublicacion.mensaje === 'exito') {
              alert('PUBLICACION CREADA CON EXITO');
              this.routingServices.redireccionarUsuario();
            } else if (nuevaPublicacion.mensaje  === 'error') {
              alert('ERROR EN EL REGISTRO, vuelva mas tarde');
              this.routingServices.redireccionarUsuario();
            } else {
              this.errorDatos = true;
              this.mensajeErro = nuevaPublicacion.mensaje;
            }
          }, error: (error: any) => {
            console.log('HUBO ERROR');
            console.log(error);
          }
        });
      }
    } else {
      alert('Debe completar TODOS los campos');
    }
  }

}
