import { CommonModule } from '@angular/common';
import { Component } from '@angular/core';
import { FormControl, FormGroup, ReactiveFormsModule, Validators } from '@angular/forms';
import { Anuncio } from '../../../models/anuncio';
import { AnuncioService } from '../../../services/anuncio.service';
import { RoutingService } from '../../../services/routing.service';
import { ActivatedRoute } from '@angular/router';

@Component({
  selector: 'app-form-edicion-anuncio',
  standalone: true,
  imports: [ReactiveFormsModule, CommonModule],
  templateUrl: './form-edicion-anuncio.component.html',
  styleUrl: './form-edicion-anuncio.component.css'
})
export class FormEdicionAnuncioComponent {

  formulario: FormGroup;
  anuncioID!: string | null;
  anuncioAntiguo: Anuncio;
  estado: boolean;
  errorDatos: boolean = false;
  mensajeErro: string = '';

  constructor(private anuncioService: AnuncioService,
              private routingServices: RoutingService,
              private routParam: ActivatedRoute
  ) {
    this.routParam.paramMap.subscribe(paramMap => {
      this.anuncioID = paramMap.get('id');
    });
    this.anuncioAntiguo = JSON.parse(`${localStorage.getItem('Anuncio'+ this.anuncioID)}`);
    this.estado = this.anuncioAntiguo.isActivo;
    this.formulario = new FormGroup({
      titulo: new FormControl(this.anuncioAntiguo.titulo, Validators.required),
      estadoAnuncio: new FormControl(this.estado)
    });
  }

  public editarAnuncio() {
    if (this.formulario.valid) {
      const tituloControl: FormControl = this.formulario.get('titulo') as FormControl;
    
      let anuncio = new Anuncio(0, 0, this.estado, 0, 0, 0, parseInt(this.anuncioID!),
                                tituloControl.value, '', 0, '', false)

      this.anuncioService.actualizarAnuncioAnunciante(anuncio).subscribe({
        next: (nuevaRevista: any) => {
          console.log(nuevaRevista);
          if (nuevaRevista.mensaje === 'exito') {
            alert('ACTUALIZACION REALIZADA CON EXITO');
            this.routingServices.redireccionarRuta('anunciante/home-page/anuncios');
          } else if (nuevaRevista.mensaje === 'error') {
            alert('NO SE PUDO REALIZAR LA ACTUALIZACION, vuelva mas tarde')
            this.routingServices.redireccionarRuta('anunciante/home-page/anuncios');
          } else {
            this.errorDatos = true;
            this.mensajeErro = nuevaRevista.mensaje;
          }
        }, error: (error: any) => {
          console.log('HUBO ERROR');
          console.log(error);
        }
      });
    } else {
      alert('El campo para el titulo DEBE de ser rellenado');
    }
  }

  public cambiarEstado() {
    this.estado = !this.estado;
  }

}
