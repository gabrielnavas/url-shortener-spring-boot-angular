import {NgModule} from '@angular/core';
import {BrowserModule} from '@angular/platform-browser';

import {AppRoutingModule} from './app-routing.module';
import {AppComponent} from './app.component';
import {provideAnimationsAsync} from '@angular/platform-browser/animations/async';
import {HeaderComponent} from "./components/header/header.component";
import {HttpClientModule} from "@angular/common/http";
import {ClipboardModule} from "@angular/cdk/clipboard";
import {UrlResolverComponent} from './pages/url-resolver/url-resolver.component';
import {MatCard, MatCardContent} from "@angular/material/card";
import {MatProgressSpinner} from "@angular/material/progress-spinner";

@NgModule({
  declarations: [
    AppComponent,
    UrlResolverComponent,
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    HeaderComponent,
    HttpClientModule,
    ClipboardModule,
    MatCard,
    MatCardContent,
    MatProgressSpinner
  ],
  providers: [
    provideAnimationsAsync()
  ],
  exports: [],
  bootstrap: [AppComponent]
})
export class AppModule {
}
