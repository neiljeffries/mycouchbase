package com.example.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class DemoApplication {

	// @Autowired
	// private YAMLConfig myConfig;

	public static void main(String... args) {
		SpringApplication.run(DemoApplication.class, args);
	}



	// @EventListener(ApplicationReadyEvent.class)
	// @Order(value=2);
	// public void addDocumentWithScope() {
	// 	System.out.println(myConfig.toString());
	// 	// Connect to Couchbase cluster
	// 	Cluster cluster = Cluster.connect(myConfig.getConnectionString(), myConfig.getUname(),
	// 			myConfig.getPassword());

	// 	// Get a reference to the myScope scope
	// 	Scope scope = cluster.bucket(myConfig.getBucketName()).scope("tenant_agent_00");

	// 	// Get a reference to the myCollection collection within myScope
	// 	Collection collection = scope.collection("users");

	// 	// Create a sample document
	// 	JsonObject document = JsonObject.create()
	// 			.put("name", "John4")
	// 			.put("age", 38)
	// 			.put("email", "john4@example.com");

	// 	Duration expiry = Duration.ofHours(1);

	// 	// Insert the document into the collection
	// 	collection.insert("doc4", document, InsertOptions.insertOptions().expiry(expiry));

	// 	// Disconnect from Couchbase cluster
	// 	cluster.disconnect();


	// }

	// @Order(1);
	// @EventListener(ApplicationReadyEvent.class)
	// public void launch() {
	// 	Cluster cluster = Cluster.connect(
	// 		myConfig.getConnectionString(),
	// 			ClusterOptions.clusterOptions(myConfig.getUname(), myConfig.getPassword()).environment(env -> {
	// 				// Customize client settings by calling methods on the "env" variable.
	// 			}));

	// 	// get a bucket reference
	// 	Bucket bucket = cluster.bucket(myConfig.getBucketName());
	// 	bucket.waitUntilReady(Duration.ofSeconds(10));

	// 	// get a user-defined collection reference
	// 	Scope scope = bucket.scope("tenant_agent_00");
	// 	Collection collection = scope.collection("users");

	// 	// Upsert Document
	// 	collection.upsert(
	// 			"my-document",
	// 			JsonObject.create().put("name", "mike1"));

	// 	// Get Document
	// 	GetResult getResult = collection.get("my-document");
	// 	String name = getResult.contentAsObject().getString("name");
	// 	System.out.println(name); // name == "mike"

	// 	// Call the query() method on the scope object and store the result.
	// 	Scope inventoryScope = bucket.scope("inventory");
	// 	QueryResult result = inventoryScope.query("SELECT * FROM airline WHERE id = 10;");

	// 	// Return the result rows with the rowsAsObject() method and print to the
	// 	// terminal.
	// 	System.out.println(result.rowsAsObject());
	// 	cluster.disconnect();

	// 	// addDocumentWithScope();
	// }

}