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
    private Integer highScore = 0;
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

//    public Integer getHighScore() {
//        userRef.addValueEventListener(new ValueEventListener() {
//            @Override
//            public void onDataChange(DataSnapshot dataSnapshot) {
//                if (dataSnapshot.child("highScore").getValue() != null) {
//                    highScore = dataSnapshot.child("highScore").getValue(Integer.class);
//                }
//            }
//
//            @Override
//            public void onCancelled(DatabaseError databaseError) {
//                System.out.println("The read failed: " + databaseError.getCode());
//            }
//        });
//        return highScore;
//    }

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
//        Integer currentHighScore = getHighScore();

    }

    public Object getFirstPlaceScore() {
        // Rank 불러오기
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference("users");

        // 상위 10명의 사용자 가져오기
        Query topScoresQuery = myRef.orderByChild("score").limitToLast(1);
        topScoresQuery.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot childSnapshot : dataSnapshot.getChildren()) {
                    firstPlaceScore = Math.toIntExact(dataSnapshot.child("score").getValue(Long.class));
                }
            }
            @Override
            public void onCancelled(DatabaseError databaseError) {
                // 처리할 오류 처리
            }
        });
        return firstPlaceScore;
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
                if (dataSnapshot.child("playCount").getValue() != null) {
                    playCount = dataSnapshot.child("playCount").getValue(Integer.class);
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                System.out.println("The read failed: " + databaseError.getCode());
            }
        });
        return playCount;
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
                    System.out.println("Transaction failed.");
                } else {
                    System.out.println("Transaction completed.");
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
                    System.out.println("Transaction failed.");
                } else {
                    System.out.println("Transaction completed.");
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

//        userRef.addValueEventListener(new ValueEventListener() {
//            @Override
//            public void onDataChange(DataSnapshot dataSnapshot) {
//                if (dataSnapshot.child("playTime").getValue() != null) {
//                    playTime = dataSnapshot.child("playTime").getValue(Integer.class);
//                }
//            }
//
//            @Override
//            public void onCancelled(DatabaseError databaseError) {
//                System.out.println("The read failed: " + databaseError.getCode());
//            }
//        });
////        return playTime;
    }

//    public Object returnData(){
//        return score;
//    }

}
