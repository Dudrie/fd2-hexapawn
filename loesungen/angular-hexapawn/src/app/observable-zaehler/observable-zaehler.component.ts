import { Component } from '@angular/core';
import { BehaviorSubject } from 'rxjs';

@Component({
  selector: 'app-observable-zaehler',
  templateUrl: './observable-zaehler.component.html',
  styleUrls: ['./observable-zaehler.component.scss'],
})
export class ObservableZaehlerComponent {
  readonly zaehler$ = new BehaviorSubject<number>(0);

  constructor() {}

  erhoeheZaehler() {
    this.zaehler$.next(this.zaehler$.value + 1);
  }

  verringereZaehler() {
    this.zaehler$.next(this.zaehler$.value - 1);
  }
}
