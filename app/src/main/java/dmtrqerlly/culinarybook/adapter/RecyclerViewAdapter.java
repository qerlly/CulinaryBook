package dmtrqerlly.culinarybook.adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v7.graphics.Palette;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;

import com.squareup.picasso.Picasso;

import java.io.File;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import dmtrqerlly.culinarybook.R;
import dmtrqerlly.culinarybook.model.Item;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewHolder> implements Filterable {

    private List<Item> mItems = Collections.emptyList();
    private IOnItemClickListener mItemClickListener;
    private final Context mContext;
    private RecipeFilter recipeFilter;

    public RecyclerViewAdapter(final Context context, final List<Item> pItems) {
        mContext = context;
        mItems = pItems;
    }

    @Override
    public RecyclerViewHolder onCreateViewHolder(final ViewGroup parent, final int viewType) {
        final View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_item, parent, false);
        return new RecyclerViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final RecyclerViewHolder holder, final int position) {

        Picasso.with(mContext).load(new File(mContext.getFilesDir() + mItems.get(position).getImage())).error(R.drawable.splash_cook).into(holder.mImageView);
        holder.mName.setText(mItems.get(position).getName());
        holder.itemView.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(final View v) {
                if (mItemClickListener != null) {
                    mItemClickListener.onItemClick(holder.itemView, position);
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

    @Override
    public Filter getFilter() {
        if (recipeFilter == null) {
            recipeFilter = new RecipeFilter();
        }
        return recipeFilter;
    }

    private class RecipeFilter extends Filter {

        @Override
        protected FilterResults performFiltering(final CharSequence pConstraint) {
            final FilterResults filterResults = new FilterResults();
            if (pConstraint != null && pConstraint.length() > 0) {
                final Collection<Item> tempList = new ArrayList<>();

                for (final Item item : mItems) {
                    if (item.getName().toLowerCase().contains(pConstraint.toString().toLowerCase())) {
                        tempList.add(item);
                    }
                }

                filterResults.count = tempList.size();
                filterResults.values = tempList;
            } else {
                filterResults.count = mItems.size();
                filterResults.values = mItems;
            }
            return filterResults;
        }

        @Override
        protected void publishResults(final CharSequence pConstraint, final FilterResults pResults) {
            mItems = (ArrayList<Item>) pResults.values;
            notifyDataSetChanged();
        }
    }
}