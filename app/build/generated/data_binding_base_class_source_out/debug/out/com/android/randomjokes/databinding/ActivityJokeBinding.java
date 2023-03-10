// Generated by view binder compiler. Do not edit!
package com.android.randomjokes.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.android.randomjokes.R;
import java.lang.NullPointerException;
import java.lang.Override;
import java.lang.String;

public final class ActivityJokeBinding implements ViewBinding {
  @NonNull
  private final ConstraintLayout rootView;

  @NonNull
  public final RecyclerView jokesRecyclerView;

  private ActivityJokeBinding(@NonNull ConstraintLayout rootView,
      @NonNull RecyclerView jokesRecyclerView) {
    this.rootView = rootView;
    this.jokesRecyclerView = jokesRecyclerView;
  }

  @Override
  @NonNull
  public ConstraintLayout getRoot() {
    return rootView;
  }

  @NonNull
  public static ActivityJokeBinding inflate(@NonNull LayoutInflater inflater) {
    return inflate(inflater, null, false);
  }

  @NonNull
  public static ActivityJokeBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable ViewGroup parent, boolean attachToParent) {
    View root = inflater.inflate(R.layout.activity_joke, parent, false);
    if (attachToParent) {
      parent.addView(root);
    }
    return bind(root);
  }

  @NonNull
  public static ActivityJokeBinding bind(@NonNull View rootView) {
    // The body of this method is generated in a way you would not otherwise write.
    // This is done to optimize the compiled bytecode for size and performance.
    int id;
    missingId: {
      id = R.id.jokes_recycler_view;
      RecyclerView jokesRecyclerView = ViewBindings.findChildViewById(rootView, id);
      if (jokesRecyclerView == null) {
        break missingId;
      }

      return new ActivityJokeBinding((ConstraintLayout) rootView, jokesRecyclerView);
    }
    String missingId = rootView.getResources().getResourceName(id);
    throw new NullPointerException("Missing required view with ID: ".concat(missingId));
  }
}
