package dmtrqerlly.culinarybook.ui.activities;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.StrictMode;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import dmtrqerlly.culinarybook.BuildConfig;
import dmtrqerlly.culinarybook.R;
import dmtrqerlly.culinarybook.ui.fragments.Category;
import dmtrqerlly.culinarybook.ui.fragments.Favorite;
import dmtrqerlly.culinarybook.ui.fragments.Home;
import dmtrqerlly.culinarybook.ui.fragments.Random;
import dmtrqerlly.culinarybook.utils.PreferenceHelper;

public class MainActivity extends AppCompatActivity {

    private static final String APPLICATION_URL = "https://github.com/qerlly";
    private DrawerLayout mDrawerLayout;
    private ActionBarDrawerToggle mDrawerToggle;

    @Override
    protected void onCreate(final Bundle pSavedInstanceState) {

        if (BuildConfig.DEBUG) {
            StrictMode.setThreadPolicy(new StrictMode.ThreadPolicy.Builder()
                    .detectDiskReads()
                    .detectDiskWrites()
                    .detectNetwork()
                    .penaltyLog()
                    .build());
            StrictMode.setVmPolicy(new StrictMode.VmPolicy.Builder()
                    .detectLeakedSqlLiteObjects()
                    .detectLeakedClosableObjects()
                    .penaltyLog()
                    .penaltyDeath()
                    .build());
        }

        super.onCreate(pSavedInstanceState);
        new PreferenceHelper().themeSwitch(this);
        setContentView(R.layout.main_layout);

        initView(pSavedInstanceState);
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        final Bundle mBundle = new Bundle();
        onSaveInstanceState(mBundle);
        final Intent intent = new Intent(this, MainActivity.class);
        intent.putExtra("bundle", mBundle);
        startActivity(intent);
        finish();
    }

    @Override
    protected void onPostCreate(final Bundle pSavedInstanceState) {
        super.onPostCreate(pSavedInstanceState);
        mDrawerToggle.syncState();
    }

    @Override
    public boolean onOptionsItemSelected(final MenuItem pItem) {
        return mDrawerToggle.onOptionsItemSelected(pItem);
    }

    private void initView(final Bundle pSavedInstanceState) {
        final Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        final ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
        }
        toolbar.setTitle(R.string.nav_home);

        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        mDrawerToggle = setupDrawerToggle(mDrawerLayout);
        mDrawerLayout.addDrawerListener(mDrawerToggle);

        final NavigationView view = (NavigationView) findViewById(R.id.navigation_view);
        view.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {

            @Override
            public boolean onNavigationItemSelected(@NonNull final MenuItem pMenuItem) {

                if (pMenuItem.getGroupId() == R.id.nav_footer) {
                    selectDrawerItem(pMenuItem.getItemId(), R.id.nav_footer);
                } else {
                    selectDrawerItem(pMenuItem.getItemId(), R.id.nav_header);
                    pMenuItem.setChecked(true);
                }
                mDrawerLayout.closeDrawers();
                return true;
            }
        });

        if (pSavedInstanceState == null) {
            selectDrawerItem(R.id.nav_home, R.id.nav_header);
        }
    }

    private ActionBarDrawerToggle setupDrawerToggle(final DrawerLayout pDrawerLayout) {
        return new ActionBarDrawerToggle(this, pDrawerLayout, null, R.string.drawer_open, R.string.drawer_close);
    }

    private void selectDrawerItem(final int pMenuItemId, final int pMenuItemGroup) {

        Fragment fragment = new Home();
        switch (pMenuItemId) {
            case R.id.nav_home:
                fragment = new Home();
                setTitle(R.string.nav_home);
                break;
            case R.id.nav_all:
                fragment = new Category();
                setTitle(R.string.nav_all);
                break;
            case R.id.nav_favorite:
                fragment = new Favorite();
                setTitle(R.string.nav_favorite);
                break;
            case R.id.nav_random:
                fragment = new Random();
                setTitle(R.string.nav_random);
                break;
            case R.id.nav_settings:
                startActivity(new Intent(this, SettingsActivity.class));
                break;
            case R.id.nav_open_source:
                final Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(APPLICATION_URL));
                startActivity(intent);
                break;
            case R.id.nav_exit:
                finish();
                break;
        }
        if (pMenuItemGroup == R.id.nav_header) {
            final FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
            ft.setCustomAnimations(R.anim.animation_fragments_menu, 0);
            ft.replace(R.id.main_fragment, fragment);
            ft.commit();
        }
    }
}