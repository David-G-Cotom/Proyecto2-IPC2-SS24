import { TestBed } from '@angular/core/testing';
import { CanActivateFn } from '@angular/router';

import { authSuscriptorGuard } from './auth-suscriptor.guard';

describe('authSuscriptorGuard', () => {
  const executeGuard: CanActivateFn = (...guardParameters) => 
      TestBed.runInInjectionContext(() => authSuscriptorGuard(...guardParameters));

  beforeEach(() => {
    TestBed.configureTestingModule({});
  });

  it('should be created', () => {
    expect(executeGuard).toBeTruthy();
  });
});
