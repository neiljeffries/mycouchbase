package com.example.demo.controllers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.couchbase.client.core.error.CouchbaseException;
import com.example.demo.classes.User;
import com.example.demo.services.UserService;

@RequestMapping("/api/users")
@RestController
public class UserController {

    @Autowired
    UserService userService;

    @PostMapping("/addUser")
    public ResponseEntity<String> handlePayload(@RequestBody User payload, @RequestParam String docId) {

        if (payload.getName() == null || payload.getName().isEmpty()) {
            return ResponseEntity.badRequest().body("Name is required");
        }
        if (docId == null || docId.isEmpty()) {
            return ResponseEntity.badRequest().body("docId is a required path variable");
        }
        if (payload.getAge() < 0) {
            return ResponseEntity.badRequest().body("Age must be a positive number");
        }

        try{
            userService.insertUser(docId, payload.getName(), payload.getAge(), payload.getEmail());
        }catch(CouchbaseException exception){
            if(exception.getMessage().contains("id already exists")){
                return ResponseEntity.badRequest().body("A document with that Id already exists");
            }else{
                return ResponseEntity.badRequest().body(exception.getMessage());
            }
        }

         return ResponseEntity.ok("successfull");
    }



    @PutMapping("/upsertUser")
    public ResponseEntity<String> upsertUser(@RequestBody User payload, @RequestParam String docId) {

        if (payload.getName() == null || payload.getName().isEmpty()) {
            return ResponseEntity.badRequest().body("Name is required!");
        }

        if (payload.getAge() < 0) {
            return ResponseEntity.badRequest().body("Age must be a positive number");
        }

        try{
            userService.upsertUser(docId, payload.getName());
        }catch(CouchbaseException exception){
            return ResponseEntity.badRequest().body(exception.getMessage());
        }

        return ResponseEntity.ok("successfull");
    }


}
