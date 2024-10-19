import { ComponentFixture, TestBed } from '@angular/core/testing';

import { FormComentarComponent } from './form-comentar.component';

describe('FormComentarComponent', () => {
  let component: FormComentarComponent;
  let fixture: ComponentFixture<FormComentarComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [FormComentarComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(FormComentarComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
