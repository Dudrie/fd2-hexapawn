import { Component, OnInit, Input } from '@angular/core';
import { Figur } from '../model/Figur';

@Component({
  selector: 'app-kachel',
  templateUrl: './kachel.component.html',
  styleUrls: ['./kachel.component.scss'],
})
export class KachelComponent implements OnInit {
  @Input() figur?: Figur;

  constructor() {}

  ngOnInit(): void {}
}
