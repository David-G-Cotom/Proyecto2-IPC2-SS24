import { CommonModule } from '@angular/common';
import { Component } from '@angular/core';
import { FormControl, FormGroup, ReactiveFormsModule, Validators } from '@angular/forms';
import { User } from '../../../models/user';
import { AuthService } from '../../../services/auth.service';
import { RoutingService } from '../../../services/routing.service';
import { RouterLink, RouterLinkActive } from '@angular/router';

@Component({
  selector: 'app-inicio-sesion',
  standalone: true,
  imports: [ReactiveFormsModule, CommonModule, RouterLink, RouterLinkActive],
  templateUrl: './inicio-sesion.component.html',
  styleUrl: './inicio-sesion.component.css'
})
export class InicioSesionComponent {
  
  formulario: FormGroup;
  user: User;
  errorDatos: boolean = false;

  constructor(private authService: AuthService, private routingService: RoutingService) {
    this.formulario = new FormGroup({
      userName: new FormControl('', Validators.required),
      password: new FormControl('', Validators.required)
    }),
    this.user = new User("", "", "")
    authService.removeStorageItems();
  };

  ingresar() {
    if (this.formulario.valid) {
      const userNameControl: FormControl = this.formulario.get('userName') as FormControl;
      this.user.userName = userNameControl.value;
      const passwordControl: FormControl = this.formulario.get('password') as FormControl;
      this.user.password = passwordControl.value;

      this.authService.validarInicioSesion(this.user).subscribe({
        next: (existeUsuario: any) =>{
          if (existeUsuario.mensaje === 'Si hay usuario') {
            console.log("DENTRO DE EXISTE USUARIO");
            this.authService.removeStorageItems();
            this.authService.setLocalStorageItem(existeUsuario.usuario);
            this.authService.setSessionStorage(existeUsuario.token);
            this.routingService.redireccionarUsuario();
            this.errorDatos = false;
          } else if (existeUsuario.mensaje === 'No hay usuario') {
            console.log("DENTRO DE EXISTE USUARIO NO");
            this.errorDatos = true;
            this.formulario.reset();
          }
        }, error: (error: any) => {
          this.routingService.enviarPagina("page-not-found")
          console.log("HUBO ERROR");
          console.log(error);
        }
      });
    } else {
      alert("Debe Completar TODOS los campos");
    }
  }

}
