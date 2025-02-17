package com.example.fidely.fidely.config;

import org.ektorp.CouchDbConnector;
import org.ektorp.CouchDbInstance;
import org.ektorp.http.StdHttpClient;
import org.ektorp.impl.StdCouchDbConnector;
import org.ektorp.impl.StdCouchDbInstance;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.net.MalformedURLException;

@Configuration
public class CouchDbConfig {

    @Value("${couchdb.url}")
    private String couchDbUrl;

    @Value("${couchdb.username}")
    private String couchDbUsername;

    @Value("${couchdb.password}")
    private String couchDbPassword;

    @Value("${couchdb.database}")
    private String couchDbDatabase;

    @Bean
    public CouchDbConnector couchDbConnector() throws MalformedURLException {
        CouchDbInstance dbInstance = new StdCouchDbInstance(new StdHttpClient.Builder()
                .url(couchDbUrl)
                .username(couchDbUsername)
                .password(couchDbPassword)
                .build());

        CouchDbConnector db = new StdCouchDbConnector(couchDbDatabase, dbInstance);
        db.createDatabaseIfNotExists();
        return db;
    }
}