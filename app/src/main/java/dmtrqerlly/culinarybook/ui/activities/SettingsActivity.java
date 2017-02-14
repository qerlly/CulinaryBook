package dmtrqerlly.culinarybook.ui.activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;

import dmtrqerlly.culinarybook.R;
import dmtrqerlly.culinarybook.ui.fragments.Preferences;
import dmtrqerlly.culinarybook.utils.PreferenceHelper;

public class SettingsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        new PreferenceHelper().themeSwitch(this);
        setContentView(R.layout.settings_layout);

        final Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar_settings);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(final View v) {
                finish();
            }
        });

        getFragmentManager().beginTransaction().replace(R.id.settings_fragment, new Preferences()).commit();
    }
}