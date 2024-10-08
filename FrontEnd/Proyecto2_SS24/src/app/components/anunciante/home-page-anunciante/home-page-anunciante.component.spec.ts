import { ComponentFixture, TestBed } from '@angular/core/testing';

import { HomePageAnuncianteComponent } from './home-page-anunciante.component';

describe('HomePageAnuncianteComponent', () => {
  let component: HomePageAnuncianteComponent;
  let fixture: ComponentFixture<HomePageAnuncianteComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [HomePageAnuncianteComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(HomePageAnuncianteComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
