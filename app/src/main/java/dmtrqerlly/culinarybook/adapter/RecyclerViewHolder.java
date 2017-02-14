package dmtrqerlly.culinarybook.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import dmtrqerlly.culinarybook.R;

public class RecyclerViewHolder extends RecyclerView.ViewHolder {

    public ImageView mImageView;
    public TextView mName;
    public LinearLayout mMainLayout;
    public LinearLayout mNameLayout;

    public RecyclerViewHolder(final View itemView) {
        super(itemView);
        mImageView = (ImageView)itemView.findViewById(R.id.image_item);
        mName = (TextView)itemView.findViewById(R.id.name_item);
        mNameLayout = (LinearLayout)itemView.findViewById(R.id.name_holder);
    }
}
