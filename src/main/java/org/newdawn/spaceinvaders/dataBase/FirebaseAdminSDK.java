package org.newdawn.spaceinvaders.dataBase;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import com.google.firebase.database.*;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class FirebaseAdminSDK {

    public void initFirebase() {

        // Fetch the service account key JSON file contents
        try {
            FileInputStream serviceAccount =
                    new FileInputStream("config/spaceinvader-a2ad5-firebase-adminsdk-1j6qf-cd998d0f76.json");

            FirebaseOptions options = new FirebaseOptions.Builder()
                    .setCredentials(GoogleCredentials.fromStream(serviceAccount))
                    .setDatabaseUrl("https://spaceinvader-a2ad5-default-rtdb.firebaseio.com")
                    .build();

            FirebaseApp.initializeApp(options);


            // As an admin, the app has access to read and write all data, regardless of Security Rules
            DatabaseReference ref = FirebaseDatabase.getInstance()
                    .getReference("restricted_access/secret_document");
            ref.addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                    Object document = dataSnapshot.getValue();
                    System.out.println(document);
                }

                @Override
                public void onCancelled(DatabaseError error) {
                }
            });

        } catch (IOException e) {
            Logger.getLogger(FirebaseAdminSDK.class.getName()).log(Level.SEVERE, null, e);
        }

    }
}
