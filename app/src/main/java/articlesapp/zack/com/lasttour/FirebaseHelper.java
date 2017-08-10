package articlesapp.zack.com.lasttour;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseException;
import com.google.firebase.database.DatabaseReference;

import java.util.ArrayList;

/**
 * Created by Admin on 8/4/2017.
 */

public class FirebaseHelper {
    DatabaseReference db;
    Boolean saved;

    ArrayList<Mall> malls = new ArrayList<>();

    public FirebaseHelper(DatabaseReference db) {
        this.db = db;
    }

    public Boolean save(Mall mall) {
        if (mall == null) {
            saved = false;
        } else {
            try {
                db.child("Mall").push().setValue(mall);
                saved = true;

            } catch (DatabaseException e) {
                e.printStackTrace();
                saved = false;
            }
        }
        return saved;
    }

    //Fetch and fill  ArrayList
    private void fetchData(DataSnapshot dataSnapshot) {
        malls.clear();
        for (DataSnapshot ds : dataSnapshot.getChildren()) {
            Mall mall = ds.getValue(Mall.class);
            malls.add(mall);
        }
    }

    public ArrayList<Mall> retrive() {
        db.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                fetchData(dataSnapshot);

            }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {
                fetchData(dataSnapshot);

            }

            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {

            }

            @Override
            public void onChildMoved(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }


        });
        return malls;
    }

}









