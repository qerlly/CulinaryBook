package dmtrqerlly.culinarybook.db;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteException;
import android.os.AsyncTask;

import java.util.ArrayList;
import java.util.List;

import dmtrqerlly.culinarybook.model.CategoryItem;
import dmtrqerlly.culinarybook.model.Item;

public class DatabaseOperation {

    public List<Item> getRecipes(final Cursor pCursor) {
        final List<Item> arrayList = new ArrayList<>();
        try {
            if (pCursor.moveToFirst()) {
                final int id = pCursor.getColumnIndex(IDatabaseConst.ID);
                final int name = pCursor.getColumnIndex(IDatabaseConst.NAME);
                final int detail = pCursor.getColumnIndex(IDatabaseConst.DETAILS);
                final int recipe = pCursor.getColumnIndex(IDatabaseConst.RECIPE);
                final int image = pCursor.getColumnIndex(IDatabaseConst.IMAGE);
                final int type = pCursor.getColumnIndex(IDatabaseConst.TYPE);
                do {
                    final Item item = new Item(pCursor.getInt(id), pCursor.getString(image), pCursor.getString(name),
                            pCursor.getString(detail), pCursor.getString(recipe), pCursor.getInt(type));
                    arrayList.add(item);
                } while (pCursor.moveToNext());
                pCursor.close();
            }
        } catch (final SQLiteException e) {
            e.printStackTrace();
        }
        return arrayList;
    }

    public List<CategoryItem> getCategory(final Cursor pCursor) {
        final List<CategoryItem> arrayList = new ArrayList<>();
        try {
            if (pCursor.moveToFirst()) {
                final int id = pCursor.getColumnIndex(IDatabaseConst.ID);
                final int category = pCursor.getColumnIndex(IDatabaseConst.CATEGORY_NAME);
                final int icon = pCursor.getColumnIndex(IDatabaseConst.ICON);
                do {
                    final CategoryItem item = new CategoryItem(pCursor.getInt(id), pCursor.getString(category), pCursor.getString(icon));
                    arrayList.add(item);
                } while (pCursor.moveToNext());
                pCursor.close();
            }
        } catch (final SQLiteException e) {
            e.printStackTrace();
        }
        return arrayList;
    }
}






/*   private Cursor mCursor;
    private DatabaseHelper mDBHelper;

    public boolean checkFavorite(final Context pContext, final int id) {
        mDBHelper = new DatabaseHelper(pContext);
        try {
            mDBHelper.openDB();
        } catch (final SQLiteException e) {
            e.printStackTrace();
        }
        mCursor = mDBHelper.QueryData(IDatabaseConst.CHECK_FAVORITE + id);
        if (mCursor != null && mCursor.getCount() > 0) {
            mCursor.close();
            mDBHelper.close();
            return true;
        }
        mDBHelper.close();
        return false;
    }*/