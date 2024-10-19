import { ComponentFixture, TestBed } from '@angular/core/testing';

import { PerfilEditorComponent } from './perfil-editor.component';

describe('PerfilEditorComponent', () => {
  let component: PerfilEditorComponent;
  let fixture: ComponentFixture<PerfilEditorComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [PerfilEditorComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(PerfilEditorComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
