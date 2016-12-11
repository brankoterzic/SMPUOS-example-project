import { Injectable } from '@angular/core';

import { Observable } from 'rxjs/Observable';
import 'rxjs/add/observable/of';
import 'rxjs/add/operator/do';
import 'rxjs/add/operator/delay';

import { UserService, User } from 'app/user/user.service';

@Injectable()
export class AuthService {
	constructor(private userService: UserService) { }
	
	private user: User;

	// store the URL so we can redirect after logging in
	redirectUrl: string;

	public login(email, password): Observable<User> {
		return this.userService.login(email, password)
			.map(user => {
				user ? this.user = user : null;
				return this.user;
			});
	}

	public logout(): void {
		this.user = null;
	}
}