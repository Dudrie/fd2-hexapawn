import { Component, OnInit, Input } from '@angular/core';
import { Spielerfarbe } from '../model/Spieler';

@Component({
  selector: 'app-spielfigur',
  templateUrl: './spielfigur.component.html',
  styleUrls: ['./spielfigur.component.scss'],
})
export class SpielfigurComponent implements OnInit {
  @Input() farbe!: Spielerfarbe;
  @Input() hervorheben?: boolean;

  constructor() {}

  ngOnInit(): void {}

  getStyle() {
    if (this.hervorheben) {
      return { backgroundColor: 'orange' };
    }

    return this.farbe === Spielerfarbe.BLAU
      ? { backgroundColor: 'blue' }
      : { backgroundColor: 'red' };
  }
}
