import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { HttpModule, JsonpModule } from '@angular/http';

import { AppComponent } from './app.component';
import { AppRoutingModule } from './app-routing';
import { AuthService } from './auth.service';
import { UserService } from './user/user.service';
import { ProductService } from './product/product.service';


@NgModule({
	imports: [ 
		BrowserModule,
		AppRoutingModule,
		HttpModule,
		JsonpModule
	],
	declarations: [ 
		AppComponent 
	],
	bootstrap: [
		AppComponent 
	],
	providers: [
		AuthService, 
		UserService,
		ProductService 
	]
})
export class AppModule { }
