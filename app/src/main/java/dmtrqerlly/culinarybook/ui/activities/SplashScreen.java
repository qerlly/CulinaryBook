package dmtrqerlly.culinarybook.ui.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.IOException;

import dmtrqerlly.culinarybook.R;
import dmtrqerlly.culinarybook.db.DatabaseHelper;
import dmtrqerlly.culinarybook.utils.PreferenceHelper;

public class SplashScreen extends AppCompatActivity {

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        new PreferenceHelper().themeSwitch(this);
        setContentView(R.layout.splash_screen);

        final ImageView imageView = (ImageView) findViewById(R.id.image_splash);
        final Animation animation_start = AnimationUtils.loadAnimation(getBaseContext(), R.anim.animation_splash);
        final Animation animationFadeOut = AnimationUtils.loadAnimation(getBaseContext(), R.anim.animation_out);
        final DatabaseHelper dbHelper = new DatabaseHelper(this);

        try {
            dbHelper.copyDB();
            dbHelper.copyImages();
        } catch (final IOException pE) {
            Toast.makeText(SplashScreen.this, R.string.error_db, Toast.LENGTH_SHORT).show();
        }

        imageView.startAnimation(animation_start);

        animation_start.setAnimationListener(new Animation.AnimationListener() {

            @Override
            public void onAnimationStart(final Animation pAnimation) {

            }

            @Override
            public void onAnimationEnd(final Animation pAnimation) {
                imageView.startAnimation(animationFadeOut);
                finish();
                startActivity(new Intent(SplashScreen.this, MainActivity.class));
            }

            @Override
            public void onAnimationRepeat(final Animation pAnimation) {

            }
        });
    }
}
