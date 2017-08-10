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

public class FirebaseHelperEntertainment {
    DatabaseReference db;
    Boolean saved;

    ArrayList<Entertainment> entertainments = new ArrayList<>();
    public FirebaseHelperEntertainment(DatabaseReference db) {
        this.db = db;
    }

    public Boolean save(Entertainment entertainment)
    {
        if(entertainment ==null)
        {
            saved=false;
        }else {
            try {
                db.child("Entertainment").push().setValue(entertainment);
                saved=true;

            }catch (DatabaseException e)
            {
                e.printStackTrace();
                saved=false;
            }
        }
        return  saved;
    }
    //Fetch and fill  ArrayList
    private void fetchData(DataSnapshot dataSnapshot)
    {
        entertainments.clear();
        for (DataSnapshot ds :dataSnapshot.getChildren())
        {
            Entertainment entertainment = ds.getValue(Entertainment.class);
            entertainments.add(entertainment);
        }
    }

    public ArrayList<Entertainment> retrive()
    {
        db.addChildEventListener(new ChildEventListener()
        {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s)
            {
                fetchData(dataSnapshot);

            }
            @Override
            public void onChildChanged(DataSnapshot dataSnapshot,String s)
            {
                fetchData(dataSnapshot);

            }
            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot)
            {

            }
            @Override
            public void onChildMoved(DataSnapshot dataSnapshot,String s)
            {

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }


        });
        return entertainments;
    }





}

