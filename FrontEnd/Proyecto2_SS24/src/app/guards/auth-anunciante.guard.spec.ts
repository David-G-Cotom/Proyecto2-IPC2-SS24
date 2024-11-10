import { TestBed } from '@angular/core/testing';
import { CanActivateFn } from '@angular/router';

import { authAnuncianteGuard } from './auth-anunciante.guard';

describe('authAnuncianteGuard', () => {
  const executeGuard: CanActivateFn = (...guardParameters) => 
      TestBed.runInInjectionContext(() => authAnuncianteGuard(...guardParameters));

  beforeEach(() => {
    TestBed.configureTestingModule({});
  });

  it('should be created', () => {
    expect(executeGuard).toBeTruthy();
  });
});
