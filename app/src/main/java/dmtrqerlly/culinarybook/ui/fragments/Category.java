package dmtrqerlly.culinarybook.ui.fragments;

import android.content.Context;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v4.app.Fragment;

import android.support.v4.app.FragmentTransaction;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.CursorLoader;
import android.support.v4.content.Loader;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import dmtrqerlly.culinarybook.R;
import dmtrqerlly.culinarybook.adapter.CategoryViewAdapter;
import dmtrqerlly.culinarybook.adapter.IOnItemClickListener;
import dmtrqerlly.culinarybook.db.DatabaseHelper;
import dmtrqerlly.culinarybook.db.DatabaseOperation;
import dmtrqerlly.culinarybook.db.IDatabaseConst;
import dmtrqerlly.culinarybook.model.CategoryItem;

public class Category extends Fragment implements LoaderManager.LoaderCallbacks<Cursor> {

    @Override
    public View onCreateView(final LayoutInflater pInflater, final ViewGroup pContainer, final Bundle savedInstanceState) {
        final View view = pInflater.inflate(R.layout.category_fragment, pContainer, false);
        getActivity().getSupportLoaderManager().initLoader(0, null, this);
        return view;
    }

    @Override
    public Loader<Cursor> onCreateLoader(final int pId, final Bundle pArgs) {
        return new CategoryLoader(getActivity());
    }

    @Override
    public void onLoadFinished(final Loader<Cursor> pLoader, final Cursor pCursor) {
        final RecyclerView recyclerView = (RecyclerView)getActivity().findViewById(R.id.category_list);
        final StaggeredGridLayoutManager staggeredLayoutManager = new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL);

        final List<CategoryItem> list = new DatabaseOperation().getCategory(pCursor);
        final CategoryViewAdapter adapter = new CategoryViewAdapter(getContext(), list);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(staggeredLayoutManager);
        recyclerView.setAdapter(adapter);
        adapter.setOnItemClickListener(new IOnItemClickListener() {

            @Override
            public void onItemClick(final View view, final int position) {
                final FragmentTransaction ft = getActivity().getSupportFragmentManager().beginTransaction();
                ft.setCustomAnimations(R.anim.animation_fragments, 0);
                ft.replace(R.id.main_fragment, new All());
                ft.commit();
            }
        });

        getActivity().getSupportLoaderManager().destroyLoader(0);
    }

    @Override
    public void onLoaderReset(final Loader<Cursor> pLoader) {
    }

    static class CategoryLoader extends CursorLoader {

        Context mContext;

        CategoryLoader(final Context pContext) {
            super(pContext);
            mContext = pContext;
        }

        @Override
        public Cursor loadInBackground() {
            final DatabaseHelper databaseHelper = new DatabaseHelper(mContext);
            databaseHelper.openDB();
            return databaseHelper.QueryData(IDatabaseConst.SHOW_CATEGORIES);
        }
    }
}