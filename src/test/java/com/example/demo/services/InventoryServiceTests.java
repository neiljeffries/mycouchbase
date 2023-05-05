package com.example.demo.services;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.util.ReflectionTestUtils;

import com.couchbase.client.java.Bucket;
import com.couchbase.client.java.Cluster;
import com.couchbase.client.java.Scope;
import com.couchbase.client.java.query.QueryResult;
import com.example.demo.classes.Airline;
import com.example.demo.classes.AirlineObject;
import com.example.demo.configs.YAMLConfig;

@ExtendWith(MockitoExtension.class)
public class InventoryServiceTests {


    @Mock
    private YAMLConfig myConfig;

    @Mock
    private Cluster cluster;

    @Mock
    private Bucket bucket;

    @Mock
    private Scope inventoryScope;

    @Mock
    private QueryResult queryResult;

    @InjectMocks
    private InventoryService inventoryService;

    @BeforeEach
    public void setup() {
        inventoryService = new InventoryService();
        ReflectionTestUtils.setField(inventoryService, "myConfig", myConfig);
    }

    @Test
    public void testGetInventoryByIdTest() {
        // int id = 123;

        // Set up mock objects and return values
        when(myConfig.getConnectionString()).thenReturn("couchbase://127.0.0.1");
        when(myConfig.getUname()).thenReturn("couchbase://127.0.0.1");
        when(myConfig.getPassword()).thenReturn("8675309kyk");
        when(myConfig.getBucketName()).thenReturn("travel-sample");

        when(cluster.connect("couchbase://127.0.0.1", "couchbase://127.0.0.1", "8675309kyk")).thenReturn(cluster);

        when(cluster.bucket(myConfig.getBucketName())).thenReturn(bucket);
        doNothing().when(bucket).waitUntilReady(any(Duration.class));

        when(bucket.scope("inventory")).thenReturn(inventoryScope);

        when(inventoryScope.query("SELECT * FROM airline;")).thenReturn(queryResult);

        List<AirlineObject> airlineObjects = new ArrayList<>();
        AirlineObject airlineObject = new AirlineObject();
        Airline airline = new Airline();
        airline.setCountry("USA");
        airlineObject.setAirline(airline);
        airlineObjects.add(airlineObject);

        when(queryResult.rowsAs(AirlineObject.class)).thenReturn(airlineObjects);

        QueryResult result = inventoryService.getInventory(1);
        verify(inventoryScope, times(1)).query("SELECT * FROM airline;");
    }

}
