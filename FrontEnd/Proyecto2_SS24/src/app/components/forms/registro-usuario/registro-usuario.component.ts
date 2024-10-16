import { CommonModule, JsonPipe } from '@angular/common';
import { Component } from '@angular/core';
import { FormControl, FormGroup, ReactiveFormsModule, Validators } from '@angular/forms';
import { UsuarioAplicacion } from '../../../models/usuarioAplicacion';
import { User } from '../../../models/user';
import { Perfil } from '../../../models/perfil';
import { AuthService } from '../../../services/auth.service';
import { RoutingService } from '../../../services/routing.service';

@Component({
  selector: 'app-registro-usuario',
  standalone: true,
  imports: [ReactiveFormsModule, CommonModule, JsonPipe],
  templateUrl: './registro-usuario.component.html',
  styleUrl: './registro-usuario.component.css'
})
export class RegistroUsuarioComponent {

  formulario: FormGroup;
  foto: File | null = null;
  errorDatos: boolean = false;
  mensajeErro: string = '';

  constructor(private authService: AuthService, private routingService: RoutingService) {
    this.formulario = new FormGroup({
      userName: new FormControl('', Validators.required),
      password: new FormControl('', Validators.required),
      nombre: new FormControl('', Validators.required),
      tipoUsuario: new FormControl('', Validators.required),
      archivoFoto: new FormControl('', Validators.required)
    });
  }

  public obtenerImagen(event: Event) {
    const file = (event.target as HTMLInputElement).files;
    if (file != null) {
      this.foto = file.item(0);
    }
  }

  public registrar() {
    if (this.formulario.valid) {
      const userNameControl: FormControl = this.formulario.get('userName') as FormControl;
      const passwordControl: FormControl = this.formulario.get('password') as FormControl;
      const nombreControl: FormControl = this.formulario.get('nombre') as FormControl;
      const tipoUsuarioControl: FormControl = this.formulario.get('tipoUsuario') as FormControl;
      if (this.foto != null) {
        let userAplication = new UsuarioAplicacion(new User(userNameControl.value, passwordControl.value, tipoUsuarioControl.value), new Perfil("", nombreControl.value));
        this.authService.registrarUsuario(userAplication, this.foto).subscribe({
          next: (nuevoUsuario: any) => {
            console.log(nuevoUsuario);
            if (nuevoUsuario.mensaje === 'nuevo') {
              this.authService.setLocalStorageItem(nuevoUsuario.usuario);
              this.authService.setSessionStorage(nuevoUsuario.token);
              this.routingService.redireccionarUsuario();
            } else {
              this.errorDatos = true;
              this.mensajeErro = nuevoUsuario.mensaje;
            }
          }, error: (error: any) => {
            this.routingService.enviarPagina('page-not-found');
            console.log('HUBO ERROR');
            console.log(error);
          }
        });
      }
    } else {
      alert("Debe Completar TODOS los campos");
    }
  }

}
