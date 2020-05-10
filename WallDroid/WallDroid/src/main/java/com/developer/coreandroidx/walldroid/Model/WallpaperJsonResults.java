package com.developer.coreandroidx.walldroid.Model;

public class WallpaperJsonResults {

    private String wallpaper_username, wallpaper_user_image, wallpaper, wallpaper_tags;
    private int wallpaper_id, wallpaper_likes, wallpaper_favorites, wallpaper_views, wallpaper_downloads, wallpaper_comments;

    public WallpaperJsonResults() {
    }

    public WallpaperJsonResults(int wallpaper_id, String wallpaper_username, String wallpaper_user_image, String wallpaper, String wallpaper_tags, int wallpaper_likes, int wallpaper_favorites, int wallpaper_views, int wallpaper_downloads, int wallpaper_comments) {
        this.wallpaper_id = wallpaper_id;
        this.wallpaper_username = wallpaper_username;
        this.wallpaper_user_image = wallpaper_user_image;
        this.wallpaper = wallpaper;
        this.wallpaper_tags = wallpaper_tags;
        this.wallpaper_likes = wallpaper_likes;
        this.wallpaper_favorites = wallpaper_favorites;
        this.wallpaper_views = wallpaper_views;
        this.wallpaper_downloads = wallpaper_downloads;
        this.wallpaper_comments = wallpaper_comments;
    }

    public int getWallpaper_id() {
        return wallpaper_id;
    }

    public void setWallpaper_id(int wallpaper_id) {
        this.wallpaper_id = wallpaper_id;
    }

    public String getWallpaper_username() {
        return wallpaper_username;
    }

    public void setWallpaper_username(String wallpaper_username) {
        this.wallpaper_username = wallpaper_username;
    }

    public String getWallpaper_user_image() {
        return wallpaper_user_image;
    }

    public void setWallpaper_user_image(String wallpaper_user_image) {
        this.wallpaper_user_image = wallpaper_user_image;
    }

    public String getWallpaper() {
        return wallpaper;
    }

    public void setWallpaper(String wallpaper) {
        this.wallpaper = wallpaper;
    }

    public int getWallpaper_likes() {
        return wallpaper_likes;
    }

    public void setWallpaper_likes(int wallpaper_likes) {
        this.wallpaper_likes = wallpaper_likes;
    }

    public int getWallpaper_favorites() {
        return wallpaper_favorites;
    }

    public void setWallpaper_favorites(int wallpaper_favorites) {
        this.wallpaper_favorites = wallpaper_favorites;
    }

    public int getWallpaper_views() {
        return wallpaper_views;
    }

    public void setWallpaper_views(int wallpaper_views) {
        this.wallpaper_views = wallpaper_views;
    }

    public int getWallpaper_downloads() {
        return wallpaper_downloads;
    }

    public void setWallpaper_downloads(int wallpaper_downloads) {
        this.wallpaper_downloads = wallpaper_downloads;
    }

    public String getWallpaper_tags() {
        return wallpaper_tags;
    }

    public void setWallpaper_tags(String wallpaper_tags) {
        this.wallpaper_tags = wallpaper_tags;
    }

    public int getWallpaper_comments() {
        return wallpaper_comments;
    }

    public void setWallpaper_comments(int wallpaper_comments) {
        this.wallpaper_comments = wallpaper_comments;
    }
}
