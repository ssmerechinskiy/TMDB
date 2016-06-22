package com.example.soberman.tmdb.ui;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.soberman.tmdb.R;
import com.example.soberman.tmdb.data.model.business.Movie;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by soberman on 20.06.2016.
 */
public class MoviesAdapter extends RecyclerView.Adapter<MoviesAdapter.ViewHolder>{

    private List<Movie> movies;
    private String baseImageUrl;
    private String imageSize;
    private Context context;
    private Callback mCallback;
    private boolean useSelection = false;
    private int selectedPosition = 0;

    public MoviesAdapter(Context context, Callback callback) {
        movies = new ArrayList<>();
        this.context = context;
        mCallback = callback;
    }

    public void addMovies(List<Movie> list) {
        movies.addAll(list);
        notifyDataSetChanged();
    }

    public void clearMovies() {
        selectedPosition = 0;
        movies.clear();
        notifyDataSetChanged();
    }

    public void setImagesParams(String baseImageUrl, String imageSize) {
        this.baseImageUrl = baseImageUrl;
        this.imageSize = imageSize;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.movie_list_content, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {
        holder.item = movies.get(position);
        holder.title.setText(holder.item.getTitle());
        holder.rating.setText(String.valueOf(holder.item.getVoteAverage()));
        if(baseImageUrl != null && imageSize != null) {
            Picasso.with(context)
                    .load(baseImageUrl + imageSize + holder.item.getPosterPath())
                    .into(holder.image);
        }

        if(useSelection) {
            if(position == selectedPosition) {
                holder.mView.setBackgroundColor(Color.BLUE);
            } else {
                holder.mView.setBackgroundColor(Color.WHITE);
            }
        } else {
            holder.mView.setBackgroundColor(Color.WHITE);
        }

        holder.mView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                notifyItemChanged(selectedPosition);
                selectedPosition = position;
                notifyItemChanged(selectedPosition);
                mCallback.onItemClicked(v.getContext(), holder.item);
            }
        });

        if(position == movies.size() -1) {
            mCallback.onLastItemShowed();
        }
    }

    public void setUseSelection(boolean val) {
        useSelection = val;
    }

    @Override
    public int getItemCount() {
        return movies.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public final View mView;
        public final ImageView image;
        public final TextView title;
        public final TextView rating;
        public Movie item;
        public final View selection;

        public ViewHolder(View view) {
            super(view);
            mView = view;
            title = (TextView) view.findViewById(R.id.title);
            rating = (TextView) view.findViewById(R.id.rating);
            image = (ImageView) view.findViewById(R.id.image);
            selection = view.findViewById(R.id.image_container);
        }

    }

    public interface Callback {
        void onLastItemShowed();
        void onItemClicked(Context context, Movie movie);
    }
}
