import { Component, Input } from '@angular/core';

import { AuthService } from 'app/auth.service';

@Component({
	selector: 'login',
	templateUrl: 'login.html',
	styleUrls: ['login.css'],
	providers: [ AuthService ]
})
export class LoginComponent {
	constructor(authService: AuthService) { this.authService = authService; }
	authService: AuthService;
	@Input() username: string = '';
	@Input() password: string = '';

	login(): void {
		console.log('Logging in');
		this.authService.login(this.username, this.password);
	}
}
