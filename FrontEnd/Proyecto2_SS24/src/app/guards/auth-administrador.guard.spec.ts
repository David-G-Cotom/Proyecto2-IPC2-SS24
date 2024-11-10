import { TestBed } from '@angular/core/testing';
import { CanActivateFn } from '@angular/router';

import { authAdministradorGuard } from './auth-administrador.guard';

describe('authAdministradorGuard', () => {
  const executeGuard: CanActivateFn = (...guardParameters) => 
      TestBed.runInInjectionContext(() => authAdministradorGuard(...guardParameters));

  beforeEach(() => {
    TestBed.configureTestingModule({});
  });

  it('should be created', () => {
    expect(executeGuard).toBeTruthy();
  });
});
