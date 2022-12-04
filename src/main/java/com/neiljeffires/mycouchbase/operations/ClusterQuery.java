package com.neiljeffires.mycouchbase.operations;
import java.time.Duration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.couchbase.client.java.Bucket;
import com.couchbase.client.java.Cluster;
import com.couchbase.client.java.Scope;
import com.couchbase.client.java.query.QueryResult;
import com.neiljeffires.mycouchbase.configs.YAMLConfig;

@Service
public class ClusterQuery {

    @Autowired
	private YAMLConfig myConfig;

    public QueryResult sqlLookup(String queryStatement, String bucketScope) {
        // For a secure cluster connection, use `couchbases://<your-cluster-ip> instead.
        try (Cluster cluster = Cluster.connect(myConfig.getConnectionString(), myConfig.getCouchbaseUserName(),
                myConfig.getPassword());) {

            // get a bucket reference
			Bucket bucket = cluster.bucket(myConfig.getBucketName());
			bucket.waitUntilReady(Duration.ofSeconds(10));

            // Call the query() method on the scope object and store the result.
            Scope inventoryScope = bucket.scope(bucketScope);

            return inventoryScope.query(queryStatement);

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return null;
    }

}
