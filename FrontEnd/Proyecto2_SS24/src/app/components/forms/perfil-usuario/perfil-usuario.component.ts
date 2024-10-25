import { Component, OnInit } from '@angular/core';
import { UsuarioAplicacionJava } from '../../../models/usuarioAplicacionJava';
import { FormControl, FormGroup, ReactiveFormsModule } from '@angular/forms';
import { JsonPipe } from '@angular/common';
import { ProfileService } from '../../../services/profile.service';
import { RoutingService } from '../../../services/routing.service';
import { ImagenService } from '../../../services/imagen.service';

@Component({
  selector: 'app-perfil-usuario',
  standalone: true,
  imports: [ReactiveFormsModule, JsonPipe],
  templateUrl: './perfil-usuario.component.html',
  styleUrl: './perfil-usuario.component.css'
})
export class PerfilUsuarioComponent implements OnInit {

  usuarioAnterior: UsuarioAplicacionJava;
  usuarioActualizado: UsuarioAplicacionJava | null = null;
  formulario: FormGroup;
  passDecode: string;
  fotoSrc!: string;
  fotoNueva: File | null = null;

  constructor (private perfileService: ProfileService,
              private routingService: RoutingService,
              private imageService: ImagenService) {
    this.usuarioAnterior = JSON.parse(`${localStorage.getItem('Usuario-Actual')}`);
    this.passDecode = atob(this.usuarioAnterior.password);
    this.formulario = new FormGroup({
      nombre: new FormControl(this.usuarioAnterior.nombre),
      hobbies: new FormControl(this.usuarioAnterior.hobbies),
      temasInteres: new FormControl(this.usuarioAnterior.temasInteres),
      descripcion: new FormControl(this.usuarioAnterior.descripcion),
      gustos: new FormControl(this.usuarioAnterior.gustos),
      userName: new FormControl(this.usuarioAnterior.userName),
      password: new FormControl(this.passDecode),
      archivoFotoNueva: new FormControl('')
    });
  }
  
  ngOnInit(): void {
    this.imageService.getImagenPerfil(this.usuarioAnterior.idUsuario).subscribe({
      next: (imagen: Blob) => {
        const url = window.URL.createObjectURL(imagen);
        this.fotoSrc = url;
      }
    });
  }

  public obtenerImagenNueva(event: Event) {
    const file = (event.target as HTMLInputElement).files;
    if (file != null) {
      this.fotoNueva = file.item(0);
    }
  }

  public guardarCambios() {
    const nombreControl: FormControl = this.formulario.get('nombre') as FormControl;
    const hobbiesControl: FormControl = this.formulario.get('hobbies') as FormControl;
    const temasInteresControl: FormControl = this.formulario.get('temasInteres') as FormControl;
    const descripcionControl: FormControl = this.formulario.get('descripcion') as FormControl;
    const gustosControl: FormControl = this.formulario.get('gustos') as FormControl;
    const userNameControl: FormControl = this.formulario.get('userName') as FormControl;
    const passwordControl: FormControl = this.formulario.get('password') as FormControl;
    
    this.usuarioActualizado = new UsuarioAplicacionJava('', hobbiesControl.value, temasInteresControl.value,
      descripcionControl.value, gustosControl.value, this.usuarioAnterior.userName, this.usuarioAnterior.password,
      this.usuarioAnterior.idTipoUsuario, nombreControl.value, this.usuarioAnterior.idUsuario
    );
    this.usuarioActualizado.userName = userNameControl.value;
    this.usuarioActualizado.password = passwordControl.value;

    if (this.fotoNueva != null) {
      this.perfileService.actualizarPerfil1(this.usuarioActualizado, this.fotoNueva).subscribe({
        next: (nuevoUsuario: UsuarioAplicacionJava) => {
          console.log('ACTUALIZACION REALIZADA CON EXITO')
          this.perfileService.actualizarLocalStorage(nuevoUsuario);
          this.routingService.redireccionarUsuario();
        }, error: (error: any) => {
          console.log('HUBO ERROR');
          console.log(error);
        }
      });
    } else {
      this.perfileService.actualizarPerfil2(this.usuarioActualizado).subscribe({
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

}
