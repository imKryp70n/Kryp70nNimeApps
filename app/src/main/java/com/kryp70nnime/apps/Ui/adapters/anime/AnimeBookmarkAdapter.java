package com.kryp70nnime.apps.Ui.adapters.anime;

import android.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.kryp70nnime.apps.R;

public class AnimeBookmarkAdapter extends RecyclerView.Adapter<AnimeBookmarkAdapter.ViewHolder> {

    @NonNull
    @Override
    public AnimeBookmarkAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_bookmark, parent, false);
        return new ViewHolder(view);
    }


    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        ImageView removeBookmark = holder.itemView.findViewById(R.id.removeBookmark);

        removeBookmark.setOnClickListener(view1 -> {
            AlertDialog dialog = new AlertDialog.Builder(holder.itemView.getContext())
                    .setTitle("Warning")
                    .setMessage("Are you sure want to delete this ?")
                    .setPositiveButton("YES", (dialogInterface, i) -> {
                        Toast.makeText(holder.itemView.getContext(), "Deleted", Toast.LENGTH_LONG).show();
                    }).setNegativeButton("NO", (dialogInterface, i) -> {
                        dialogInterface.dismiss();
                    })
                    .show();
        });

    }

    @Override
    public int getItemCount() {
        return 3;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
        }
    }
}
