package com.example.demo.services;

import java.time.Duration;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.couchbase.client.core.deps.com.fasterxml.jackson.core.JsonProcessingException;
import com.couchbase.client.core.deps.com.fasterxml.jackson.core.type.TypeReference;
import com.couchbase.client.core.deps.com.fasterxml.jackson.databind.JsonMappingException;
import com.couchbase.client.core.deps.com.fasterxml.jackson.databind.ObjectMapper;
import com.couchbase.client.java.Bucket;
import com.couchbase.client.java.Cluster;
import com.couchbase.client.java.Scope;
import com.couchbase.client.java.query.QueryResult;
import com.example.demo.classes.Airline;
import com.example.demo.configs.YAMLConfig;
import com.example.demo.interfaces.InventoryServiceInterface;

@Service
public class InventoryService implements InventoryServiceInterface {

	@Autowired
	private YAMLConfig myConfig;

	@Override
	public QueryResult getInventory() {

		try (Cluster cluster = Cluster.connect(myConfig.getConnectionString(), myConfig.getUname(),
				myConfig.getPassword());) {

			// get a bucket reference
			Bucket bucket = cluster.bucket(myConfig.getBucketName());
			bucket.waitUntilReady(Duration.ofSeconds(10));

			// Call the query() method on the scope object and store the result.
			Scope inventoryScope = bucket.scope("inventory");
			QueryResult result = inventoryScope.query("SELECT * FROM airline WHERE id = 10;");

			// List<Airline> u = result.rowsAs(Airline.class);
			// System.out.println(u);

			AirlineConverter converter = new AirlineConverter(new ObjectMapper());

			try {
				List<Airline> airlines = converter.convertJsonToList(result.rowsAsObject().toString());
				System.out.println(airlines);
			} catch (JsonMappingException e) {
					e.printStackTrace();
			} catch (JsonProcessingException e) {
					e.printStackTrace();
			}

			System.out.println(result.rowsAsObject());

			return result;
		}

	}

	public class AirlineConverter {

		private final ObjectMapper objectMapper;

		public AirlineConverter(ObjectMapper objectMapper) {
			this.objectMapper = objectMapper;
		}

		public List<Airline> convertJsonToList(String json) throws JsonMappingException, JsonProcessingException {
			TypeReference<List<Airline>> typeReference = new TypeReference<List<Airline>>() {
			};
			return objectMapper.readValue(json, typeReference);
		}
	}

}
