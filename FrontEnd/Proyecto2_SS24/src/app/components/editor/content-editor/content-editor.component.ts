import { Component } from '@angular/core';
import { RevistaComponent } from "../revista/revista.component";

@Component({
  selector: 'app-content-editor',
  standalone: true,
  imports: [RevistaComponent],
  templateUrl: './content-editor.component.html',
  styleUrl: './content-editor.component.css'
})
export class ContentEditorComponent {

}
