import { ComponentFixture, TestBed } from '@angular/core/testing';

import { FormCompraImageAdComponent } from './form-compra-image-ad.component';

describe('FormCompraImageAdComponent', () => {
  let component: FormCompraImageAdComponent;
  let fixture: ComponentFixture<FormCompraImageAdComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [FormCompraImageAdComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(FormCompraImageAdComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
