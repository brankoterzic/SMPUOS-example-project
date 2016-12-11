import { Component } from '@angular/core';

import { ProductService, Product } from './product.service';

@Component({
	selector: 'product',
	templateUrl: 'product.html',
	styleUrls: ['product.css'],
	providers: [  ]
})
export class ProductComponent {
	constructor(productService: ProductService) {
		productService.findOne('1').subscribe(response => this.product = response);
	}
	product: Product;
}
