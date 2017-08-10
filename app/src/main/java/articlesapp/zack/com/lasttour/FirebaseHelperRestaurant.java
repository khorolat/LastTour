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

public class FirebaseHelperRestaurant {
    DatabaseReference db;
    Boolean saved;

    ArrayList<Restaurant> restaurants = new ArrayList<>();
    public FirebaseHelperRestaurant(DatabaseReference db) {
        this.db = db;
    }

    public Boolean save(Restaurant restaurant)
    {
        if(restaurant ==null)
        {
            saved=false;
        }else {
            try {
                db.child("Restaurant").push().setValue(restaurant);
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
        restaurants.clear();
        for (DataSnapshot ds :dataSnapshot.getChildren())
        {
            Restaurant restaurant = ds.getValue(Restaurant.class);
            restaurants.add(restaurant);
        }
    }

    public ArrayList<Restaurant> retrive()
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
        return restaurants;
    }





}
