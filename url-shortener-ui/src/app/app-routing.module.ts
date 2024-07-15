import {NgModule} from '@angular/core';
import {RouterModule, Routes} from '@angular/router';
import {CreateLinkComponent} from "./pages/create-link/create-link.component";
import {UrlResolverComponent} from "./pages/url-resolver/url-resolver.component";

const routes: Routes = [{
  path: '',
  component: CreateLinkComponent,
}, {
  path: ':url',
  component: UrlResolverComponent
}];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule {
}
