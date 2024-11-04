import { Component } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { ImagenService } from '../../../services/imagen.service';
import { CommonModule } from '@angular/common';
import { DomSanitizer, SafeResourceUrl } from '@angular/platform-browser';

@Component({
  selector: 'app-publicacion',
  standalone: true,
  imports: [CommonModule],
  templateUrl: './publicacion.component.html',
  styleUrl: './publicacion.component.css'
})
export class PublicacionComponent {

  archivoSrc!: SafeResourceUrl;
  idPublicacion!: string | null;

  constructor(private routParam: ActivatedRoute,
              private imageService: ImagenService,
              private d: DomSanitizer) {
    this.routParam.paramMap.subscribe(paramMap => {
      this.idPublicacion = paramMap.get('id');
    });
  }

  ngOnInit(): void {
    if (this.idPublicacion != null) {
      this.imageService.getPdfPublicacion(parseInt(this.idPublicacion)).subscribe({
        next: (archivo: Blob) => {
          console.log(archivo);
          const url = URL.createObjectURL(archivo);
          this.archivoSrc = this.d.bypassSecurityTrustResourceUrl(url);
        }
      });
    }
  }

}
