import { ComponentFixture, TestBed } from '@angular/core/testing';

import { FormCompraVideoAdComponent } from './form-compra-video-ad.component';

describe('FormCompraVideoAdComponent', () => {
  let component: FormCompraVideoAdComponent;
  let fixture: ComponentFixture<FormCompraVideoAdComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [FormCompraVideoAdComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(FormCompraVideoAdComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
