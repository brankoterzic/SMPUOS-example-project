var insertDefaultDataUser = function(db){
    db.user.insert([
        {
            "firstName" : "Test",
            "lastName": "Test",
            "userName": "user",
			"password": "user",
			"mail": "test@mail.com",
			"age": 23,
			"active": true,
			"dateOfBirth": "1991-01-01T00:00:00.000Z"
        }
    ]);

};