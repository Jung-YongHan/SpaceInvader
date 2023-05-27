package org.newdawn.spaceinvaders.database;

import com.google.firebase.auth.FirebaseAuthException;
import com.google.firebase.database.*;
import org.newdawn.spaceinvaders.frame.LoginFrame;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;
import java.util.function.IntConsumer;

public class DB {
    private final FirebaseDatabase db = FirebaseDatabase.getInstance();
    private final DatabaseReference userRef = db.getReference("users").child(LoginFrame.getUserName());
    private final Logger log = LoggerFactory.getLogger(getClass());


    public DB() throws FirebaseAuthException {
    }

    public void getHighScore(IntConsumer callback) {
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
                log.debug("The read failed: {}", databaseError.getCode());
            }
        });
    }

    public void storeHighScore(int score) {
        getHighScore(highScore -> {
            HashMap<String, Object> users = new HashMap<>();
            users.put("highScore", Math.max(highScore, score));
            this.userRef.updateChildren(users, (databaseError, databaseReference) -> {
                if (databaseError != null) {
                    log.debug("Data could not be updated: {}", databaseError.getMessage());
                } else {
                    log.debug("Data updated successfully.");
                }
            });
        });
    }

    public void getPlayCount(IntConsumer callback) {
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
                log.debug("The read failed: {}", databaseError.getCode());
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
                    log.debug("Transaction failed. - playCount");
                } else {
                    log.debug("Transaction completed. - playCount");
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
                    log.debug("Transaction failed. - playTime");
                } else {
                    log.debug("Transaction completed. - playTime");
                }
            }
        });
    }

    public void getPlayTime(IntConsumer callback) {
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
                log.debug("The read failed: {}", databaseError.getCode());
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
                    log.debug("Transaction failed. - coin");
                } else {
                    log.debug("Transaction completed. - coin");
                }
            }
        });
    }

    public void getCoin(IntConsumer callback) {
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
                log.debug("The read failed: {}", databaseError.getCode());
            }
        });
    }
}
