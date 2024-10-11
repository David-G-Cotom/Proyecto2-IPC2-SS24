import { Component } from '@angular/core';
import { FormControl, FormGroup, ReactiveFormsModule, Validators } from '@angular/forms';
import { Publicacion } from '../../../models/publicacion';
import { RevistaService } from '../../../services/revista.service';
import { RoutingService } from '../../../services/routing.service';

@Component({
  selector: 'app-form-nueva-publicacion',
  standalone: true,
  imports: [ReactiveFormsModule],
  templateUrl: './form-nueva-publicacion.component.html',
  styleUrl: './form-nueva-publicacion.component.css'
})
export class FormNuevaPublicacionComponent {

  formulario: FormGroup;
  pdf: File | null = null;

  constructor(private revistaServices: RevistaService, private routingServices: RoutingService) {
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
        this.revistaServices.registrarPublicacion(publicacion).subscribe({
          next: (nuevaPublicacion: Publicacion) => {
            console.log('PUBLICACION CREADA CON EXITO');
            console.log(nuevaPublicacion);
            this.routingServices.redireccionarUsuario;
          }, error: (error: any) => {
            console.log('HUBO ERROR');
            console.log(error);
          }
        });
      }
    }
  }

}
