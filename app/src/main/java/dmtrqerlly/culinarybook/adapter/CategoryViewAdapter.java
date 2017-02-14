package dmtrqerlly.culinarybook.adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v7.graphics.Palette;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.squareup.picasso.Picasso;

import java.io.File;
import java.util.Collections;
import java.util.List;

import dmtrqerlly.culinarybook.R;
import dmtrqerlly.culinarybook.model.CategoryItem;
import dmtrqerlly.culinarybook.utils.PreferenceHelper;

public class CategoryViewAdapter extends RecyclerView.Adapter<RecyclerViewHolder> {

    private List<CategoryItem> mItems = Collections.emptyList();
    private IOnItemClickListener mItemClickListener;
    private final Context mContext;

    public CategoryViewAdapter(final Context pContext, final List<CategoryItem> pItems) {
        mContext = pContext;
        mItems = pItems;
    }

    @Override
    public RecyclerViewHolder onCreateViewHolder(final ViewGroup parent, final int viewType) {
        final View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_item, parent, false);
        return new RecyclerViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final RecyclerViewHolder holder, final int pPosition) {

        Picasso.with(mContext).load(new File(mContext.getFilesDir() + mItems.get(pPosition).getIcon())).into(holder.mImageView);
        holder.mName.setText(mItems.get(pPosition).getCategory());
        holder.itemView.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(final View pView) {
                if (mItemClickListener != null) {
                    mItemClickListener.onItemClick(holder.itemView, pPosition);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return mItems.size();
    }

    public void setOnItemClickListener(final IOnItemClickListener mItemClickListener) {
        this.mItemClickListener = mItemClickListener;
    }
}
