package com.neiljeffires.mycouchbase.configs;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties
public class YAMLConfig {
    private String couchbaseUserName;
    private String password;
    private String connectionString;
    private String bucketName;

    public String getCouchbaseUserName() {
        return couchbaseUserName;
    }

    public void setCouchbaseUserName(String couchbaseUserName) {
        this.couchbaseUserName = couchbaseUserName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getConnectionString() {
        return connectionString;
    }

    public void setConnectionString(String connectionString) {
        this.connectionString = connectionString;
    }

    public String getBucketName() {
        return bucketName;
    }

    public void setBucketName(String bucketName) {
        this.bucketName = bucketName;
    }

    @Override
    public String toString() {
        return "YAMLConfig [couchbaseUserName=" + couchbaseUserName + ", password=" + password + ", connectionString="
                + connectionString + ", bucketName=" + bucketName + "]";
    }

}
