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
	public QueryResult getInventory(int id) {

		try (Cluster cluster = Cluster.connect(myConfig.getConnectionString(), myConfig.getUname(),	myConfig.getPassword());) {

			Bucket bucket = cluster.bucket(myConfig.getBucketName());
			bucket.waitUntilReady(Duration.ofSeconds(10));
			Scope inventoryScope = bucket.scope("inventory");
			String qString = "SELECT * FROM airline;";
			if (id != -1) {
				qString = String.format("SELECT * FROM airline WHERE id = %d;", id);
			}
			QueryResult result = inventoryScope.query(qString);
			List<AirlineObject> airlines =result.rowsAs(AirlineObject.class);

			for (AirlineObject airline : airlines) {
				System.out.println(airline.getAirline().toString());
			}

			return result;
		}

	}

}
