package com.example.demo.interfaces;

import com.couchbase.client.java.query.QueryResult;

public interface InventoryServiceInterface {

    QueryResult getInventory() throws Exception;

}