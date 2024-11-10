import { TestBed } from '@angular/core/testing';
import { CanActivateFn } from '@angular/router';

import { authEditorGuard } from './auth-editor.guard';

describe('authEditorGuard', () => {
  const executeGuard: CanActivateFn = (...guardParameters) => 
      TestBed.runInInjectionContext(() => authEditorGuard(...guardParameters));

  beforeEach(() => {
    TestBed.configureTestingModule({});
  });

  it('should be created', () => {
    expect(executeGuard).toBeTruthy();
  });
});
