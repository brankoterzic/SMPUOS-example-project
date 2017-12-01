var connection = new Mongo();

var dropDatabases = function(conn, sufix){
	conn.getDB("productservice-" + sufix).dropDatabase();
	conn.getDB("userservice-" + sufix).dropDatabase();

};

var createDatabasesAndCollections = function(conn, sufix){
	var productserviceDb = conn.getDB("productservice-" + sufix);
	productserviceDb.createCollection("product");
	
	var userserviceDb = conn.getDB("userservice-" + sufix);
	userserviceDb.createCollection("user");
};

load("product.js");
load("user.js");

var insertDefaultData = function(conn, sufix){
	var productserviceDb = conn.getDB("productservice-" + sufix);
	insertDefaultDataProduct(productserviceDb);
	
	var userserviceDb = conn.getDB("userservice-" + sufix);
	insertDefaultDataUser(userserviceDb);
};

dropDatabases(connection,"dpy");

createDatabasesAndCollections(connection,"dpy");

insertDefaultData(connection,"dpy");
