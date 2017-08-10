package articlesapp.zack.com.lasttour;

import android.app.Activity;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.List;

/**
 * Created by Admin on 8/4/2017.
 */

public class EntertainmentAdapter extends ArrayAdapter<Entertainment> {

    private Activity context;
    private List<Entertainment> entertainments;
    Entertainment entertainment;

    public EntertainmentAdapter(Activity context, List<Entertainment> entertainments) {
        super(context,R.layout.model,entertainments);
        this.entertainments = entertainments;

        this.context = context;
        this.entertainments = entertainments;

    }


    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = context.getLayoutInflater();

        View listViewItem = inflater.inflate(R.layout.model, null, true);



        Entertainment entertainment = entertainments.get(position);

        TextView tvName=(TextView) listViewItem.findViewById(R.id.tvName);
        ImageView ivPicture =(ImageView) listViewItem.findViewById(R.id.ivPicture);
        TextView tvDescription=(TextView)listViewItem.findViewById(R.id.tvDescription);

        tvName.setText(entertainment.getName());
        tvDescription.setText(entertainment.getDescription());
        Glide.with(context)
                .load(entertainment.getUrl())
                .into(ivPicture);



        return listViewItem;

    }
}
