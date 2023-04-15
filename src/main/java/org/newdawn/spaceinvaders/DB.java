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
        HashMap<String, Integer> users = new HashMap<>();
        users.put("score", score);
        this.userRef.setValueAsync(users);
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
