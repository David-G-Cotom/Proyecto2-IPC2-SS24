import { Component } from '@angular/core';
import { RouterLink, RouterLinkActive } from '@angular/router';
import { AuthService } from '../../../services/auth.service';

@Component({
  selector: 'app-menu-anunciante',
  standalone: true,
  imports: [RouterLink, RouterLinkActive],
  templateUrl: './menu-anunciante.component.html',
  styleUrl: './menu-anunciante.component.css'
})
export class MenuAnuncianteComponent {

  constructor(private authService: AuthService) { }

  public logOut() {
    this.authService.removeStorageItems();
  }

}
