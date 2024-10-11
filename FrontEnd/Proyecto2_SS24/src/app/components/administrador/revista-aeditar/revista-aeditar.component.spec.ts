import { ComponentFixture, TestBed } from '@angular/core/testing';

import { RevistaAEditarComponent } from './revista-aeditar.component';

describe('RevistaAEditarComponent', () => {
  let component: RevistaAEditarComponent;
  let fixture: ComponentFixture<RevistaAEditarComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [RevistaAEditarComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(RevistaAEditarComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
