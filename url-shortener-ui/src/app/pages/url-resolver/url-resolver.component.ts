import {Component} from '@angular/core';
import {ActivatedRoute} from "@angular/router";
import {LinkService} from "../../services/link.service";

@Component({
  selector: 'app-url-resolver',
  templateUrl: './url-resolver.component.html',
  styleUrl: './url-resolver.component.scss'
})
export class UrlResolverComponent {
  constructor(
    private readonly activatedRoute: ActivatedRoute,
    private readonly linkService: LinkService,
  ) {
    const urlShortened = this.activatedRoute.snapshot.params;
    this.redirectToCorrectUrl(urlShortened["url"]);
  }

  redirectToCorrectUrl(urlShortened: string) {
    this.linkService.findUrlByShortened(urlShortened).subscribe({
      next: result => {
        window.location.href = result.urlOriginal;
      }
    })
  }
}
