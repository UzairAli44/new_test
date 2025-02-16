package com.example.fidely.fidely.repository;
import org.ektorp.CouchDbConnector;
import org.ektorp.support.CouchDbRepositorySupport;
import org.springframework.stereotype.Repository;

import com.example.fidely.fidely.model.User;

@Repository
public class UserRepository extends CouchDbRepositorySupport<User> {

    public UserRepository(CouchDbConnector db) {
        super(User.class, db);
        initStandardDesignDocument();
    }

    public User findByEmail(String email) {
        return queryView("by_email", email).stream().findFirst().orElse(null);
    }
}

