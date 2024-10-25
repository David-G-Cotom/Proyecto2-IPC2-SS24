import { Component } from '@angular/core';
import { FormControl, FormGroup, ReactiveFormsModule, Validators } from '@angular/forms';
import { UsuarioAplicacionJava } from '../../../models/usuarioAplicacionJava';
import { RoutingService } from '../../../services/routing.service';
import { AnuncioService } from '../../../services/anuncio.service';
import { CommonModule, formatDate } from '@angular/common';
import { Anuncio } from '../../../models/anuncio';

@Component({
  selector: 'app-form-compra-image-ad',
  standalone: true,
  imports: [ReactiveFormsModule, CommonModule],
  templateUrl: './form-compra-image-ad.component.html',
  styleUrl: './form-compra-image-ad.component.css'
})
export class FormCompraImageAdComponent {

  formulario: FormGroup;
  usuarioLogeado: UsuarioAplicacionJava;
  foto: File | null = null;
  errorDatos: boolean = false;
  mensajeErro: string = '';

  constructor(private routingServices: RoutingService, private anuncioService: AnuncioService) {
    this.formulario = new FormGroup({
      titulo: new FormControl('', Validators.required),
      contenido: new FormControl('', Validators.required),
      fecha: new FormControl('', Validators.required),
      duracion: new FormControl('', Validators.required),
      archivoFoto: new FormControl('', Validators.required)
    });
    this.usuarioLogeado = JSON.parse(`${localStorage.getItem('Usuario-Actual')}`);
  }

  public obtenerImagen(event: Event) {
    const file = (event.target as HTMLInputElement).files;
    if (file != null) {
      this.foto = file.item(0);
    }
  }

  public hacerCompra() {
    if (this.formulario.valid) {
      const tituloControl: FormControl = this.formulario.get('titulo') as FormControl;
      const contenidoControl: FormControl = this.formulario.get('contenido') as FormControl;
      const fechaControl: FormControl = this.formulario.get('fecha') as FormControl;
      const duracionControl: FormControl = this.formulario.get('duracion') as FormControl;

      let fecha: string = fechaControl.value;
      if (fechaControl.value === '') {
        fecha = formatDate(new Date(), 'yyyy-MM-dd', 'en-US');
      }
      let duracion: string = duracionControl.value;
      if (duracion === '') {
        duracion = '0';
      }
      
      if (this.foto != null) {
        let anuncioImagen = new Anuncio(0, parseInt(duracion), true, this.usuarioLogeado.idUsuario,
        0, 2, 0, tituloControl.value, contenidoControl.value, 0, fecha);

        this.anuncioService.registrarCompraAdImage(anuncioImagen, this.foto).subscribe({
          next: (mensajeCreacion: any) => {
            console.log(mensajeCreacion);
            if (mensajeCreacion.mensaje  === 'nuevo') {
              alert('ANUNCIO COMPRADO CON EXITO');
              this.routingServices.redireccionarUsuario();
            } else if (mensajeCreacion.mensaje  === 'error') {
              alert('NO TIENES EL CREDITO SUFICIENTE PARA LA COMPRA');
              this.routingServices.redireccionarUsuario();
            } else {
              this.errorDatos = true;
              this.mensajeErro = mensajeCreacion.mensaje;
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
