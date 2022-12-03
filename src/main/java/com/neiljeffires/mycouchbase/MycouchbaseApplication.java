package com.neiljeffires.mycouchbase;

import java.time.Duration;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;

import com.couchbase.client.java.Bucket;
import com.couchbase.client.java.Cluster;
import com.couchbase.client.java.ClusterOptions;
import com.couchbase.client.java.Collection;
import com.couchbase.client.java.Scope;
import com.couchbase.client.java.json.JsonObject;
import com.couchbase.client.java.kv.GetResult;
import com.couchbase.client.java.kv.MutationResult;
import com.couchbase.client.java.query.QueryResult;

@SpringBootApplication
public class MycouchbaseApplication {
	static String connectionString = "localhost";
	static String username = "travel_sample";
	static String bucketName = "travel-sample";
	static String password = System.getenv("travel_sample_password");

	public static void main(String[] args) {
		SpringApplication.run(MycouchbaseApplication.class, args);
	  }


	@EventListener(ApplicationReadyEvent.class)
	public void InitCouchbase(){
		System.out.println("-------------------INIT COUCHBASE--------------------");

		// For a secure cluster connection, use `couchbases://<your-cluster-ip>` instead.
		Cluster cluster = Cluster.connect("couchbase://" + connectionString, username, password);
		cluster.disconnect();

		// For a secure cluster connection, use `couchbases://<your-cluster-ip>` instead.
		cluster = Cluster.connect("couchbase://" + connectionString, ClusterOptions.clusterOptions(username, password).environment(env -> {
			// Customize client settings by calling methods on the "env" variable.
		}));

		// get a bucket reference
		Bucket bucket = cluster.bucket(bucketName);
		bucket.waitUntilReady(Duration.ofSeconds(10));

		// get a user defined collection reference
		Scope scope = bucket.scope("tenant_agent_00");
		Collection collection = scope.collection("users");

		// Upsert Document
		MutationResult upsertResult = collection.upsert("my-document",JsonObject.create().put("name", "mike"));

		// Get Document
		GetResult getResult = collection.get("my-document");
		String name = getResult.contentAsObject().getString("name");
		System.out.println(name); // name == "mike"

		// Call the query() method on the scope object and store the result.
		Scope inventoryScope = bucket.scope("inventory");
		QueryResult result = inventoryScope.query("SELECT * FROM airline WHERE id = 10;");

		// Return the result rows with the rowsAsObject() method and print to the terminal.
		System.out.println(result.rowsAsObject());
	}


	// public String getCouchBasePassword() throws IOException {
	// 	Properties prop = new Properties();
	// 	prop.load(this.getClass().getResourceAsStream("/couchbase.properties"));
	// 	return prop.getProperty("couchbase_password");
	// }

}
