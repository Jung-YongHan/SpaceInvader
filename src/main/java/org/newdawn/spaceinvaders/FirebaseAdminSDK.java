package org.newdawn.spaceinvaders;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class FirebaseAdminSDK {

    public void initFirebase() {

        try {
            FileInputStream serviceAccount =
                    new FileInputStream("spaceinvader-a2ad5-firebase-adminsdk-1j6qf-bcf033ec84.json");

            FirebaseOptions options = new FirebaseOptions.Builder()
                    .setCredentials(GoogleCredentials.fromStream(serviceAccount))
                    .setDatabaseUrl("https://spaceinvader-a2ad5-default-rtdb.firebaseio.com")
                    .build();

            FirebaseApp.initializeApp(options);
        } catch (IOException e) {
            Logger.getLogger(FirebaseAdminSDK.class.getName()).log(Level.SEVERE, null, e);
        }

    }
}
