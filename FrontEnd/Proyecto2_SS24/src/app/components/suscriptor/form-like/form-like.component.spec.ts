import { ComponentFixture, TestBed } from '@angular/core/testing';

import { FormLikeComponent } from './form-like.component';

describe('FormLikeComponent', () => {
  let component: FormLikeComponent;
  let fixture: ComponentFixture<FormLikeComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [FormLikeComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(FormLikeComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
