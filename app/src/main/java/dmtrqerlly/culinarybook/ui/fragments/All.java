package dmtrqerlly.culinarybook.ui.fragments;

import android.app.SearchManager;
import android.content.Context;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.CursorLoader;
import android.support.v4.content.Loader;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import dmtrqerlly.culinarybook.R;
import dmtrqerlly.culinarybook.adapter.IOnItemClickListener;
import dmtrqerlly.culinarybook.adapter.RecyclerViewAdapter;
import dmtrqerlly.culinarybook.db.DatabaseHelper;
import dmtrqerlly.culinarybook.db.DatabaseOperation;
import dmtrqerlly.culinarybook.db.IDatabaseConst;
import dmtrqerlly.culinarybook.model.Item;

public class All extends Fragment implements LoaderManager.LoaderCallbacks<Cursor>, SearchView.OnQueryTextListener {

    private StaggeredGridLayoutManager mStaggeredLayoutManager;
    private boolean mIcon = true;
    private RecyclerViewAdapter mAdapter;

    @Override
    public View onCreateView(final LayoutInflater pInflater, final ViewGroup pContainer, final Bundle pSavedInstanceState) {
        final View view = pInflater.inflate(R.layout.all_fragment, pContainer, false);
        mStaggeredLayoutManager = new StaggeredGridLayoutManager(1, StaggeredGridLayoutManager.VERTICAL);
        getActivity().getSupportLoaderManager().initLoader(1, null, this);
        setHasOptionsMenu(true);
        return view;
    }

    @Override
    public void onCreateOptionsMenu(final Menu menu, final MenuInflater inflater) {
        inflater.inflate(R.menu.toolbar_menu, menu);
        final SearchManager searchManager = (SearchManager)getActivity().getSystemService(Context.SEARCH_SERVICE);
        final MenuItem searchMenuItem = menu.findItem(R.id.action_search);
        final SearchView searchView = (SearchView) searchMenuItem.getActionView();

        searchView.setSearchableInfo(searchManager.getSearchableInfo(getActivity().getComponentName()));
        searchView.setSubmitButtonEnabled(true);
        searchView.setOnQueryTextListener(this);

        super.onCreateOptionsMenu(menu, inflater);
    }

    @Override
    public boolean onQueryTextSubmit(final String pQuery) {
        return false;
    }

    @Override
    public boolean onQueryTextChange(final String pNewText) {
        mAdapter.getFilter().filter(pNewText);
        return true;
    }

    @Override
    public void onPrepareOptionsMenu(final Menu menu) {

        final MenuItem viewShow = menu.findItem(R.id.view_show);

        viewShow.setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {

            @Override
            public boolean onMenuItemClick(final MenuItem item) {
                if (mIcon) {
                    viewShow.setIcon(R.drawable.view_agenda);
                    mStaggeredLayoutManager.setSpanCount(2);
                    mIcon = false;
                } else {
                    viewShow.setIcon(R.drawable.view_grid);
                    mStaggeredLayoutManager.setSpanCount(1);
                    mIcon = true;
                }
                return false;
            }
        });
        super.onPrepareOptionsMenu(menu);
    }

    @Override
    public Loader<Cursor> onCreateLoader(final int pId, final Bundle pArgs) {
        return new AllLoader(getActivity());
    }

    @Override
    public void onLoadFinished(final Loader<Cursor> pLoader, final Cursor pCursor) {
        final RecyclerView recyclerView = (RecyclerView) getActivity().findViewById(R.id.list);

        final List<Item> list = new DatabaseOperation().getRecipes(pCursor);
        mAdapter = new RecyclerViewAdapter(getContext(), list);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(mStaggeredLayoutManager);
        recyclerView.setAdapter(mAdapter);
        mAdapter.setOnItemClickListener(new IOnItemClickListener() {

            @Override
            public void onItemClick(final View view, final int position) {
                getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.main_fragment, new Recipe()).commit();
            }
        });

        getActivity().getSupportLoaderManager().destroyLoader(1);
    }

    @Override
    public void onLoaderReset(final Loader<Cursor> pLoader) {
    }

    static class AllLoader extends CursorLoader {

        Context mContext;

        AllLoader(final Context pContext) {
            super(pContext);
            mContext = pContext;
        }

        @Override
        public Cursor loadInBackground() {
            final DatabaseHelper databaseHelper = new DatabaseHelper(mContext);
            databaseHelper.openDB();
            return databaseHelper.QueryData(IDatabaseConst.SHOW_ALL);
        }
    }
}