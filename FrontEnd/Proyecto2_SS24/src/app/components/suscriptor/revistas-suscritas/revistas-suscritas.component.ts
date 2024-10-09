import { Component } from '@angular/core';
import { RevistaComponent } from "../revista/revista.component";

@Component({
  selector: 'app-revistas-suscritas',
  standalone: true,
  imports: [RevistaComponent],
  templateUrl: './revistas-suscritas.component.html',
  styleUrl: './revistas-suscritas.component.css'
})
export class RevistasSuscritasComponent {

}
