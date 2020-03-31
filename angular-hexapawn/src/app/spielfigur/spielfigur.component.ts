import { Component, OnInit, Input } from '@angular/core';
import { Spielerfarbe } from '../model/Spieler';

@Component({
  selector: 'app-spielfigur',
  templateUrl: './spielfigur.component.html',
  styleUrls: ['./spielfigur.component.scss'],
})
export class SpielfigurComponent implements OnInit {
  @Input() farbe!: Spielerfarbe;

  constructor() {}

  ngOnInit(): void {}

  getStyle() {
    return this.farbe === Spielerfarbe.BLAU
      ? { backgroundColor: 'blue' }
      : { backgroundColor: 'red' };
  }
}
