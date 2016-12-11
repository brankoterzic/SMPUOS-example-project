import { Component } from '@angular/core';

import { ProductService, Product } from './product.service';

@Component({
	selector: 'productsOverview',
	templateUrl: 'productsOverview.html',
	providers: [ ProductService ]
})
export class ProductsOverviewComponent {
	constructor(productService: ProductService) {
		productService.findAll().subscribe(products => this.products = products);
	}
	products: Product[]

}
