import { CommonModule, JsonPipe } from '@angular/common';
import { Component } from '@angular/core';
import { FormControl, FormGroup, ReactiveFormsModule, Validators } from '@angular/forms';
import { User } from '../../../models/user';
import { AuthService } from '../../../services/auth.service';
import { RoutingService } from '../../../services/routing.service';

@Component({
  selector: 'app-inicio-sesion',
  standalone: true,
  imports: [ReactiveFormsModule, JsonPipe, CommonModule],
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
  };

  ingresar() {
    if (this.formulario.valid) {
      const userNameControl: FormControl = this.formulario.get('userName') as FormControl;
      this.user.userName = userNameControl.value;
      const passwordControl: FormControl = this.formulario.get('password') as FormControl;
      this.user.password = passwordControl.value;

      console.log("UserName enviado: " + this.user.userName + ", Password enviado: " + this.user.password);

      this.authService.validarInicioSesion(this.user).subscribe((existeUsuario: User) => {
        if (existeUsuario) {
          this.authService.setLocalStorageItem(existeUsuario);
          this.routingService.redireccionarUsuario();
        } else {
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
