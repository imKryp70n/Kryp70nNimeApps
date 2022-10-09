package com.kryp70nnime.apps.Data.Remote.models.BookmarkModels;

// save this bookmark data to storage

public class BookmarkModel {
    String Title;
    String ImageUrl;
    String Url;

    public BookmarkModel(String title, String imageUrl, String url) {
        Title = title;
        ImageUrl = imageUrl;
        Url = url;
    }

    public String getTitle() {
        return Title;
    }

    public void setTitle(String title) {
        Title = title;
    }

    public String getImageUrl() {
        return ImageUrl;
    }

    public void setImageUrl(String imageUrl) {
        ImageUrl = imageUrl;
    }

    public String getUrl() {
        return Url;
    }

    public void setUrl(String url) {
        Url = url;
    }
}
