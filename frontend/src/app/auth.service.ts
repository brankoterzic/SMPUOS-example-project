import { Injectable } from '@angular/core';

import { Observable } from 'rxjs/Observable';
import 'rxjs/add/observable/of';
import 'rxjs/add/operator/do';
import 'rxjs/add/operator/delay';

import { UserService, User } from 'app/user/user.service';

@Injectable()
export class AuthService {
	constructor(userService: UserService) { this.userService = userService; }
	private userService: UserService;
	private user: User;

	// store the URL so we can redirect after logging in
	redirectUrl: string;

	public login(username: string, password: string) {
		this.userService.login(username, password)
			.subscribe(user => {
				console.log(user);
				this.user = user;
			});
		// return this.user;
	}

	public logout(): void {
		this.user = null;
	}
}