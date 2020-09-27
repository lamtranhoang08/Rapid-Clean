package com.uts.rapid.clean.model.dao;

import com.mongodb.client.MongoCollection;
import static com.mongodb.client.model.Filters.eq;
import static com.mongodb.client.model.Filters.and;
import org.bson.Document;
import com.uts.rapid.clean.model.Cleaner;
import org.bson.types.ObjectId;

public class CleanerDAO extends MongoDB {
    
    private MongoCollection<Document> collection;
    
    public CleanerDAO() {
        super();
        collection = super.database.getCollection("Cleaner");
    }
    
    public void createCleaner(String firstName, String lastName, String email,
            String password, String phoneNumber,int bankBsbNumber,
            int bankAccountNumber, String bankAccountHolderName) {
        Document document = new Document("firstName", firstName)
                .append("lastName", lastName)
                .append("email", email)
                .append("password", password)
                .append("phoneNumber", phoneNumber)
                .append("bankBsbNumber", bankBsbNumber)
                .append("bankAccountNumber", bankAccountNumber)
                .append("bankAccountHolderName", bankAccountHolderName);
        collection.insertOne(document);
    }
    
    public boolean hasCleaner(String email) {
        return collection.find(eq("email", email)).first() != null;
    }
    
    public Cleaner findCleaner(String email, String password) {
        Document document = collection.find(and(eq("email", email), eq("password", password))).first();
        if (document != null) {
            ObjectId cleanerObjId = (ObjectId) document.get("_id");
            String cleanerId = cleanerObjId.toString();
            return new Cleaner(cleanerId, (String) document.get("firstName"),
                    (String) document.get("lastName"), (String) document.get("email"),
                    (String) document.get("password"), (String) document.get("phoneNumber"),
                    (int) document.get("bankBsbNumber"), (int) document.get("bankAccountNumber"),
                    (String) document.get("bankAccountHolderName"));
        }
        else {
            return null;
        }
    }
}
