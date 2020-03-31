import { Component, OnInit, Input } from '@angular/core';
import { Kachel } from '../model/Kachel';

@Component({
  selector: 'app-kachel',
  templateUrl: './kachel.component.html',
  styleUrls: ['./kachel.component.scss'],
})
export class KachelComponent implements OnInit {
  @Input() kachel?: Kachel;
  @Input() hervorheben?: boolean;
  @Input() anklickbar?: boolean;

  constructor() {}

  ngOnInit(): void {}
}
