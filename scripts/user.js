var insertDefaultDataUser = function(db){
    db.user.insert([
        {
            "firstName" : "user1",
            "lastName": "user1",
            "userName": "user1",
			"password": "user1",
			"mail": "user1@mail.com",
			"age": 23,
			"active": true,
			"dateOfBirth": "1900-01-01T00:00:00.000Z"

        },
		{
            "firstName" : "user2",
            "lastName": "user2",
            "userName": "user2",
			"password": "user2",
			"mail": "user2@mail.com",
			"age": 23,
			"active": true,
			"dateOfBirth": "1900-01-01T00:00:00.000Z"

        },
{
            "firstName" : "user3",
            "lastName": "user3",
            "userName": "user3",
			"password": "user3",
			"mail": "user3@mail.com",
			"age": 23,
			"active": true,
			"dateOfBirth": "1900-01-01T00:00:00.000Z"

        },
		{
            "firstName" : "user4",
            "lastName": "user4",
            "userName": "user4",
			"password": "user4",
			"mail": "user4@mail.com",
			"age": 23,
			"active": true,
			"dateOfBirth": "1900-01-01T00:00:00.000Z"

        }
    ]);

};