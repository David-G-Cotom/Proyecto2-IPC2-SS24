import { Component } from '@angular/core';
import { FormControl, FormGroup, ReactiveFormsModule } from '@angular/forms';
import { RoutingService } from '../../../services/routing.service';
import { SuscriptorService } from '../../../services/suscriptor.service';
import { UsuarioAplicacionJava } from '../../../models/usuarioAplicacionJava';
import { ActivatedRoute } from '@angular/router';
import { Comentario } from '../../../models/comentario';

@Component({
  selector: 'app-form-comentar',
  standalone: true,
  imports: [ReactiveFormsModule],
  templateUrl: './form-comentar.component.html',
  styleUrl: './form-comentar.component.css'
})
export class FormComentarComponent {

  formulario: FormGroup;
  usuarioLogeado: UsuarioAplicacionJava;
  idRevista!: string | null;

  constructor(private routingServices: RoutingService,
    private suscriptorService: SuscriptorService,
    private routParam: ActivatedRoute) {
    this.formulario = new FormGroup({
      descripcion: new FormControl('')
    });
    this.usuarioLogeado = JSON.parse(`${localStorage.getItem('Usuario-Actual')}`);
    this.routParam.paramMap.subscribe(paramMap => {
      this.idRevista = paramMap.get('id');
    });
  }

  public comentar() {
    const descripcionControl: FormControl = this.formulario.get('descripcion') as FormControl;
    let comentario = new Comentario(descripcionControl.value, this.usuarioLogeado.idUsuario, parseInt(this.idRevista!));
    this.suscriptorService.registrarComentario(comentario).subscribe({
      next: (nuevoComentario: any) => {
        console.log(nuevoComentario);
        if (nuevoComentario.mensaje === 'exito') {
          alert('COMENTARIO REALIZADO CON EXITO');
          this.routingServices.redireccionarRuta('suscriptor/home-page/busqueda');
        } else {
          alert('No se pudo publicar el comentario, vuelva mas tarde');
        }
      }, error: (error: any) => {
        console.log('HUBO ERROR');
        console.log(error);
      }
    });
  }

}
