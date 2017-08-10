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

public class FirebaseHelperExtra {
    DatabaseReference db;
    Boolean saved;

    ArrayList<Extra> extras = new ArrayList<>();
    public FirebaseHelperExtra(DatabaseReference db) {
        this.db = db;
    }

    public Boolean save(Extra extra)
    {
        if(extra ==null)
        {
            saved=false;
        }else {
            try {
                db.child("Extra").push().setValue(extra);
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
        extras.clear();
        for (DataSnapshot ds :dataSnapshot.getChildren())
        {
            Extra extra = ds.getValue(Extra.class);
            extras.add(extra);
        }
    }

    public ArrayList<Extra> retrive()
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
        return extras;
    }





}

