package dmtrqerlly.culinarybook.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public class DatabaseHelper extends SQLiteOpenHelper {

    private final Context mContext;
    private SQLiteDatabase mDatabase;
    private final String mDir;
    private final String mPath;
    private InputStream mInput;
    private OutputStream mOutput;

    public DatabaseHelper(final Context pContext) {
        super(pContext, IDatabaseConst.DB_NAME, null, 1);
        this.mContext = pContext;
        this.mDir = this.mContext.getApplicationInfo().dataDir + "/databases/";
        this.mPath = this.mContext.getApplicationInfo().dataDir + "/databases/" + IDatabaseConst.DB_NAME;
    }

    public void onCreate(final SQLiteDatabase db) {
    }

    public void onUpgrade(final SQLiteDatabase db, final int oldVersion, final int newVersion) {
    }

    public void copyDB() throws IOException {
        final File dir = new File(this.mDir);
        final File database = new File(dir, IDatabaseConst.DB_NAME);
        if (dir.mkdirs() || !database.exists()) {
            mInput = this.mContext.getAssets().open(IDatabaseConst.DB_NAME);
            mOutput = new FileOutputStream(database);
            final byte[] buffer = new byte[1024];
            int length;
            while ((length = mInput.read(buffer)) > 0) {
                mOutput.write(buffer, 0, length);
            }
            mOutput.flush();
            mOutput.close();
            mInput.close();
        }
    }

    public void copyImages() throws IOException {
        final File dirImages = new File(this.mContext.getFilesDir() + "/images/");
        final String[] imagesArray = mContext.getAssets().list("images");
        final String pathImages = mContext.getFilesDir().getPath() + "/images/";
        if (dirImages.mkdirs() || !dirImages.exists()) {
            for (int i = 0; i < imagesArray.length - 2; i++) {
                final File image = new File(pathImages, imagesArray[i]);
                if (!image.exists()) {
                    mInput = mContext.getAssets().open("images/" + imagesArray[i]);
                    mOutput = new FileOutputStream(image);

                    final byte[] buffer = new byte[1024];
                    int length;
                    while ((length = mInput.read(buffer)) > 0) {
                        mOutput.write(buffer, 0, length);
                    }
                }
            }
            mOutput.flush();
            mOutput.close();
            mInput.close();
        }
    }

    public void openDB() {
        mDatabase = SQLiteDatabase.openDatabase(mPath, null, SQLiteDatabase.OPEN_READWRITE); //TODO 55ms задержка
    }

    public Cursor QueryData(final String query) {
        return this.mDatabase.rawQuery(query, null);
    }

    public void addFavorite(final int id) {
        final ContentValues mValues = new ContentValues();
        mValues.put(IDatabaseConst.ID_FAV, id);
        mDatabase = this.getWritableDatabase();
        mDatabase.insert(IDatabaseConst.TABLE_FAV, IDatabaseConst.ID_FAV, mValues);
        mDatabase.close();
    }

    public void deleteFavorite(final int id) {
        mDatabase = this.getWritableDatabase();
        mDatabase.delete(IDatabaseConst.TABLE_FAV, IDatabaseConst.ID_FAV + " = " + id, null);
        mDatabase.close();
    }

    public synchronized void close() {
        if (this.mDatabase != null) {
            this.mDatabase.close();
        }
        super.close();
    }
}