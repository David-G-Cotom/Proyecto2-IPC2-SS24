import { Component, OnInit } from '@angular/core';
import { UsuarioAplicacionJava } from '../../../models/usuarioAplicacionJava';
import { ActivatedRoute } from '@angular/router';
import { ProfileService } from '../../../services/profile.service';
import { ImagenService } from '../../../services/imagen.service';

@Component({
  selector: 'app-perfil-editor',
  standalone: true,
  imports: [],
  templateUrl: './perfil-editor.component.html',
  styleUrl: './perfil-editor.component.css'
})
export class PerfilEditorComponent implements OnInit{

  perfilEditor!: UsuarioAplicacionJava;
  idEditor!: string | null;
  fotoSrc!: string;

  constructor(private routParam: ActivatedRoute,
              private profileService: ProfileService,
              private imageService: ImagenService) {
    this.routParam.paramMap.subscribe(paramMap => {
      this.idEditor = paramMap.get('id');
    });
    this.getPerfil();
  }

  ngOnInit(): void {
    if (this.idEditor != null) {
      this.imageService.getImagenPerfilEditor(parseInt(this.idEditor)).subscribe({
        next: (imagen: Blob) => {
          const url = window.URL.createObjectURL(imagen);
          this.fotoSrc = url;
        }
      });
    }
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
