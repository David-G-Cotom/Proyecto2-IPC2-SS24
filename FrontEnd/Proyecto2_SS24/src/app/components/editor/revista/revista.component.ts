import { Component, Input } from '@angular/core';
import { RouterLink, RouterLinkActive, RouterOutlet } from '@angular/router';
import { Revista } from '../../../models/revista';

@Component({
  selector: 'app-revista',
  standalone: true,
  imports: [RouterLink, RouterLinkActive],
  templateUrl: './revista.component.html',
  styleUrl: './revista.component.css'
})
export class RevistaComponent {

  @Input({required: true})
  revista!: Revista;

}
