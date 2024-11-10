import { inject } from '@angular/core';
import { CanActivateFn, Router } from '@angular/router';
import { jwtDecode } from 'jwt-decode';

export const authAnuncianteGuard: CanActivateFn = (route, state) => {
  
  const token: string | null = sessionStorage.getItem('token');
  if (token) {
    const decodeToken: any = jwtDecode(token);
    console.log('ROL en Token:' + decodeToken.ROL);
    if (decodeToken.ROL === 3) {
      return true
    }
  }
  const router = inject(Router);
  router.navigate(['/inicio-sesion']);
  return false;

};
