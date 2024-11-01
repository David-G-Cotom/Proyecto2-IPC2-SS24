import { CommonModule } from '@angular/common';
import { Component } from '@angular/core';
import { FormControl, FormGroup, ReactiveFormsModule, Validators } from '@angular/forms';
import { AnuncioService } from '../../../services/anuncio.service';
import { RoutingService } from '../../../services/routing.service';

@Component({
  selector: 'app-edicion-precios-tiempo-anuncio',
  standalone: true,
  imports: [ReactiveFormsModule, CommonModule],
  templateUrl: './edicion-precios-tiempo-anuncio.component.html',
  styleUrl: './edicion-precios-tiempo-anuncio.component.css'
})
export class EdicionPreciosTiempoAnuncioComponent {

  preciosAnteriores: number[] = [];
  formulario!: FormGroup;
  errorDatos: boolean = false;
  mensajeErro: string = '';

  constructor(private anuncioService: AnuncioService, private routingServices: RoutingService) {
    this.anuncioService.getrPreciosTiempoAnuncio().subscribe({
      next: (precios: number[]) => {
        console.log('Todo fue bien, procesando response...');
        this.preciosAnteriores = precios;
        this.formulario = new FormGroup({
          precioDia1: new FormControl(this.preciosAnteriores[0], Validators.required),
          precioDia3 : new FormControl(this.preciosAnteriores[1], Validators.required),
          precioDia7: new FormControl(this.preciosAnteriores[2], Validators.required),
          precioDia14: new FormControl(this.preciosAnteriores[3], Validators.required)
        });
      },
      error: (error: any) => {
        console.log(error);
      }
    });
  }

  public editarPrecios() {
    if (this.formulario.valid) {
      const precioDia1Control: FormControl = this.formulario.get('precioDia1') as FormControl;
      const precioDia3Control: FormControl = this.formulario.get('precioDia3') as FormControl;
      const precioDia7Control: FormControl = this.formulario.get('precioDia7') as FormControl;
      const precioDia14Control: FormControl = this.formulario.get('precioDia14') as FormControl;
      let precios: string[] = [precioDia1Control.value, precioDia3Control.value, precioDia7Control.value, precioDia14Control.value];
      this.anuncioService.registrarPreciosTiempoAnuncio(precios).subscribe({
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
