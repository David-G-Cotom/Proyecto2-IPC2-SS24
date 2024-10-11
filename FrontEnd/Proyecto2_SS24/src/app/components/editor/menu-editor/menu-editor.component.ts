import { Component } from '@angular/core';
import { RouterLink, RouterLinkActive } from '@angular/router';
import { AuthService } from '../../../services/auth.service';

@Component({
  selector: 'app-menu-editor',
  standalone: true,
  imports: [RouterLink, RouterLinkActive],
  templateUrl: './menu-editor.component.html',
  styleUrl: './menu-editor.component.css'
})
export class MenuEditorComponent {

  constructor(private authService: AuthService) { }

  public logOut() {
    this.authService.removeLocalStorageItem();
  }

}
