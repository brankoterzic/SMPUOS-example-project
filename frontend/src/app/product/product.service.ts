import { Injectable } from '@angular/core';
import { Http, Response, URLSearchParams } from '@angular/http';
import { Observable } from 'rxjs/Rx';
import 'rxjs/add/operator/map'

export class Product {
	constructor(
		public id: string, 
		public productName: string,
		public productDescription: string,
		public productPrice: number,
		public active: boolean
	){ }
}

@Injectable()
export class ProductService {
	constructor(private http: Http) {}

	private productServiceURL = 'http://localhost:8082/products';

	public findAll() {
		return this.http.get(this.productServiceURL)
			.map((res:Response) => res.json());
	}

	public findOne(id: string) : Observable<Product> {
		return this.http.get(`${this.productServiceURL}/${id}`)
			.map((res:Response) => res.json());
	}
}