import { Component } from '@angular/core';
import { UsuarioAplicacionJava } from '../../../models/usuarioAplicacionJava';
import { FormControl, FormGroup, ReactiveFormsModule } from '@angular/forms';
import { JsonPipe } from '@angular/common';
import { ProfileService } from '../../../services/profile.service';
import { RoutingService } from '../../../services/routing.service';

@Component({
  selector: 'app-perfil-usuario',
  standalone: true,
  imports: [ReactiveFormsModule, JsonPipe],
  templateUrl: './perfil-usuario.component.html',
  styleUrl: './perfil-usuario.component.css'
})
export class PerfilUsuarioComponent {

  usuarioAnterior: UsuarioAplicacionJava;
  usuarioActualizado: UsuarioAplicacionJava | null = null;
  formulario: FormGroup;

  constructor (private perfileService: ProfileService, private routingService: RoutingService) {
    this.usuarioAnterior = JSON.parse(`${localStorage.getItem('Usuario-Actual')}`);
    this.formulario = new FormGroup({
      archivoFoto: new FormControl(this.usuarioAnterior.foto),
      nombre: new FormControl(this.usuarioAnterior.nombre),
      hobbies: new FormControl(this.usuarioAnterior.hobbies),
      temasInteres: new FormControl(this.usuarioAnterior.temasInteres),
      descripcion: new FormControl(this.usuarioAnterior.descripcion),
      gustos: new FormControl(this.usuarioAnterior.gustos)
    });
  }

  public guardarCambios() {
    const archivoFotoControl: FormControl = this.formulario.get('archivoFoto') as FormControl;
    const nombreControl: FormControl = this.formulario.get('nombre') as FormControl;
    const hobbiesControl: FormControl = this.formulario.get('hobbies') as FormControl;
    const temasInteresControl: FormControl = this.formulario.get('temasInteres') as FormControl;
    const descripcionControl: FormControl = this.formulario.get('descripcion') as FormControl;
    const gustosControl: FormControl = this.formulario.get('gustos') as FormControl;
    
    this.usuarioActualizado = new UsuarioAplicacionJava('', hobbiesControl.value, temasInteresControl.value,
      descripcionControl.value, gustosControl.value, this.usuarioAnterior.userName, this.usuarioAnterior.password,
      this.usuarioAnterior.idTipoUsuario, nombreControl.value, this.usuarioAnterior.idUsuario
    );

    this.perfileService.actualizarPerfil(this.usuarioActualizado).subscribe({
      next: (nuevoUsuario: UsuarioAplicacionJava) => {
        console.log('ACTUALIZACION REALIZADA CON EXITO')
        this.perfileService.actualizarLocalStorage(nuevoUsuario);
        this.routingService.redireccionarUsuario();
      }, error: (error: any) => {
        console.log('HUBO ERROR');
        console.log(error);
      }
    })
  }

}
