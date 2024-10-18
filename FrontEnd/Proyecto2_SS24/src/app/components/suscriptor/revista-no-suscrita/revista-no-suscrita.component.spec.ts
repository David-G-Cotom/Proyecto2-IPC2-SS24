import { ComponentFixture, TestBed } from '@angular/core/testing';

import { RevistaNoSuscritaComponent } from './revista-no-suscrita.component';

describe('RevistaNoSuscritaComponent', () => {
  let component: RevistaNoSuscritaComponent;
  let fixture: ComponentFixture<RevistaNoSuscritaComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [RevistaNoSuscritaComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(RevistaNoSuscritaComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
