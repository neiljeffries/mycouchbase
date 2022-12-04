package com.neiljeffires.mycouchbase.operations;

import java.time.Duration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.couchbase.client.java.Bucket;
import com.couchbase.client.java.Cluster;
import com.couchbase.client.java.Collection;
import com.couchbase.client.java.Scope;
import com.couchbase.client.java.kv.GetResult;
import com.neiljeffires.mycouchbase.configs.YAMLConfig;

@Service
public class GetDocument {
    @Autowired
    private YAMLConfig myConfig;

    public String getDocument(String scopeName, String collectionName, String id, String name) {

        try (Cluster cluster = Cluster.connect(myConfig.getConnectionString(), myConfig.getCouchbaseUserName(),
                myConfig.getPassword());) {

            Bucket bucket = cluster.bucket(myConfig.getBucketName());
            bucket.waitUntilReady(Duration.ofSeconds(10));

            Scope scope = bucket.scope(scopeName);
            Collection collection = scope.collection(collectionName);

            // Get Document
            GetResult getResult = collection.get(id);
            System.out.println(name);

            return getResult.contentAsObject().getString(name);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return null;
    }
}
