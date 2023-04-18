package com.example.demo.services;

import java.time.Duration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.couchbase.client.java.Bucket;
import com.couchbase.client.java.Cluster;
import com.couchbase.client.java.ClusterOptions;
import com.couchbase.client.java.Collection;
import com.couchbase.client.java.Scope;
import com.couchbase.client.java.json.JsonObject;
import com.couchbase.client.java.kv.GetResult;
import com.couchbase.client.java.kv.InsertOptions;
import com.example.demo.configs.YAMLConfig;
import com.example.demo.interfaces.UserServiceInterface;

@Service
public class UserService implements UserServiceInterface {

    @Autowired
	private YAMLConfig myConfig;


    @Override
    public String upsertUser(String docId, String name){

		Cluster cluster = Cluster.connect(
			myConfig.getConnectionString(),
				ClusterOptions.clusterOptions(myConfig.getUname(), myConfig.getPassword()).environment(env -> {
					// Customize client settings by calling methods on the "env" variable.
				}));

		// get a bucket reference
		Bucket bucket = cluster.bucket(myConfig.getBucketName());
		bucket.waitUntilReady(Duration.ofSeconds(10));

		// get a user-defined collection reference
		Scope scope = bucket.scope("tenant_agent_00");
		Collection collection = scope.collection("users");

		// Upsert Document
		collection.upsert(
				docId, // "my-document"
				JsonObject.create().put("name", name)); //mike1

		// Get Document
		GetResult getResult = collection.get(docId); // "my-document"
		cluster.disconnect();
		// System.out.println(nameResult); // nameResult == "mike1"
        return getResult.contentAsObject().getString("name");
    }



    @Override
    public boolean insertUser(String docId, String name, int age, String email){

		// Connect to Couchbase cluster
		Cluster cluster = Cluster.connect(myConfig.getConnectionString(), myConfig.getUname(),
				myConfig.getPassword());

		// Get a reference to the myScope scope
		Scope scope = cluster.bucket(myConfig.getBucketName()).scope("tenant_agent_00");

		// Get a reference to the myCollection collection within myScope
		Collection collection = scope.collection("users");

		// Create a document
		JsonObject document = JsonObject.create()
				.put("name", name)
				.put("age", age)
				.put("email", email);

		Duration expiry = Duration.ofHours(1);

		// Insert the document into the collection
		collection.insert(docId, document, InsertOptions.insertOptions().expiry(expiry));

		// Disconnect from Couchbase cluster
		cluster.disconnect();
        // TODO get return better result response
        return true;
    }

}
