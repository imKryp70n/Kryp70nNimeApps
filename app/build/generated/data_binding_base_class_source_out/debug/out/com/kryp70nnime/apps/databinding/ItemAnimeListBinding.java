// Generated by view binder compiler. Do not edit!
package com.kryp70nnime.apps.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.cardview.widget.CardView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.facebook.shimmer.ShimmerFrameLayout;
import com.kryp70nnime.apps.R;
import java.lang.NullPointerException;
import java.lang.Override;
import java.lang.String;

public final class ItemAnimeListBinding implements ViewBinding {
  @NonNull
  private final ConstraintLayout rootView;

  @NonNull
  public final CardView AnimeCardView;

  @NonNull
  public final ImageView animeImage;

  @NonNull
  public final TextView animeTitle;

  @NonNull
  public final TextView releaseDateItem;

  @NonNull
  public final ShimmerFrameLayout shimmerAnime;

  @NonNull
  public final TextView statusAnimeItem;

  @NonNull
  public final TextView subOrDubItem;

  private ItemAnimeListBinding(@NonNull ConstraintLayout rootView, @NonNull CardView AnimeCardView,
      @NonNull ImageView animeImage, @NonNull TextView animeTitle,
      @NonNull TextView releaseDateItem, @NonNull ShimmerFrameLayout shimmerAnime,
      @NonNull TextView statusAnimeItem, @NonNull TextView subOrDubItem) {
    this.rootView = rootView;
    this.AnimeCardView = AnimeCardView;
    this.animeImage = animeImage;
    this.animeTitle = animeTitle;
    this.releaseDateItem = releaseDateItem;
    this.shimmerAnime = shimmerAnime;
    this.statusAnimeItem = statusAnimeItem;
    this.subOrDubItem = subOrDubItem;
  }

  @Override
  @NonNull
  public ConstraintLayout getRoot() {
    return rootView;
  }

  @NonNull
  public static ItemAnimeListBinding inflate(@NonNull LayoutInflater inflater) {
    return inflate(inflater, null, false);
  }

  @NonNull
  public static ItemAnimeListBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable ViewGroup parent, boolean attachToParent) {
    View root = inflater.inflate(R.layout.item_anime_list, parent, false);
    if (attachToParent) {
      parent.addView(root);
    }
    return bind(root);
  }

  @NonNull
  public static ItemAnimeListBinding bind(@NonNull View rootView) {
    // The body of this method is generated in a way you would not otherwise write.
    // This is done to optimize the compiled bytecode for size and performance.
    int id;
    missingId: {
      id = R.id.AnimeCardView;
      CardView AnimeCardView = ViewBindings.findChildViewById(rootView, id);
      if (AnimeCardView == null) {
        break missingId;
      }

      id = R.id.animeImage;
      ImageView animeImage = ViewBindings.findChildViewById(rootView, id);
      if (animeImage == null) {
        break missingId;
      }

      id = R.id.animeTitle;
      TextView animeTitle = ViewBindings.findChildViewById(rootView, id);
      if (animeTitle == null) {
        break missingId;
      }

      id = R.id.releaseDateItem;
      TextView releaseDateItem = ViewBindings.findChildViewById(rootView, id);
      if (releaseDateItem == null) {
        break missingId;
      }

      id = R.id.shimmerAnime;
      ShimmerFrameLayout shimmerAnime = ViewBindings.findChildViewById(rootView, id);
      if (shimmerAnime == null) {
        break missingId;
      }

      id = R.id.statusAnimeItem;
      TextView statusAnimeItem = ViewBindings.findChildViewById(rootView, id);
      if (statusAnimeItem == null) {
        break missingId;
      }

      id = R.id.subOrDubItem;
      TextView subOrDubItem = ViewBindings.findChildViewById(rootView, id);
      if (subOrDubItem == null) {
        break missingId;
      }

      return new ItemAnimeListBinding((ConstraintLayout) rootView, AnimeCardView, animeImage,
          animeTitle, releaseDateItem, shimmerAnime, statusAnimeItem, subOrDubItem);
    }
    String missingId = rootView.getResources().getResourceName(id);
    throw new NullPointerException("Missing required view with ID: ".concat(missingId));
  }
}
