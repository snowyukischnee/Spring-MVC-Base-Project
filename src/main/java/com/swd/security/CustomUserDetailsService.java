package com.swd.security;


import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Filters;
import com.swd.config.Config;
import org.bson.Document;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.List;

public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    MongoClient mongoClient;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        MongoDatabase db = mongoClient.getDatabase(Config.DbName);
        MongoCollection<Document> collection = db.getCollection("account");
        Document document = collection.find(Filters.eq("email", email)).first();
        if (document != null) {
            ObjectId _id = document.getObjectId("_id");
            String u = document.getString("email");
            String p = document.getString("password");
            List<String> roles = (List<String>) document.get("roles");
            boolean isEnabled = document.getBoolean("enabled");
            CustomUserDetails mongoUserDetails = new CustomUserDetails(_id, u, p, roles.toArray(new String[roles.size()]), isEnabled);
            return mongoUserDetails;
        } else {
            throw new UsernameNotFoundException(email + " not found!");
        }
    }
}
