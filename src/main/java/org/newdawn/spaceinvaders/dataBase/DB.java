package org.newdawn.spaceinvaders.dataBase;

import com.google.firebase.auth.FirebaseAuthException;
import com.google.firebase.database.*;
import org.newdawn.spaceinvaders.frame.LoginPage;

import java.util.HashMap;
import java.util.function.Consumer;
import java.util.logging.Level;
import java.util.logging.Logger;

public class DB {
    private final FirebaseDatabase db = FirebaseDatabase.getInstance();
    private final DatabaseReference userRef = db.getReference("users").child(LoginPage.getUserName());

    public static Object score = 0;
    private Integer playCount = 0;
    private Integer playTime = 0;
    private Integer firstPlaceScore = 0;

    public DB() throws FirebaseAuthException {
    }

    public void getHighScore(Consumer<Integer> callback) {
        userRef.child("highScore").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                Integer highScore = dataSnapshot.getValue(Integer.class);
                if (highScore == null) {
                    highScore = 0;
                }
                callback.accept(highScore);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                System.out.println("The read failed: " + databaseError.getCode());
            }
        });
    }

    public void storeHighScore(int score) {
        getHighScore(highScore -> {
            Integer currentHighScore = highScore;
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
        });
    }

    public void getPlayCount(Consumer<Integer> callback) {
        userRef.child("playCount").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                Integer playCount = dataSnapshot.getValue(Integer.class);
                if (playCount == null) {
                    playCount = 0;
                }
                callback.accept(playCount);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                System.out.println("The read failed: " + databaseError.getCode());
            }
        });
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
                    System.out.println("Transaction failed. - playCount");
                } else {
                    System.out.println("Transaction completed. - playCount");
                }
            }
        });
    }

    public void updatePlayTime(int time) {
        userRef.runTransaction(new Transaction.Handler() {
            @Override
            public Transaction.Result doTransaction(MutableData mutableData) {
                Integer playTime = mutableData.child("playTime").getValue(Integer.class);
                if (playTime == null) {
                    mutableData.child("playTime").setValue(time);
                } else {
                    mutableData.child("playTime").setValue(playTime + time);
                }
                return Transaction.success(mutableData);
            }

            @Override
            public void onComplete(DatabaseError databaseError, boolean b, DataSnapshot dataSnapshot) {
                if (databaseError != null) {
                    System.out.println("Transaction failed. - playTime");
                } else {
                    System.out.println("Transaction completed. - playTime");
                }
            }
        });
    }

    public void getPlayTime(Consumer<Integer> callback) {
        userRef.child("playTime").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                Integer playTime = dataSnapshot.getValue(Integer.class);
                if (playTime == null) {
                    playTime = 0;
                }
                callback.accept(playTime);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                System.out.println("The read failed: " + databaseError.getCode());
            }
        });
    }

    public void updateCoin(int newCoin) {
        userRef.runTransaction(new Transaction.Handler() {
            @Override
            public Transaction.Result doTransaction(MutableData mutableData) {
                Integer coin = mutableData.child("coin").getValue(Integer.class);
                if (coin == null) {
                    mutableData.child("coin").setValue(newCoin);
                } else {
                    mutableData.child("coin").setValue(coin + newCoin);
                }
                return Transaction.success(mutableData);
            }

            @Override
            public void onComplete(DatabaseError databaseError, boolean b, DataSnapshot dataSnapshot) {
                if (databaseError != null) {
                    System.out.println("Transaction failed. - coin");
                } else {
                    System.out.println("Transaction completed. - coin");
                }
            }
        });
    }

    public void getCoin(Consumer<Integer> callback) {
        userRef.child("coin").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                Integer coin = dataSnapshot.getValue(Integer.class);
                if (coin == null) {
                    coin = 0;
                }
                callback.accept(coin);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                System.out.println("The read failed: " + databaseError.getCode());
            }
        });
    }
}
