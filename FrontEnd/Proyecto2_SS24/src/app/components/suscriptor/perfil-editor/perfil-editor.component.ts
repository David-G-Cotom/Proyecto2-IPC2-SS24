import { Component } from '@angular/core';
import { UsuarioAplicacionJava } from '../../../models/usuarioAplicacionJava';
import { ActivatedRoute } from '@angular/router';
import { ProfileService } from '../../../services/profile.service';

@Component({
  selector: 'app-perfil-editor',
  standalone: true,
  imports: [],
  templateUrl: './perfil-editor.component.html',
  styleUrl: './perfil-editor.component.css'
})
export class PerfilEditorComponent {

  perfilEditor!: UsuarioAplicacionJava;
  idEditor!: string | null;

  constructor(private routParam: ActivatedRoute, private profileService: ProfileService) {
    this.routParam.paramMap.subscribe(paramMap => {
      this.idEditor = paramMap.get('id');
    });
    this.getPerfil();
  }

  private getPerfil() {
    this.profileService.getPerfilEditor(parseInt(this.idEditor!)).subscribe({
      next: (perfil: UsuarioAplicacionJava) => {
        console.log(perfil);
        this.perfilEditor = perfil;
      }, error: (error: any) => {
        console.log('HUBO ERROR');
        console.log(error);
      }
    });
  }

}
