package articlesapp.zack.com.lasttour;

import android.app.Activity;
import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Admin on 8/4/2017.
 */

public class RestaurantAdapter extends ArrayAdapter<Restaurant> {

    private Activity context;
    private List<Restaurant> restaurants;
    Restaurant restaurant;

    public RestaurantAdapter(Activity context, List<Restaurant> restaurants) {
        super(context,R.layout.model,restaurants);
        this.restaurants = restaurants;

        this.context = context;
        this.restaurants = restaurants;

    }


    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = context.getLayoutInflater();

        View listViewItem = inflater.inflate(R.layout.model, null, true);



        Restaurant restaurant = restaurants.get(position);

        TextView tvName=(TextView) listViewItem.findViewById(R.id.tvName);
        ImageView ivPicture =(ImageView) listViewItem.findViewById(R.id.ivPicture);
        TextView tvDescription=(TextView)listViewItem.findViewById(R.id.tvDescription);

        tvName.setText(restaurant.getName());
        tvDescription.setText(restaurant.getDescription());
        Glide.with(context)
                .load(restaurant.getUrl())
                .into(ivPicture);



        return listViewItem;

    }
}











