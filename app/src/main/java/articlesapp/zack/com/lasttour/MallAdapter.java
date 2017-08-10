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

public class MallAdapter extends ArrayAdapter<Mall> {

    private Activity context;
    private List<Mall> malls;
    Mall mall;

    public MallAdapter(Activity context, List<Mall> malls) {
        super(context,R.layout.model,malls);
        this.malls = malls;

        this.context = context;
        this.malls = malls;

    }


    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = context.getLayoutInflater();

        View listViewItem = inflater.inflate(R.layout.model, null, true);



        Mall mall = malls.get(position);

        TextView tvName=(TextView) listViewItem.findViewById(R.id.tvName);
        ImageView ivPicture =(ImageView) listViewItem.findViewById(R.id.ivPicture);
        TextView tvDescription=(TextView)listViewItem.findViewById(R.id.tvDescription);

        tvName.setText(mall.getName());
        tvDescription.setText(mall.getDescription());
        Glide.with(context)
                .load(mall.getUrl())
                .into(ivPicture);



        return listViewItem;

    }
}










