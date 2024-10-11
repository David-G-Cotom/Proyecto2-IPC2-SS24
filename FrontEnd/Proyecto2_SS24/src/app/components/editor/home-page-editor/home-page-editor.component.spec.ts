import { ComponentFixture, TestBed } from '@angular/core/testing';

import { HomePageEditorComponent } from './home-page-editor.component';

describe('HomePageEditorComponent', () => {
  let component: HomePageEditorComponent;
  let fixture: ComponentFixture<HomePageEditorComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [HomePageEditorComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(HomePageEditorComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
