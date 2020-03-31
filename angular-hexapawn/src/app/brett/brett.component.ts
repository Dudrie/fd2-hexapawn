import { Component, OnInit } from '@angular/core';
import { Figur, Koordinaten } from '../model/Figur';
import { SpielService } from '../spiel-service/spiel.service';

@Component({
  selector: 'app-brett',
  templateUrl: './brett.component.html',
  styleUrls: ['./brett.component.scss'],
})
export class BrettComponent implements OnInit {
  readonly kacheln = new Array(9).fill(0).map((_, idx) => idx);

  constructor(private spielService: SpielService) {}

  ngOnInit(): void {}

  indexToPosition(index: number): Koordinaten {
    return {
      x: index % 3,
      y: Math.floor(index / 3),
    };
  }

  figurBeiKoordinaten(koordinaten: Koordinaten): Figur | undefined {
    return this.spielService.getFigurAnKoordinaten(koordinaten);
  }
}
