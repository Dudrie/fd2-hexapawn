import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { SpielfigurComponent } from './spielfigur.component';

describe('SpielfigurComponent', () => {
  let component: SpielfigurComponent;
  let fixture: ComponentFixture<SpielfigurComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [SpielfigurComponent],
    }).compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(SpielfigurComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
