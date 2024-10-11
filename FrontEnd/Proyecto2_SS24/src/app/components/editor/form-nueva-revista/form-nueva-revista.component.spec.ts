import { ComponentFixture, TestBed } from '@angular/core/testing';

import { FormNuevaRevistaComponent } from './form-nueva-revista.component';

describe('FormNuevaRevistaComponent', () => {
  let component: FormNuevaRevistaComponent;
  let fixture: ComponentFixture<FormNuevaRevistaComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [FormNuevaRevistaComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(FormNuevaRevistaComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
