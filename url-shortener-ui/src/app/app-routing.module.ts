import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import {CreateLinkComponent} from "./pages/create-link/create-link.component";

const routes: Routes = [{
  path: '',
  component: CreateLinkComponent,
}];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
