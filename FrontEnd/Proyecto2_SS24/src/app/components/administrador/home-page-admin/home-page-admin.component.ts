import { Component } from '@angular/core';
import { MenuAdminComponent } from "../menu-admin/menu-admin.component";
import { ContentAdminComponent } from "../content-admin/content-admin.component";

@Component({
  selector: 'app-home-page-admin',
  standalone: true,
  imports: [MenuAdminComponent, ContentAdminComponent],
  templateUrl: './home-page-admin.component.html',
  styleUrl: './home-page-admin.component.css'
})
export class HomePageAdminComponent {

}
