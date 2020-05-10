package com.developer.coreandroidx.walldroid.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.developer.coreandroidx.walldroid.Adapter.WallpaperAdapter;
import com.developer.coreandroidx.walldroid.Helper.ApplicationController;
import com.developer.coreandroidx.walldroid.Model.WallpaperJsonResults;
import com.developer.coreandroidx.walldroid.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import es.dmoral.toasty.Toasty;

public class WallpapersActivity extends AppCompatActivity implements WallpaperAdapter.OnItemClickListener {

    private EditText wallpaper_search_bar;
    private RecyclerView wallpaper_recycler_list;
    private ImageButton search_btn;

    private String searchedWord;
    private WallpaperAdapter wallpaperAdapter;
    private ArrayList<WallpaperJsonResults> mWallpaperJsonResults;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wallpapers);

        initializeViews();
        mWallpaperJsonResults = new ArrayList<>();
        fetchWallpapers();

        search_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (wallpaper_search_bar.getText().toString().isEmpty()) {

                    ///Do nothing;

                } else {

                    fetchWallpapersOnSearch();

                }

            }
        });
    }

    private void initializeViews() {

        wallpaper_search_bar = findViewById(R.id.wallpaper_search_bar);
        search_btn = findViewById(R.id.btn_search);
        wallpaper_recycler_list = findViewById(R.id.wallpaperRecyclerList);
        wallpaper_recycler_list.setHasFixedSize(true);
        wallpaper_recycler_list.setLayoutManager(new LinearLayoutManager(this));

    }

    private void fetchWallpapers() {

        RequestQueue requestQueue = Volley.newRequestQueue(WallpapersActivity.this);

        searchedWord = wallpaper_search_bar.getText().toString();

        String pixabayUrl = ApplicationController.BASE_URL + "?key=" + ApplicationController.API_KEY + "?q=" + ApplicationController.WALLPAPER + "&image_type=" + ApplicationController.IMAGE_TYPE + "&pretty=" + ApplicationController.PRETTY;

        JsonObjectRequest wallpaperObjectRequest = new JsonObjectRequest(Request.Method.GET, pixabayUrl, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {

                try {

                    JSONArray jsonArray = response.getJSONArray("hits");
                    for (int i = 0; i < jsonArray.length(); i++) {

                        JSONObject hitsJsonObject = jsonArray.getJSONObject(i);
                        int wallpaper_id = hitsJsonObject.getInt(ApplicationController.WALLPAPER_ID);
                        String wallpaper = hitsJsonObject.getString("webformatURL");
                        String wallpaperUserImage = hitsJsonObject.getString("userImageURL");
                        int wallpaperComments = hitsJsonObject.getInt(ApplicationController.WALLPAPER_COMMENTS);
                        String wallpaperTags = hitsJsonObject.getString(ApplicationController.WALLPAPER_TAGS);
                        String wallpaperUserName = hitsJsonObject.getString("user");
                        int wallpaperLikes = hitsJsonObject.getInt("likes");
                        int wallpaperFavorites = hitsJsonObject.getInt("favorites");
                        int wallpaperViews = hitsJsonObject.getInt("views");
                        int wallpaperDownloads = hitsJsonObject.getInt("downloads");

                        mWallpaperJsonResults.add(new WallpaperJsonResults(wallpaper_id, wallpaperUserName, wallpaperUserImage, wallpaper, wallpaperTags, wallpaperLikes, wallpaperFavorites, wallpaperViews, wallpaperDownloads, wallpaperComments));
                    }

                    wallpaperAdapter = new WallpaperAdapter(WallpapersActivity.this, mWallpaperJsonResults);
                    wallpaper_recycler_list.setAdapter(wallpaperAdapter);
                    wallpaperAdapter.setOnItemClickListener(WallpapersActivity.this);

                } catch (JSONException je) {

                    je.printStackTrace();

                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

                Toasty.error(getApplicationContext(), "Volley Error: " + error, Toast.LENGTH_SHORT).show();
                Log.d("TAG", "onErrorResponse: " + error);

            }
        });
        requestQueue.add(wallpaperObjectRequest);
    }

    private void fetchWallpapersOnSearch() {
        RequestQueue requestQueue = Volley.newRequestQueue(WallpapersActivity.this);

        searchedWord = wallpaper_search_bar.getText().toString();

        String pixabayUrl = ApplicationController.BASE_URL+"?key="+ApplicationController.API_KEY+"&q="+searchedWord+"&image_type="+ApplicationController.IMAGE_TYPE+"&pretty="+ApplicationController.PRETTY;

        mWallpaperJsonResults.clear();
        JsonObjectRequest wallpaperObjectRequest = new JsonObjectRequest(Request.Method.GET, pixabayUrl, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {

                try {
                    JSONArray jsonArray = response.getJSONArray("hits");
                    for (int i = 0; i < jsonArray.length(); i++) {

                        JSONObject hitsJsonObject = jsonArray.getJSONObject(i);
                        int wallpaper_id = hitsJsonObject.getInt(ApplicationController.WALLPAPER_ID);
                        String wallpaper = hitsJsonObject.getString(ApplicationController.WALLPAPER_WEB_FORMAT_URL);
                        String wallpaperUserImage = hitsJsonObject.getString(ApplicationController.WALLPAPER_USER_IMAGE_URL);
                        String wallpaperUserName = hitsJsonObject.getString(ApplicationController.WALLPAPER_USERNAME);
                        int wallpaperLikes = hitsJsonObject.getInt(ApplicationController.WALLPAPER_LIKES);
                        int wallpaperComments = hitsJsonObject.getInt(ApplicationController.WALLPAPER_COMMENTS);
                        String wallpaperTags = hitsJsonObject.getString(ApplicationController.WALLPAPER_TAGS);
                        int wallpaperFavorites = hitsJsonObject.getInt(ApplicationController.WALLPAPER_FAVORITES);
                        int wallpaperViews = hitsJsonObject.getInt(ApplicationController.WALLPAPER_VIEWS);
                        int wallpaperDownloads = hitsJsonObject.getInt(ApplicationController.WALLPAPER_DOWNLOADS);

                        mWallpaperJsonResults.add(new WallpaperJsonResults(wallpaper_id, wallpaperUserName, wallpaperUserImage, wallpaper,wallpaperTags, wallpaperLikes, wallpaperFavorites, wallpaperViews, wallpaperDownloads, wallpaperComments));
                    }

                    wallpaperAdapter = new WallpaperAdapter(WallpapersActivity.this, mWallpaperJsonResults);
                    wallpaperAdapter.notifyDataSetChanged();
                    wallpaper_recycler_list.setAdapter(wallpaperAdapter);
                    wallpaperAdapter.setOnItemClickListener(WallpapersActivity.this);

                } catch (JSONException je) {

                    je.printStackTrace();

                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

                Toasty.error(getApplicationContext(), "Volley Error: " + error, Toast.LENGTH_SHORT).show();

            }
        });
        requestQueue.add(wallpaperObjectRequest);
    }

    @Override
    public void OnItemClick(int position) {

        Intent infoIntent = new Intent(this, WallpaperInfoActivity.class);
        WallpaperJsonResults clickedItemResults = mWallpaperJsonResults.get(position);

        infoIntent.putExtra("id", clickedItemResults.getWallpaper_id());
        infoIntent.putExtra("imageUrl", clickedItemResults.getWallpaper());
        infoIntent.putExtra("userImageUrl", clickedItemResults.getWallpaper_user_image());
        infoIntent.putExtra("username", clickedItemResults.getWallpaper_username());
        infoIntent.putExtra("likes", clickedItemResults.getWallpaper_likes());
        infoIntent.putExtra("favorites", clickedItemResults.getWallpaper_favorites());
        infoIntent.putExtra("views", clickedItemResults.getWallpaper_views());
        infoIntent.putExtra("downloads", clickedItemResults.getWallpaper_downloads());
        infoIntent.putExtra("comments", clickedItemResults.getWallpaper_comments());
        infoIntent.putExtra("tags", clickedItemResults.getWallpaper_tags());

        startActivity(infoIntent);

    }
}
