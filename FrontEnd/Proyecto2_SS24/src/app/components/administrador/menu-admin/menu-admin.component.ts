import { Component } from '@angular/core';
import { RouterLink, RouterLinkActive } from '@angular/router';
import { AuthService } from '../../../services/auth.service';

@Component({
  selector: 'app-menu-admin',
  standalone: true,
  imports: [RouterLink, RouterLinkActive],
  templateUrl: './menu-admin.component.html',
  styleUrl: './menu-admin.component.css'
})
export class MenuAdminComponent {

  constructor(private authService: AuthService) { }

  public logOut() {
    this.authService.removeStorageItems();
  }

}
