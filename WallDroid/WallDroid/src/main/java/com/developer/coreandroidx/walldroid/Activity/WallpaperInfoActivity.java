package com.developer.coreandroidx.walldroid.Activity;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.core.content.FileProvider;

import android.Manifest;
import android.app.Activity;
import android.app.DownloadManager;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.Settings;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.airbnb.lottie.utils.Utils;
import com.bumptech.glide.Glide;
import com.bumptech.glide.request.target.SimpleTarget;
import com.bumptech.glide.request.transition.Transition;
import com.developer.coreandroidx.walldroid.Model.WallpaperJsonResults;
import com.developer.coreandroidx.walldroid.R;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.nio.channels.InterruptedByTimeoutException;
import java.util.Date;

import de.hdodenhof.circleimageview.CircleImageView;

public class WallpaperInfoActivity extends AppCompatActivity {

    private ImageView wallpaper;
    private CircleImageView wallpaper_user_image;
    private TextView wallpaper_username, wallpaper_tags, wallpaper_likes, wallpaper_favorites, wallpaper_comments, wallpaper_downloads, share_wallpaper, download_wallpaper;

    private Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wallpaper_info);

        intent = getIntent();
        initializeViews();

        final String wallpaperUrl = intent.getStringExtra("imageUrl");
        String userImageUrl = intent.getStringExtra("userImageUrl");
        String username = intent.getStringExtra("username");
        int likes = intent.getIntExtra("likes", 0);
        int favorites = intent.getIntExtra("favorites", 0);
        int views = intent.getIntExtra("views", 0);
        final int downloads = intent.getIntExtra("downloads", 0);
        int comments = intent.getIntExtra("comments", 0);
        String tags = intent.getStringExtra("tags");


        Glide.with(getApplicationContext()).load(wallpaperUrl).fitCenter().into(wallpaper);
        Glide.with(getApplicationContext()).load(userImageUrl).fitCenter().placeholder(R.drawable.ic_person).into(wallpaper_user_image);

        wallpaper_username.setText(username);
        wallpaper_tags.setText(tags);
        wallpaper_likes.setText(String.valueOf(likes));
        wallpaper_favorites.setText(String.valueOf(favorites));
        wallpaper_comments.setText(String.valueOf(comments));
        wallpaper_downloads.setText(String.valueOf(downloads));

        download_wallpaper.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (ContextCompat.checkSelfPermission(WallpaperInfoActivity.this,
                        Manifest.permission.WRITE_EXTERNAL_STORAGE)
                        != PackageManager.PERMISSION_GRANTED) {

                    if (ActivityCompat.shouldShowRequestPermissionRationale(WallpaperInfoActivity.this, Manifest.permission.WRITE_EXTERNAL_STORAGE)) {
                        Intent intent = new Intent();
                        intent.setAction(Settings.ACTION_APPLICATION_DETAILS_SETTINGS);

                        Uri uri = Uri.fromParts("package", getApplicationContext().getPackageName(), null);
                        intent.setData(uri);

                        Toast.makeText(WallpaperInfoActivity.this, "save", Toast.LENGTH_SHORT).show();

                        startActivity(intent);
                    } else {
                        ActivityCompat.requestPermissions(WallpaperInfoActivity.this,
                                new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.READ_EXTERNAL_STORAGE},
                                3621);
                    }
                } else {
                }

                DownloadManager.Request request = new DownloadManager.Request(Uri.parse(wallpaperUrl));
                request.allowScanningByMediaScanner();
                request.setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE_NOTIFY_COMPLETED);

                Toast.makeText(WallpaperInfoActivity.this, String.valueOf(Environment.getExternalStorageDirectory()), Toast.LENGTH_SHORT).show();

                request.setDestinationInExternalFilesDir(getApplicationContext(), Environment.getExternalStorageDirectory() + "/Walldroid", new Date().toString() + ".png");
                DownloadManager dm = (DownloadManager) getSystemService(DOWNLOAD_SERVICE);
                dm.enqueue(request);
                Toast.makeText(getApplicationContext(), "Downloading File", Toast.LENGTH_LONG).show();
            }
        });

    }

    private void initializeViews() {

        wallpaper = findViewById(R.id.wallpaper);
        wallpaper_user_image = findViewById(R.id.wallpaper_user_image);
        wallpaper_username = findViewById(R.id.wallpaper_user_name);
        wallpaper_tags = findViewById(R.id.wallpaper_tags);
        wallpaper_likes = findViewById(R.id.wallpaper_likes);
        wallpaper_favorites = findViewById(R.id.wallpaper_favorites);
        wallpaper_comments = findViewById(R.id.wallpaper_comments);
        wallpaper_downloads = findViewById(R.id.wallpaper_downloads);
        download_wallpaper = findViewById(R.id.download_wallpaper);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        switch (requestCode) {
            case 3621: {
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                } else {
                }
            }
        }
    }
}
