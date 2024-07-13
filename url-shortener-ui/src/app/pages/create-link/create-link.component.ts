import {Component} from '@angular/core';
import {LinkResponse} from "../../services/link-response";
import {MatFormField} from "@angular/material/form-field";
import {ShowUrlShortenedComponent} from "../../components/show-url-shortened/show-url-shortened.component";
import {NgIf} from "@angular/common";
import {MatInput} from "@angular/material/input";
import {MatButton} from "@angular/material/button";
import {MatSnackBar} from "@angular/material/snack-bar";
import {LinkService} from "../../services/link.service";
import {FormsModule} from "@angular/forms";
import {Title} from "@angular/platform-browser";

@Component({
  selector: 'app-create-link',
  templateUrl: './create-link.component.html',
  styleUrl: './create-link.component.scss',
  standalone: true,
  imports: [
    MatFormField,
    ShowUrlShortenedComponent,
    NgIf,
    MatInput,
    MatButton,
    FormsModule,
  ]
})
export class CreateLinkComponent {
  linkResponse: LinkResponse | undefined = undefined;
  isLoading = false
  form = {
    url: ''
  }

  constructor(
    private readonly linkService: LinkService,
    private readonly _snackBar: MatSnackBar,
    private title: Title
  ) {
    this.title.setTitle("Create a Link | URL Shortener");
  }

  onClickShortener() {
    this.isLoading = true;
    this._snackBar.open("URL is performing!")
    this.linkService.createLink({
      body: {
        url: this.form.url,
      }
    }).subscribe({
      next: linkResponse => {
        this.linkResponse = linkResponse;
        this.isLoading = false;
        this._snackBar.dismiss();
      },
      error: err => {
        this._snackBar.open(err.error.errors[0]);
        this.isLoading = false;
      },
    })
  }
}
