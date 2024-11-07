import { Component, Input } from '@angular/core';
import { Revista } from '../../../models/revista';
import { RouterLink, RouterLinkActive } from '@angular/router';
import { CommonModule } from '@angular/common';
import { SuscriptorService } from '../../../services/suscriptor.service';
import { RoutingService } from '../../../services/routing.service';
import { UsuarioAplicacionJava } from '../../../models/usuarioAplicacionJava';
import { Like } from '../../../models/like';

@Component({
  selector: 'app-revista',
  standalone: true,
  imports: [RouterLink, RouterLinkActive, CommonModule],
  templateUrl: './revista.component.html',
  styleUrl: './revista.component.css'
})
export class RevistaComponent {

  @Input({required: true})
  revista!: Revista;

}
