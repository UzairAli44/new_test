package com.example.fidely.fidely.config;

import org.ektorp.CouchDbConnector;
import org.ektorp.CouchDbInstance;
import org.ektorp.http.StdHttpClient;
import org.ektorp.impl.StdCouchDbConnector;
import org.ektorp.impl.StdCouchDbInstance;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.net.MalformedURLException;

@Configuration
public class CouchDbConfig {

    @Bean
    public CouchDbConnector couchDbConnector() throws MalformedURLException {
        CouchDbInstance dbInstance = new StdCouchDbInstance(new StdHttpClient.Builder()
                .url("http://localhost:5984") // Change to your CouchDB URL
                .username("admin") // CouchDB admin username
                .password("admin12345") // CouchDB admin password
                .build());

        CouchDbConnector db = new StdCouchDbConnector("users", dbInstance);
        db.createDatabaseIfNotExists();
        return db;
    }
}
