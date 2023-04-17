package com.example.demo.configs;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties
// @Data
public class YAMLConfig {
    private String connectionString;
    private String uname;
    private String password;
    private String bucketName;

    public String getConnectionString() {
        return connectionString;
    }
    public void setConnectionString(String connectionString) {
        this.connectionString = connectionString;
    }
    public String getUname() {
        return uname;
    }
    public void setUname(String uname) {
        this.uname = uname;
    }
    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }
    public String getBucketName() {
        return bucketName;
    }
    public void setBucketName(String bucketName) {
        this.bucketName = bucketName;
    }
    @Override
    public String toString() {
        return "YAMLConfig [connectionString=" + connectionString + ", uname=" + uname + ", password=" + password
                + ", bucketName=" + bucketName + "]";
    }

}