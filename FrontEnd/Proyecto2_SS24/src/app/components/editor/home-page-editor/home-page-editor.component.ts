import { Component } from '@angular/core';
import { MenuEditorComponent } from "../menu-editor/menu-editor.component";
import { ContentEditorComponent } from "../content-editor/content-editor.component";
import { RouterOutlet } from '@angular/router';

@Component({
  selector: 'app-home-page-editor',
  standalone: true,
  imports: [MenuEditorComponent, ContentEditorComponent, RouterOutlet],
  templateUrl: './home-page-editor.component.html',
  styleUrl: './home-page-editor.component.css'
})
export class HomePageEditorComponent {

}
