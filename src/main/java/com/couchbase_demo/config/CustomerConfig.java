package com.couchbase_demo.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.couchbase.config.AbstractCouchbaseConfiguration;

@Configuration
public class CustomerConfig extends AbstractCouchbaseConfiguration {
    @Override
    public String getConnectionString() {
        return "127.0.0.1";
    }

    @Override
    public String getUserName() {
        return "Sonu";
    }

    @Override
    public String getPassword() {
        return "sonu@123";
    }

    @Override
    public String getBucketName() {
        return "Customer";
    }
}
