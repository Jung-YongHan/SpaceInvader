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
    private Integer playCount;
    private Integer highScore = 0;

    public DB() throws FirebaseAuthException {
    }

    public int getHighScore() {
        userRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                if (dataSnapshot.child("highScore").getValue() != null) {
                    highScore = dataSnapshot.child("highScore").getValue(Integer.class);
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                System.out.println("The read failed: " + databaseError.getCode());
            }
        });
        return highScore;
    }

    public void storeHighScore(int score) {
        Integer currentHighScore = getHighScore();
        HashMap<String, Object> users = new HashMap<>();
        users.put("highScore", Math.max(currentHighScore, score));
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

    public void readHighScore(){
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

    public int getPlayCount() {
        userRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                playCount = dataSnapshot.child("playCount").getValue(Integer.class);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                System.out.println("The read failed: " + databaseError.getCode());
            }
        });
        return playCount;
    }

    public void increasePlayCount() {
        userRef.runTransaction(new Transaction.Handler() {
            @Override
            public Transaction.Result doTransaction(MutableData mutableData) {
                Integer playCount = mutableData.child("playCount").getValue(Integer.class);
                if (playCount == null) {
                    mutableData.child("playCount").setValue(1);
                } else {
                    mutableData.child("playCount").setValue(playCount + 1);
                }
                return Transaction.success(mutableData);
            }

            @Override
            public void onComplete(DatabaseError databaseError, boolean b, DataSnapshot dataSnapshot) {
                if (databaseError != null) {
                    System.out.println("Transaction failed.");
                } else {
                    System.out.println("Transaction completed.");
                }
            }
        });
    }

    public Object returnData(){
        return score;
    }

}
