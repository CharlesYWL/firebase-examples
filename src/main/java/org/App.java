package org;

import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.DocumentReference;
import com.google.cloud.firestore.Firestore;
import com.google.cloud.firestore.FirestoreOptions;
import com.google.cloud.firestore.QueryDocumentSnapshot;
import com.google.cloud.firestore.QuerySnapshot;
import com.google.cloud.firestore.WriteResult;
import com.google.common.collect.ImmutableMap;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutionException;

public class App {
    public static void main( String[] args ) {
        String projectId = (args.length == 0) ? null : args[0];
        if (projectId == null) {
            throw new IllegalArgumentException("projectId unset");
        }
        
        // init firebase db
        FirestoreOptions firestoreOptions = 
            FirestoreOptions.getDefaultInstance().toBuilder()
                .setProjectId(projectId).build();
        Firestore db = firestoreOptions.getService();
        
        // get all documents in collections
        ApiFuture<QuerySnapshot> future = db.collection("users").get();
        try {
            List<QueryDocumentSnapshot> documents = future.get().getDocuments();
            for (QueryDocumentSnapshot document : documents) {
                System.out.println("Id: %s".format(document.getId()));
            }
        } catch (InterruptedException|ExecutionException e) {
            System.err.println(e);
        }
        
        // check if collections contain documentId
        // get collections.document all key-value pairs
        // get collections.document.key -> value
        // upsert collections.document key-value    
        // delete collections.document key
        // does firestore has auth? yes! how to use?
    
        System.out.println("main bottom reached");
    }
}
