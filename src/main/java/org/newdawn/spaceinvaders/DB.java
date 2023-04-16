package org.newdawn.spaceinvaders;

import com.google.firebase.auth.FirebaseAuthException;
import com.google.firebase.database.*;
import org.newdawn.spaceinvaders.Frame.LoginPage;

import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;

public class DB {
    private final FirebaseDatabase db = FirebaseDatabase.getInstance();

    private final DatabaseReference userRef = db.getReference("users").child(LoginPage.getUserName());

    public static Object score;

    public DB() throws FirebaseAuthException {
    }

    public void storeScore(int score) {
        HashMap<String, Object> users = new HashMap<>();
        users.put("score", score);
        this.userRef.updateChildren(users, new DatabaseReference.CompletionListener() {
            @Override
            public void onComplete(DatabaseError databaseError, DatabaseReference databaseReference) {
                if (databaseError != null) {
                    System.out.println("Data could not be updated: " + databaseError.getMessage());
                } else {
                    System.out.println("Data updated successfully.");
                }
            }
        });
    }

    public void initPlayCount() {
        HashMap<String, Object> users = new HashMap<>();
        users.put("playCount", 0);
        this.userRef.updateChildren(users, new DatabaseReference.CompletionListener() {
            @Override
            public void onComplete(DatabaseError databaseError, DatabaseReference databaseReference) {
                if (databaseError != null) {
                    System.out.println("Data could not be updated: " + databaseError.getMessage());
                } else {
                    System.out.println("Data updated successfully.");
                }
            }
        });
    }

    public void readData(){
        userRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                Logger.getLogger(DB.class.getName()).log(Level.INFO, "data read");
                score = dataSnapshot.getValue();
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                Logger.getLogger(DB.class.getName()).log(Level.INFO, "data read");
            }
        });
    }

    public Object returnData(){
        return score;
    }

}
