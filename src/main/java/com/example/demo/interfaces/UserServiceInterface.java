package com.example.demo.interfaces;

public interface UserServiceInterface {

    String upsertUser(String docId, String name);

    boolean insertUser(String docId, String name, int age, String email);

}