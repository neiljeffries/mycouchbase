package com.neiljeffires.mycouchbase;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;

import com.couchbase.client.java.kv.MutationResult;
import com.couchbase.client.java.query.QueryResult;
import com.neiljeffires.mycouchbase.configs.YAMLConfig;
import com.neiljeffires.mycouchbase.operations.ClusterQuery;
import com.neiljeffires.mycouchbase.operations.GetDocument;
import com.neiljeffires.mycouchbase.operations.UpsertDocument;

@SpringBootApplication
public class MycouchbaseApplication {

	@Autowired
	private YAMLConfig myConfig;

	@Autowired
	private UpsertDocument upsertDocument;

	@Autowired
	private GetDocument getDocument;

	@Autowired
	private ClusterQuery clusterQuery;

	public static void main(String[] args) {
		SpringApplication.run(MycouchbaseApplication.class, args);
	}

	@EventListener(ApplicationReadyEvent.class)
	public void InitCouchbase() {

		// overriding yml password with a custom env variable here.
		// comment this out to use password from yml
		myConfig.setPassword(System.getenv("travel_sample_password"));

		// Upsert Document
		MutationResult upsertResult = upsertDocument.upsertDocument("tenant_agent_00", "users", "my-document", "name",	"Donovan");
		System.out.println("UPSERT RESULT: " + upsertResult.toString());

		// Get Document
		String name = getDocument.getDocument("tenant_agent_00", "users", "my-document", "name");
		System.out.println("GET DOCUMENT RESULT: " + name);

		// Call the SQL++ query() method on the scope object and store the result.
		QueryResult result = clusterQuery.sqlLookup("SELECT * FROM airline WHERE id = 10;", "inventory");
		System.out.println(result.rowsAsObject());

	}

	// public String getCouchBasePassword() throws IOException {
	// Properties prop = new Properties();
	// prop.load(this.getClass().getResourceAsStream("/couchbase.properties"));
	// return prop.getProperty("couchbase_password");
	// }

}
