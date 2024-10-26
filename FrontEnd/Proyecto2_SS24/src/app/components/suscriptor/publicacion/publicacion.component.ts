import { Component } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { ImagenService } from '../../../services/imagen.service';
import { CommonModule } from '@angular/common';

@Component({
  selector: 'app-publicacion',
  standalone: true,
  imports: [CommonModule],
  templateUrl: './publicacion.component.html',
  styleUrl: './publicacion.component.css'
})
export class PublicacionComponent {

  archivoSrc!: string;
  idPublicacion!: string | null;

  constructor(private routParam: ActivatedRoute, private imageService: ImagenService) {
    this.routParam.paramMap.subscribe(paramMap => {
      this.idPublicacion = paramMap.get('id');
    });
  }

  ngOnInit(): void {
    if (this.idPublicacion != null) {
      this.imageService.getPdfPublicacion(parseInt(this.idPublicacion)).subscribe({
        next: (imagen: Blob) => {
          const url = window.URL.createObjectURL(imagen);
          this.archivoSrc = url;
        }
      });
    }
  }

}
