import { Routes } from '@angular/router';
import { HomePageComponent } from './components/home/home-page/home-page.component';
import { InicioSesionComponent } from './components/forms/inicio-sesion/inicio-sesion.component';
import { RegistroUsuarioComponent } from './components/forms/registro-usuario/registro-usuario.component';
import { PageNotFoundComponent } from './components/page-not-found/page-not-found.component';
import { HomePageEditorComponent } from './components/editor/home-page-editor/home-page-editor.component';
import { HomePageSuscriptorComponent } from './components/suscriptor/home-page-suscriptor/home-page-suscriptor.component';
import { HomePageAnuncianteComponent } from './components/anunciante/home-page-anunciante/home-page-anunciante.component';
import { HomePageAdminComponent } from './components/administrador/home-page-admin/home-page-admin.component';

export const routes: Routes = [
  {
    path: '',
    pathMatch: 'full',
    redirectTo: 'homepage',
  },
  {
    path: 'homepage',
    component: HomePageComponent
  },
  {
    path: 'inicio-sesion',
    component: InicioSesionComponent
  },
  {
    path: 'registro-usuario',
    component: RegistroUsuarioComponent
  },
  {
    path: 'editor/home-page',
    component: HomePageEditorComponent
  },
  {
    path: 'suscriptor/home-page',
    component: HomePageSuscriptorComponent
  },
  {
    path: 'anunciante/home-page',
    component: HomePageAnuncianteComponent
  },
  {
    path: 'administrador/home-page',
    component: HomePageAdminComponent
  },
  {
    path: '**',
    component: PageNotFoundComponent
  }
];
