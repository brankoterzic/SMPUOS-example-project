import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';

import { ProductOverviewComponent } from './productsOverview.component';
import { ProductComponent } from './product.component';

const productRoutes: Routes = [
	{
		path: '',
		component: ProductOverviewComponent
	},
	{
		path: 'product',
		component: ProductComponent
	}
];

@NgModule({
	imports: [
		RouterModule.forChild(productRoutes)
	],
	exports: [
		RouterModule
	]
})
export class ProductRoutingModule { }
