var connection = new Mongo();

var dropDatabases = function(conn, sufix){
	conn.getDB("productservice-" + sufix).dropDatabase();
};

var createDatabasesAndCollections = function(conn, sufix){
	var productserviceDb = conn.getDB("productservice-" + sufix);
	productserviceDb.createCollection("product");
};

load("product.js");

var insertDefaultData = function(conn, sufix){
	var productserviceDb = conn.getDB("productservice-" + sufix);
	insertDefaultDataProduct(productserviceDb);
};

dropDatabases(connection,"dev");
dropDatabases(connection,"dpy");

createDatabasesAndCollections(connection,"dev");
createDatabasesAndCollections(connection,"dpy");

insertDefaultData(connection,"dev");
insertDefaultData(connection,"dpy");
