import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { DeudaComponent } from './component/deuda/deuda.component';

const routes: Routes = [
  {path:'deuda', component: DeudaComponent}
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
