package com.example.demo.services;

import java.time.Duration;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.couchbase.client.java.Bucket;
import com.couchbase.client.java.Cluster;
import com.couchbase.client.java.Scope;
import com.couchbase.client.java.query.QueryResult;
import com.example.demo.classes.AirlineObject;
import com.example.demo.configs.YAMLConfig;
import com.example.demo.interfaces.InventoryServiceInterface;

@Service
public class InventoryService implements InventoryServiceInterface {

	@Autowired
	private YAMLConfig myConfig;

	@Override
	public QueryResult getInventoryById(int id) {

		try (Cluster cluster = Cluster.connect(myConfig.getConnectionString(), myConfig.getUname(),
				myConfig.getPassword());) {

			// get a bucket reference
			Bucket bucket = cluster.bucket(myConfig.getBucketName());
			bucket.waitUntilReady(Duration.ofSeconds(10));

			// Call the query() method on the scope object and store the result.
			Scope inventoryScope = bucket.scope("inventory");

			// QueryResult result = inventoryScope.query(String.format("SELECT * FROM airline WHERE id = %d;", id));
			QueryResult result = inventoryScope.query("SELECT * FROM airline;");

			List<AirlineObject> f =result.rowsAs(AirlineObject.class);

			System.out.println(f.get(3).getAirline().getCountry());

			return result;
		}

	}

}
