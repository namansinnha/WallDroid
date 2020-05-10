package com.developer.coreandroidx.walldroid.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.developer.coreandroidx.walldroid.Model.WallpaperJsonResults;
import com.developer.coreandroidx.walldroid.R;

import java.util.ArrayList;

import de.hdodenhof.circleimageview.CircleImageView;

public class WallpaperAdapter extends RecyclerView.Adapter<WallpaperAdapter.WallpaperViewHolder> {

    private Context mContext;
    private ArrayList<WallpaperJsonResults> mWallpaperJsonResults;
    private OnItemClickListener mListener;

    public interface OnItemClickListener {
        void OnItemClick(int position);
    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        mListener = listener;
    }

    public WallpaperAdapter(Context mContext, ArrayList<WallpaperJsonResults> mWallpaperJsonResults) {
        this.mContext = mContext;
        this.mWallpaperJsonResults = mWallpaperJsonResults;
    }

    @NonNull
    @Override
    public WallpaperAdapter.WallpaperViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(mContext).inflate(R.layout.wallpaper_recycler_layout_items, parent, false);
        return new WallpaperViewHolder(view);

    }

    @Override
    public void onBindViewHolder(@NonNull WallpaperAdapter.WallpaperViewHolder holder, int position) {

        WallpaperJsonResults wallpaperJsonResults = mWallpaperJsonResults.get(position);

        String wallpaper = wallpaperJsonResults.getWallpaper();
        String wallpaperUserImage = wallpaperJsonResults.getWallpaper_user_image();
        String wallpaperUserName = wallpaperJsonResults.getWallpaper_username();
        int wallpaperLikes = wallpaperJsonResults.getWallpaper_likes();
        int wallpaperFavorites = wallpaperJsonResults.getWallpaper_favorites();
        int wallpaperViews = wallpaperJsonResults.getWallpaper_views();
        int wallpaperDownloads = wallpaperJsonResults.getWallpaper_downloads();

        Glide.with(mContext).load(wallpaper).fitCenter().into(holder.wallpaper);
        Glide.with(mContext).load(wallpaperUserImage).fitCenter().placeholder(R.drawable.ic_person).into(holder.wallpaper_userimage);

        holder.wallpaper_username.setText(wallpaperUserName);
        holder.wallpaper_likes.setText(String.valueOf(wallpaperLikes));
        holder.wallpaper_favorites.setText(String.valueOf(wallpaperFavorites));
        holder.wallpaper_views.setText(String.valueOf(wallpaperViews));
        holder.wallpaper_downloads.setText(String.valueOf(wallpaperDownloads));

    }

    @Override
    public int getItemCount() {
        return mWallpaperJsonResults.size();
    }

    public class WallpaperViewHolder extends RecyclerView.ViewHolder {

        ImageView wallpaper;
        CircleImageView wallpaper_userimage;
        TextView wallpaper_username, wallpaper_likes, wallpaper_favorites, wallpaper_views, wallpaper_downloads;


        public WallpaperViewHolder(@NonNull View itemView) {
            super(itemView);

            wallpaper = itemView.findViewById(R.id.wallpaper);
            wallpaper_userimage = itemView.findViewById(R.id.wallpaper_user_image);
            wallpaper_username = itemView.findViewById(R.id.wallpaper_user_name);
            wallpaper_likes = itemView.findViewById(R.id.wallpaper_likes);
            wallpaper_favorites = itemView.findViewById(R.id.wallpaper_favorites);
            wallpaper_views = itemView.findViewById(R.id.wallpaper_views);
            wallpaper_downloads = itemView.findViewById(R.id.wallpaper_downloads);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    if (mListener != null) {

                        int position = getAdapterPosition();

                        if (position != RecyclerView.NO_POSITION) {

                            mListener.OnItemClick(position);

                        }
                    }

                }
            });

        }
    }
}
