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
import { ContentEditorComponent } from './components/editor/content-editor/content-editor.component';
import { RevistasNoSuscritasComponent } from './components/suscriptor/revistas-no-suscritas/revistas-no-suscritas.component';
import { BusquedaRevistasComponent } from './components/suscriptor/busqueda-revistas/busqueda-revistas.component';
import { FormSuscripcionComponent } from './components/suscriptor/form-suscripcion/form-suscripcion.component';
import { PerfilEditorComponent } from './components/suscriptor/perfil-editor/perfil-editor.component';
import { FormComentarComponent } from './components/suscriptor/form-comentar/form-comentar.component';
import { FormCompraTextAdComponent } from './components/anunciante/form-compra-text-ad/form-compra-text-ad.component';
import { FormCompraImageAdComponent } from './components/anunciante/form-compra-image-ad/form-compra-image-ad.component';
import { FormCompraVideoAdComponent } from './components/anunciante/form-compra-video-ad/form-compra-video-ad.component';
import { ContentAnuncianteComponent } from './components/anunciante/content-anunciante/content-anunciante.component';
import { FormEdicionAnuncioComponent } from './components/anunciante/form-edicion-anuncio/form-edicion-anuncio.component';
import { PublicacionComponent } from './components/suscriptor/publicacion/publicacion.component';
import { EdicionPreciosTipoAnuncioComponent } from './components/administrador/edicion-precios-tipo-anuncio/edicion-precios-tipo-anuncio.component';
import { EdicionPreciosTiempoAnuncioComponent } from './components/administrador/edicion-precios-tiempo-anuncio/edicion-precios-tiempo-anuncio.component';
import { EdicionPreciosRevistaComponent } from './components/administrador/edicion-precios-revista/edicion-precios-revista.component';
import { FormBloqueoAnunciosComponent } from './components/editor/form-bloqueo-anuncios/form-bloqueo-anuncios.component';
import { AnunciosRegistradosComponent } from './components/administrador/anuncios-registrados/anuncios-registrados.component';
import { ReporteComentariosComponent } from './components/editor/reporte-comentarios/reporte-comentarios.component';
import { ReporteSuscripcionesComponent } from './components/editor/reporte-suscripciones/reporte-suscripciones.component';
import { FormLikeComponent } from './components/suscriptor/form-like/form-like.component';
import { ReporteRevistasTopComponent } from './components/editor/reporte-revistas-top/reporte-revistas-top.component';
import { ReporteAnunciosCompradosComponent } from './components/administrador/reporte-anuncios-comprados/reporte-anuncios-comprados.component';
import { ReporteRevistasPopularesComponent } from './components/administrador/reporte-revistas-populares/reporte-revistas-populares.component';
import { ReporteRevistasComentadasComponent } from './components/administrador/reporte-revistas-comentadas/reporte-revistas-comentadas.component';
import { authGuard } from './guards/auth.guard';
import { authAdministradorGuard } from './guards/auth-administrador.guard';
import { authAnuncianteGuard } from './guards/auth-anunciante.guard';
import { authSuscriptorGuard } from './guards/auth-suscriptor.guard';
import { authEditorGuard } from './guards/auth-editor.guard';

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
    canActivate: [ authGuard, authEditorGuard ],
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
        path: 'nueva-publicacion/:id',
        component: FormNuevaPublicacionComponent
      },
      {
        path: 'editar-revista/:id',
        component: FormEdicionRevistaComponent
      },
      {
        path: 'recargar-credito',
        component: FormRecargaCreditoComponent
      },
      {
        path: 'revistas',
        component: ContentEditorComponent,
      },
      {
        path: 'bloquear-anuncios/:id',
        component: FormBloqueoAnunciosComponent
      },
      {
        path: 'reporte-comentarios',
        component: ReporteComentariosComponent
      },
      {
        path: 'reporte-suscripciones',
        component: ReporteSuscripcionesComponent
      },
      {
        path: 'reporte-revistas-top',
        component: ReporteRevistasTopComponent
      }
    ]
  },
  {
    path: 'suscriptor/home-page',
    component: HomePageSuscriptorComponent,
    canActivate: [ authGuard, authSuscriptorGuard ],
    children: [
      {
        path: 'perfil',
        component: PerfilUsuarioComponent
      },
      {
        path: 'suscribir',
        component: RevistasNoSuscritasComponent
      },
      {
        path: 'busqueda',
        component: BusquedaRevistasComponent
      },
      {
        path: 'suscribir/:id',
        component: FormSuscripcionComponent
      },
      {
        path: 'busqueda/perfil-editor/:id',
        component: PerfilEditorComponent
      },
      {
        path: 'busqueda/comentar/:id',
        component: FormComentarComponent
      },
      {
        path: 'busqueda/dar-like/:id',
        component: FormLikeComponent
      }
    ]
  },
  {
    path: 'anunciante/home-page',
    component: HomePageAnuncianteComponent,
    canActivate: [ authGuard, authAnuncianteGuard ],
    children: [
      {
        path: 'perfil',
        component: PerfilUsuarioComponent
      },
      {
        path: 'recargar-credito',
        component: FormRecargaCreditoComponent
      },
      {
        path: 'compra-anuncio-texto',
        component: FormCompraTextAdComponent
      },
      {
        path: 'compra-anuncio-imagen',
        component: FormCompraImageAdComponent
      },
      {
        path: 'compra-anuncio-video',
        component: FormCompraVideoAdComponent
      },
      {
        path: 'anuncios',
        component: ContentAnuncianteComponent
      },
      {
        path: 'editar-anuncio/:id',
        component: FormEdicionAnuncioComponent
      }
    ]
  },
  {
    path: 'administrador/home-page',
    component: HomePageAdminComponent,
    canActivate: [ authGuard, authAdministradorGuard ],
    children: [
      {
        path: 'perfil',
        component: PerfilUsuarioComponent
      },
      {
        path: 'editar-precio-tipo-anuncio',
        component: EdicionPreciosTipoAnuncioComponent
      },
      {
        path: 'editar-precio-periodo-tiempo-anuncio',
        component: EdicionPreciosTiempoAnuncioComponent
      },
      {
        path: 'editar-precio-revista',
        component: EdicionPreciosRevistaComponent
      },
      {
        path: 'editar-anuncio',
        component: AnunciosRegistradosComponent
      },
      {
        path: 'reporte-compra-anuncios',
        component: ReporteAnunciosCompradosComponent
      },
      {
        path: 'reporte-revistas-populares',
        component: ReporteRevistasPopularesComponent
      },
      {
        path: 'reporte-revistas-comentadas',
        component: ReporteRevistasComentadasComponent
      }
    ]
  },
  {
    path: 'publicacion/:id/:hayAnuncios',
    component: PublicacionComponent
  },
  {
    path: '**',
    component: PageNotFoundComponent
  }
];
