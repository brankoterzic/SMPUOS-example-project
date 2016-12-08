import { Injectable } from '@angular/core';
import { Http, Response, URLSearchParams } from '@angular/http';
import { Observable } from 'rxjs/Rx';
import 'rxjs/add/operator/map'

export class User {
	constructor(
		public id: string, 
		public firstName: string,
		public lastName: string,
		public password: string,
		public mail: string, 
		public age: number,
		public active: boolean,
		public dateOfBirth: Date
	){ }
}

@Injectable()
export class UserService {
	constructor(private http: Http) {}

	private userServiceURL = 'api/user-service/users';

	public findAll() {
		return this.http.get(this.userServiceURL)
			.map((res:Response) => res.json());
	}

	public findOne(id: string) : Observable<User> {
		return this.http.get(`${this.userServiceURL}/${id}`)
			.map((res:Response) => res.json());
	}

	public login(email: string, password: string) : Observable<User> {
		let params: URLSearchParams = new URLSearchParams();
		params.set('mail', email);
		params.set('password', password);

		return this.http.get(`${this.userServiceURL}/login`, { search: params })
			.map((res:Response) => {
				if (res._body){
					return res.json();
				} else {
					return null;
				}
			});
	}
}