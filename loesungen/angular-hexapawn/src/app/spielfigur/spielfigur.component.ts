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
  @Input() anklickbar?: boolean;

  constructor() {}

  ngOnInit(): void {}

  getStyle() {
    return {
      color: this.getFarbe(),
      cursor: this.anklickbar ? 'pointer' : 'default',
    };
  }

  private getFarbe(): string {
    if (this.hervorheben) {
      return 'orange';
    }

    return this.farbe === Spielerfarbe.BLAU ? 'blue' : 'red';
  }
}
