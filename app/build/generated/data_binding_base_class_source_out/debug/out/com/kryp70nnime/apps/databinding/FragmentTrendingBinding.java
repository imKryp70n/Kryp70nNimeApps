// Generated by view binder compiler. Do not edit!
package com.kryp70nnime.apps.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.SearchView;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.kryp70nnime.apps.R;
import java.lang.NullPointerException;
import java.lang.Override;
import java.lang.String;

public final class FragmentTrendingBinding implements ViewBinding {
  @NonNull
  private final FrameLayout rootView;

  @NonNull
  public final RecyclerView animeTrendingRecycler;

  @NonNull
  public final LinearLayout relativeLayout;

  @NonNull
  public final SearchView searchViewAnime;

  @NonNull
  public final Toolbar toolbar;

  @NonNull
  public final TextView trendingText;

  private FragmentTrendingBinding(@NonNull FrameLayout rootView,
      @NonNull RecyclerView animeTrendingRecycler, @NonNull LinearLayout relativeLayout,
      @NonNull SearchView searchViewAnime, @NonNull Toolbar toolbar,
      @NonNull TextView trendingText) {
    this.rootView = rootView;
    this.animeTrendingRecycler = animeTrendingRecycler;
    this.relativeLayout = relativeLayout;
    this.searchViewAnime = searchViewAnime;
    this.toolbar = toolbar;
    this.trendingText = trendingText;
  }

  @Override
  @NonNull
  public FrameLayout getRoot() {
    return rootView;
  }

  @NonNull
  public static FragmentTrendingBinding inflate(@NonNull LayoutInflater inflater) {
    return inflate(inflater, null, false);
  }

  @NonNull
  public static FragmentTrendingBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable ViewGroup parent, boolean attachToParent) {
    View root = inflater.inflate(R.layout.fragment_trending, parent, false);
    if (attachToParent) {
      parent.addView(root);
    }
    return bind(root);
  }

  @NonNull
  public static FragmentTrendingBinding bind(@NonNull View rootView) {
    // The body of this method is generated in a way you would not otherwise write.
    // This is done to optimize the compiled bytecode for size and performance.
    int id;
    missingId: {
      id = R.id.animeTrendingRecycler;
      RecyclerView animeTrendingRecycler = ViewBindings.findChildViewById(rootView, id);
      if (animeTrendingRecycler == null) {
        break missingId;
      }

      id = R.id.relativeLayout;
      LinearLayout relativeLayout = ViewBindings.findChildViewById(rootView, id);
      if (relativeLayout == null) {
        break missingId;
      }

      id = R.id.searchViewAnime;
      SearchView searchViewAnime = ViewBindings.findChildViewById(rootView, id);
      if (searchViewAnime == null) {
        break missingId;
      }

      id = R.id.toolbar;
      Toolbar toolbar = ViewBindings.findChildViewById(rootView, id);
      if (toolbar == null) {
        break missingId;
      }

      id = R.id.trendingText;
      TextView trendingText = ViewBindings.findChildViewById(rootView, id);
      if (trendingText == null) {
        break missingId;
      }

      return new FragmentTrendingBinding((FrameLayout) rootView, animeTrendingRecycler,
          relativeLayout, searchViewAnime, toolbar, trendingText);
    }
    String missingId = rootView.getResources().getResourceName(id);
    throw new NullPointerException("Missing required view with ID: ".concat(missingId));
  }
}
