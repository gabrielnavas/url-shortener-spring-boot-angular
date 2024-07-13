import {Component, Input} from '@angular/core';
import {MatCard, MatCardContent, MatCardFooter} from "@angular/material/card";
import {MatButton} from "@angular/material/button";

import {Clipboard} from '@angular/cdk/clipboard';

@Component({
  selector: 'app-show-url-shortened',
  templateUrl: './show-url-shortened.component.html',
  styleUrl: './show-url-shortened.component.scss',
  standalone: true,
  imports: [
    MatCard,
    MatCardContent,
    MatButton,
    MatCardFooter
  ]
})
export class ShowUrlShortenedComponent {
  @Input()
  urlShortened: string = '';
  linkCopied = false

  constructor(
    private readonly clipboard: Clipboard,
  ) {
  }

  onClickCopyUrl() {
    this.clipboard.copy(this.urlShortened)
    this.linkCopied = true;
  }
}
