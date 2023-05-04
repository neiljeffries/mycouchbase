package com.example.demo.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.services.InventoryService;

@RequestMapping("/api/inventory")
@RestController
public class InventoryController {

    @Autowired
    InventoryService inventoryService;

    @GetMapping("/getInventoryById")
    public ResponseEntity<String> getInventoryById(@RequestParam(name = "id") int id) {
        String json = inventoryService.getInventoryById(id).rowsAsObject().toString();
        return ResponseEntity.ok().body(json);
    }

    @GetMapping("/getAllInventory")
    public ResponseEntity<String> getAllInventory() {
        String json = inventoryService.getAllInventory().rowsAsObject().toString();
        return ResponseEntity.ok().body(json);
    }

}
