package com.neiljeffires.mycouchbase.operations;

import java.time.Duration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.couchbase.client.java.Bucket;
import com.couchbase.client.java.Cluster;
import com.couchbase.client.java.Collection;
import com.couchbase.client.java.Scope;
import com.couchbase.client.java.json.JsonObject;
import com.couchbase.client.java.kv.MutationResult;
import com.neiljeffires.mycouchbase.configs.YAMLConfig;

@Service
public class UpsertDocument {

    @Autowired
    private YAMLConfig myConfig;

    public MutationResult upsertDocument(String scopeName, String collectionName, String id, String name, String value) {

        try (Cluster cluster = Cluster.connect(myConfig.getConnectionString(), myConfig.getCouchbaseUserName(), myConfig.getPassword());) {
            // get a bucket reference
            Bucket bucket = cluster.bucket(myConfig.getBucketName());
            bucket.waitUntilReady(Duration.ofSeconds(10));

            // get a user defined collection reference
            Scope scope = bucket.scope(scopeName);
            Collection collection = scope.collection(collectionName);

            // Upsert Document
            return collection.upsert(id, JsonObject.create().put(name, value));
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return null;
    }

}
