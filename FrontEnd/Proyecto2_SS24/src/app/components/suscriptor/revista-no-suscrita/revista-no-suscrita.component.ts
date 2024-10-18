import { Component, Input } from '@angular/core';
import { Revista } from '../../../models/revista';
import { RouterLink, RouterLinkActive } from '@angular/router';

@Component({
  selector: 'app-revista-no-suscrita',
  standalone: true,
  imports: [RouterLink, RouterLinkActive],
  templateUrl: './revista-no-suscrita.component.html',
  styleUrl: './revista-no-suscrita.component.css'
})
export class RevistaNoSuscritaComponent {

  @Input({required: true})
  revista!: Revista;

}
