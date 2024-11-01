import { Component } from '@angular/core';
import { AnuncioService } from '../../../services/anuncio.service';
import { FormControl, FormGroup, ReactiveFormsModule, Validators } from '@angular/forms';
import { CommonModule } from '@angular/common';
import { RoutingService } from '../../../services/routing.service';

@Component({
  selector: 'app-edicion-precios-tipo-anuncio',
  standalone: true,
  imports: [ReactiveFormsModule, CommonModule],
  templateUrl: './edicion-precios-tipo-anuncio.component.html',
  styleUrl: './edicion-precios-tipo-anuncio.component.css'
})
export class EdicionPreciosTipoAnuncioComponent {

  preciosAnteriores: number[] = [];
  formulario!: FormGroup;
  errorDatos: boolean = false;
  mensajeErro: string = '';

  constructor(private anuncioService: AnuncioService, private routingServices: RoutingService) {
    this.anuncioService.getrPreciosTipoAnuncio().subscribe({
      next: (precios: number[]) => {
        console.log('Todo fue bien, procesando response...');
        this.preciosAnteriores = precios;
        this.formulario = new FormGroup({
          precioTexto: new FormControl(this.preciosAnteriores[0], Validators.required),
          precioImagen : new FormControl(this.preciosAnteriores[1], Validators.required),
          precioVideo: new FormControl(this.preciosAnteriores[2], Validators.required)
        });
      },
      error: (error: any) => {
        console.log(error);
      }
    });
  }

  public editarPrecios() {
    if (this.formulario.valid) {
      const precioTextoControl: FormControl = this.formulario.get('precioTexto') as FormControl;
      const precioImagenControl: FormControl = this.formulario.get('precioImagen') as FormControl;
      const precioVideoControl: FormControl = this.formulario.get('precioVideo') as FormControl;
      let precios: string[] = [precioTextoControl.value, precioImagenControl.value, precioVideoControl.value];
      this.anuncioService.registrarPreciosTipoAnuncio(precios).subscribe({
        next: (mensaje: any) => {
          console.log(mensaje);
          if (mensaje.mensaje === 'exito') {
            alert('ACTUALIZACION REALIZADA CON EXITO');
            this.routingServices.redireccionarRuta('administrador/home-page');
          } else {
            this.errorDatos = true;
            this.mensajeErro = mensaje.mensaje;
          }
        }, error: (error: any) => {
          console.log('HUBO ERROR');
          console.log(error);
        }
      });
    } else {
      alert('No puede Dejar Campos Vacios');
    }
  }

}
