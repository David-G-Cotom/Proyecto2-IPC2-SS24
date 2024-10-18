import { Component, Input } from '@angular/core';
import { Revista } from '../../../models/revista';

@Component({
  selector: 'app-revista',
  standalone: true,
  imports: [],
  templateUrl: './revista.component.html',
  styleUrl: './revista.component.css'
})
export class RevistaComponent {

  @Input({required: true})
  revista!: Revista;

}
