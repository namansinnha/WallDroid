package com.developer.coreandroidx.walldroid.Activity;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import com.developer.coreandroidx.walldroid.R;

public class SplashActivity extends AppCompatActivity {

    private boolean revisited = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {

                revisited = true;
                gotoWallpaperActivity();
            }
        }, 2000);
    }


    @Override
    protected void onStart() {
        super.onStart();

        if (revisited) {

            gotoWallpaperActivity();

        }

    }

    private void gotoWallpaperActivity() {

        Intent intent = new Intent(SplashActivity.this, WallpapersActivity.class);
        intent.putExtra("revisited", revisited);
        startActivity(intent);
        finish();

    }

}
