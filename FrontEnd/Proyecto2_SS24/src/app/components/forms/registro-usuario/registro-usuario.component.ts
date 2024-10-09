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

  constructor(private authService:AuthService, private routingService: RoutingService) {
    this.formulario = new FormGroup({
      userName: new FormControl('', Validators.required),
      password: new FormControl('', Validators.required),
      nombre: new FormControl('', Validators.required),
      tipoUsuario: new FormControl('', Validators.required),
      archivoFoto: new FormControl('', Validators.required)
    });
  }

  obtenerImagen(event: Event) {
    const file = (event.target as HTMLInputElement).files;
    if (file != null) {
      this.foto = file.item(0);
    }
  }

  registrar() {
    if (this.formulario.valid) {
      const userNameControl: FormControl = this.formulario.get('userName') as FormControl;
      const passwordControl: FormControl = this.formulario.get('password') as FormControl;
      const nombreControl: FormControl = this.formulario.get('nombre') as FormControl;
      const tipoUsuarioControl: FormControl = this.formulario.get('tipoUsuario') as FormControl;
      if (this.foto != null) {
        let userAplication = new UsuarioAplicacion(new User(userNameControl.value, passwordControl.value, tipoUsuarioControl.value), new Perfil("", nombreControl.value));
        this.authService.registrarUsuario(userAplication, this.foto).subscribe((nuevoUsuario: UsuarioAplicacion) => {
          if (nuevoUsuario) {
            console.log("DENTRO DE NUEVO USUARIO? SI");
            this.authService.setLocalStorageItem(nuevoUsuario.user);
            this.routingService.redireccionarUsuario();
          } else {
            console.log("DENTRO DE NUEVO USUARIO? NO");
            this.errorDatos = true;
            this.formulario.reset();
          }
        }, error => {
          this.routingService.enviarPagina("page-not-found")
          console.log(error);
        });
      }
    }
  }

}
