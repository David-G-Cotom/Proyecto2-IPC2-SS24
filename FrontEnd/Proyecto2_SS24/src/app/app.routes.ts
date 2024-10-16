import { Routes } from '@angular/router';
import { HomePageComponent } from './components/home/home-page/home-page.component';
import { InicioSesionComponent } from './components/forms/inicio-sesion/inicio-sesion.component';
import { RegistroUsuarioComponent } from './components/forms/registro-usuario/registro-usuario.component';
import { PageNotFoundComponent } from './components/page-not-found/page-not-found.component';
import { HomePageEditorComponent } from './components/editor/home-page-editor/home-page-editor.component';
import { HomePageSuscriptorComponent } from './components/suscriptor/home-page-suscriptor/home-page-suscriptor.component';
import { HomePageAnuncianteComponent } from './components/anunciante/home-page-anunciante/home-page-anunciante.component';
import { HomePageAdminComponent } from './components/administrador/home-page-admin/home-page-admin.component';
import { PerfilUsuarioComponent } from './components/forms/perfil-usuario/perfil-usuario.component';
import { FormNuevaPublicacionComponent } from './components/editor/form-nueva-publicacion/form-nueva-publicacion.component';
import { FormNuevaRevistaComponent } from './components/editor/form-nueva-revista/form-nueva-revista.component';
import { FormEdicionRevistaComponent } from './components/editor/form-edicion-revista/form-edicion-revista.component';
import { FormRecargaCreditoComponent } from './components/editor/form-recarga-credito/form-recarga-credito.component';

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
    component: HomePageEditorComponent,
    children: [
      {
        path: 'perfil',
        component: PerfilUsuarioComponent
      },
      {
        path: 'nueva-revista',
        component: FormNuevaRevistaComponent
      },
      {
        path: 'nueva-publicacion',
        component: FormNuevaPublicacionComponent
      },
      {
        path: 'editar-revista',
        component: FormEdicionRevistaComponent
      },
      {
        path: 'recargar-credito',
        component: FormRecargaCreditoComponent
      }
    ]
  },
  {
    path: 'suscriptor/home-page',
    component: HomePageSuscriptorComponent,
    children: [
      {
        path: 'perfil',
        component: PerfilUsuarioComponent
      }
    ]
  },
  {
    path: 'anunciante/home-page',
    component: HomePageAnuncianteComponent,
    children: [
      {
        path: 'perfil',
        component: PerfilUsuarioComponent
      }
    ]
  },
  {
    path: 'administrador/home-page',
    component: HomePageAdminComponent,
    children: [
      {
        path: 'perfil',
        component: PerfilUsuarioComponent
      }
    ]
  },
  {
    path: '**',
    component: PageNotFoundComponent
  }
];
