import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { ProductComponent } from './product.component';
import { ProductsOverviewComponent } from './productsOverview.component';
import { ProductRoutingModule } from './product-routing';

@NgModule({
	imports: [
		ProductRoutingModule, 
		CommonModule
	],
	declarations: [
		ProductComponent,
		ProductsOverviewComponent
	]
})
export class ProductModule { }
