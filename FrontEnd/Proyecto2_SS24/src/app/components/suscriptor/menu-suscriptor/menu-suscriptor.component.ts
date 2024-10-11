import { Component } from '@angular/core';
import { RouterLink, RouterLinkActive } from '@angular/router';
import { AuthService } from '../../../services/auth.service';

@Component({
  selector: 'app-menu-suscriptor',
  standalone: true,
  imports: [RouterLink, RouterLinkActive],
  templateUrl: './menu-suscriptor.component.html',
  styleUrl: './menu-suscriptor.component.css'
})
export class MenuSuscriptorComponent {

  constructor(private authService: AuthService) { }

  public logOut() {
    this.authService.removeLocalStorageItem();
  }

}
