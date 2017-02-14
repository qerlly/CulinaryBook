package dmtrqerlly.culinarybook.ui.fragments;

import android.app.Activity;
import android.content.Context;
import android.database.Cursor;
import android.hardware.Sensor;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.CursorLoader;
import android.support.v4.content.Loader;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.List;

import dmtrqerlly.culinarybook.R;
import dmtrqerlly.culinarybook.db.DatabaseHelper;
import dmtrqerlly.culinarybook.db.DatabaseOperation;
import dmtrqerlly.culinarybook.db.IDatabaseConst;
import dmtrqerlly.culinarybook.model.Item;
import dmtrqerlly.culinarybook.utils.Shaker;

public class Random extends Fragment implements LoaderManager.LoaderCallbacks<Cursor> {

    private SensorManager mSensorManager;
    private Sensor mAccelerometer;
    private Shaker mShakeDetector;
    int mRandomValue = 0;

    @Override
    public View onCreateView(final LayoutInflater pInflater, final ViewGroup pContainer, final Bundle pSavedInstanceState) {
        final View view = pInflater.inflate(R.layout.random_fragment, pContainer, false);
        mSensorManager = (SensorManager) getActivity().getSystemService(Context.SENSOR_SERVICE);
        mAccelerometer = mSensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        mShakeDetector = new Shaker();
        mShakeDetector.setOnShakeListener(new Shaker.OnShakeListener() {

            @Override
            public void onShake(final int pRandomValue) {
                if(!getActivity().getSupportLoaderManager().getLoader(3).isStarted()) {
                    mRandomValue = pRandomValue;
                    getActivity().getSupportLoaderManager().initLoader(3, null, Random.this);
                }
            }

        });
        return view;
    }

    @Override
    public Loader<Cursor> onCreateLoader(final int pId, final Bundle pArgs) {
        return new RandomLoader(getActivity());
    }

    @Override
    public void onLoadFinished(final Loader<Cursor> pLoader, final Cursor pCursor) {

        final Activity activity = getActivity();
        final ImageView imageView = (ImageView) activity.findViewById(R.id.random_image);
        final TextView name = (TextView) activity.findViewById(R.id.random_name);
        final TextView viewIng = (TextView) activity.findViewById(R.id.random_show_ing);
        final TextView viewRec = (TextView) activity.findViewById(R.id.random_show_rec);
        final TextView details = (TextView) activity.findViewById(R.id.random_ingredients);
        final TextView recipe = (TextView) activity.findViewById(R.id.random_recipe);
        final CheckBox buttonFavorite = (CheckBox) activity.findViewById(R.id.random_button_favorite);

        final List<Item> list = new DatabaseOperation().getRecipes(pCursor);
        final Item item = list.get(mRandomValue);

       /* if (new DatabaseOperation().checkFavorite(getContext(), item.getId())) {
            buttonFavorite.setChecked(true);
        } else {
            buttonFavorite.setChecked(false);
        }
*/
        Picasso.with(getContext()).load(getContext().getFilesDir() + item.getImage()).error(R.drawable.splash_cook).into(imageView);
        viewIng.setVisibility(View.VISIBLE);
        buttonFavorite.setVisibility(View.VISIBLE);
        viewRec.setVisibility(View.VISIBLE);
        name.setText(item.getName());
        details.setText(item.getDetails());
        recipe.setText(item.getRecipe());

        buttonFavorite.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(final View pView) {
                if (buttonFavorite.isChecked()) {

                } else {

                }
            }
        });
        getActivity().getSupportLoaderManager().destroyLoader(1);
    }

    @Override
    public void onLoaderReset(final Loader<Cursor> pLoader) {
    }

    static class RandomLoader extends CursorLoader {

        Context mContext;

        RandomLoader(final Context pContext) {
            super(pContext);
            mContext = pContext;
        }

        @Override
        public Cursor loadInBackground() {
            final DatabaseHelper databaseHelper = new DatabaseHelper(mContext);
            databaseHelper.openDB();
            return databaseHelper.QueryData(IDatabaseConst.SHOW_RANDOM);
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        mSensorManager.registerListener(mShakeDetector, mAccelerometer, SensorManager.SENSOR_DELAY_UI);
    }

    @Override
    public void onPause() {
        mSensorManager.unregisterListener(mShakeDetector);
        super.onPause();
    }
}