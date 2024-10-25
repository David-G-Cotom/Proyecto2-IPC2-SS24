import { ComponentFixture, TestBed } from '@angular/core/testing';

import { FormEdicionAnuncioComponent } from './form-edicion-anuncio.component';

describe('FormEdicionAnuncioComponent', () => {
  let component: FormEdicionAnuncioComponent;
  let fixture: ComponentFixture<FormEdicionAnuncioComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [FormEdicionAnuncioComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(FormEdicionAnuncioComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
