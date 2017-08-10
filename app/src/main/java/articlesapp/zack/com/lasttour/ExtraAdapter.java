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

public class ExtraAdapter extends ArrayAdapter<Extra> {

    private Activity context;
    private List<Extra> extras;
    Extra extra;

    public ExtraAdapter(Activity context, List<Extra> extras) {
        super(context,R.layout.model,extras);
        this.extras = extras;

        this.context = context;
        this.extras = extras;

    }


    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = context.getLayoutInflater();

        View listViewItem = inflater.inflate(R.layout.model, null, true);



        Extra extra = extras.get(position);

        TextView tvName=(TextView) listViewItem.findViewById(R.id.tvName);
        ImageView ivPicture =(ImageView) listViewItem.findViewById(R.id.ivPicture);
        TextView tvDescription=(TextView)listViewItem.findViewById(R.id.tvDescription);

        tvName.setText(extra.getName());
        tvDescription.setText(extra.getDescription());
        Glide.with(context)
                .load(extra.getUrl())
                .into(ivPicture);



        return listViewItem;

    }
}
















