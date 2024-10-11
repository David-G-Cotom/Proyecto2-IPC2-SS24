import { Component } from '@angular/core';
import { RouterLink, RouterLinkActive } from '@angular/router';

@Component({
  selector: 'app-menu-editor',
  standalone: true,
  imports: [RouterLink, RouterLinkActive],
  templateUrl: './menu-editor.component.html',
  styleUrl: './menu-editor.component.css'
})
export class MenuEditorComponent {

}
