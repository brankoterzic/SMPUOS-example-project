import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';

import { LoginComponent } from './login.component';
import { LoginRoutingModule } from './login.routing';

@NgModule({
	imports: [
		LoginRoutingModule, 
		CommonModule,
		FormsModule
	],
	declarations: [
		LoginComponent 
	]
})
export class LoginModule { }
