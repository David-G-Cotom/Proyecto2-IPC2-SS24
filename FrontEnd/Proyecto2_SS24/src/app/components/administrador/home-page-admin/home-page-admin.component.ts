import { Component } from '@angular/core';
import { MenuAdminComponent } from "../menu-admin/menu-admin.component";
import { RouterOutlet } from '@angular/router';

@Component({
  selector: 'app-home-page-admin',
  standalone: true,
  imports: [MenuAdminComponent, RouterOutlet],
  templateUrl: './home-page-admin.component.html',
  styleUrl: './home-page-admin.component.css'
})
export class HomePageAdminComponent {

}
