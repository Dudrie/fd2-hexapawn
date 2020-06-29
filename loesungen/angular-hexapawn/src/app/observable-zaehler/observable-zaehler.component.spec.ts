import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { ObservableZaehlerComponent } from './observable-zaehler.component';

describe('ObservableZaehlerComponent', () => {
  let component: ObservableZaehlerComponent;
  let fixture: ComponentFixture<ObservableZaehlerComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ ObservableZaehlerComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(ObservableZaehlerComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
