var insertDefaultDataProduct = function(db){
    db.product.insert([
        {
            "productName" : "product1",
            "productDescription": "Product1",
            "productPrice": 123.0,
			"active": true
        },
		{
            "productName" : "product2",
            "productDescription": "Product2",
            "productPrice": 123.4,
			"active": true
        },
		{
            "productName" : "produc3",
            "productDescription": "Product3",
            "productPrice": 125.0,
			"active": true
        },
		{
            "productName" : "product4",
            "productDescription": "Product4",
            "productPrice": 123.0,
			"active": true
        },
		{
            "productName" : "product5",
            "productDescription": "Product5",
            "productPrice": 123.0,
			"active": true
        }
    ]);

};