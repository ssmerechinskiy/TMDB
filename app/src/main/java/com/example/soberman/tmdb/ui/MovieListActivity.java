package com.example.soberman.tmdb.ui;

import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Toast;


import com.example.soberman.tmdb.R;
import com.example.soberman.tmdb.data.model.business.Movie;
import com.example.soberman.tmdb.di.presenter.DaggerMoviesViewComponent;
import com.example.soberman.tmdb.di.presenter.MoviesViewComponent;
import com.example.soberman.tmdb.di.presenter.MoviesViewModule;
import com.example.soberman.tmdb.presenter.MoviesPresenter;
import com.example.soberman.tmdb.presenter.MoviesView;
import com.example.soberman.tmdb.presenter.base.BasePresenter;
import com.example.soberman.tmdb.presenter.base.Presentable;

import java.util.List;

import javax.inject.Inject;

/**
 * An activity representing a list of Movies. This activity
 * has different presentations for handset and tablet-size devices. On
 * handsets, the activity presents a list of items, which when touched,
 * lead to a {@link MovieDetailActivity} representing
 * item details. On tablets, the activity presents the list of items and
 * item details side-by-side using two vertical panes.
 */
public class MovieListActivity extends AppCompatActivity implements MoviesView, Presentable{
    private final static String TAG = MovieListActivity.class.getSimpleName();
    /**
     * Whether or not the activity is in two-pane mode, i.e. running on a tablet
     * device.
     */
    private MoviesViewComponent moviesViewComponent;

    private boolean mTwoPane;
    @Inject
    protected MoviesPresenter mPresenter;
    private MoviesAdapter mAdapter;
    private RecyclerView mRecyclerView;
    private View mPageProgress;
    private View mTotalProgress;
    private GridLayoutManager mGridLayoutManager;
    private SwipeRefreshLayout mSwipeRefreshLayout;

    private Handler mHandler = new Handler();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_list);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        toolbar.setTitle(getTitle());

        mSwipeRefreshLayout = (SwipeRefreshLayout) findViewById(R.id.swipe_refresh);
        mRecyclerView = (RecyclerView) findViewById(R.id.movie_list);
        mPageProgress = findViewById(R.id.progress);
        mTotalProgress = findViewById(R.id.total_progress);
        if (findViewById(R.id.movie_detail_container) != null) {
            mTwoPane = true;
        }
        moviesViewComponent = DaggerMoviesViewComponent.builder()
                .moviesViewModule(new MoviesViewModule(this))
                .build();
        moviesViewComponent.inject(this);
        mPresenter.setTwoPane(mTwoPane);
    }

    @Override
    public void onDestroy() {
        mHandler.removeCallbacksAndMessages(null);
        mPresenter.onDestroy();
        super.onDestroy();
    }

    private void setupViews() {
        mSwipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                mPresenter.requestRefreshMovies();
            }
        });

        mAdapter = new MoviesAdapter(this, new MoviesAdapter.Callback() {
            @Override
            public void onLastItemShowed() {
                mPresenter.requestMoviesPage(false);
            }

            @Override
            public void onItemClicked(Context context, Movie movie) {
                mPresenter.onItemClicked(context, movie);
            }
        });
        if(mTwoPane)mAdapter.setUseSelection(true);
        mAdapter.setImagesParams(mPresenter.getBaseImageUrl(), mPresenter.getLogoImageSize());
        mRecyclerView.setHasFixedSize(true);
        mGridLayoutManager = new GridLayoutManager(this, 2);
        mRecyclerView.setLayoutManager(mGridLayoutManager);
        mRecyclerView.setAdapter(mAdapter);
    }


    @Override
    public void refreshMovies(List<Movie> movies) {
        mAdapter.clearMovies();
        mAdapter.addMovies(movies);
        if(mTwoPane)mPresenter.onItemClicked(this, movies.get(0));
    }

    @Override
    public void addMoviesPage(List<Movie> movies, int page, int pageCount) {
        mAdapter.addMovies(movies);
        if(mTwoPane) {
            if(page == 1) mPresenter.onItemClicked(this, movies.get(0));
        }
    }

    @Override
    public void updateMovie(Movie movie) {

    }

    @Override
    public void showPageLoadingProgress() {
        mPageProgress.setVisibility(View.VISIBLE);
    }

    @Override
    public void hidePageLoadingProgress() {
        mPageProgress.setVisibility(View.GONE);
    }

    @Override
    public void hideSwipeRefreshMoviesProgress() {
        if(mSwipeRefreshLayout.isRefreshing()) mSwipeRefreshLayout.setRefreshing(false);
    }

    @Override
    public void onAPIConfigurationSuccess() {
        Log.d(TAG, "onAPIConfigurationSuccess");
        //Toast.makeText(this, "onAPIConfigurationSuccess SUCCESS", Toast.LENGTH_LONG).show();
        setupViews();
        mPresenter.setTwoPane(mTwoPane);
        mPresenter.requestMoviesPage(false);
    }

    @Override
    public void onAPIConfigurationError() {
        Log.d(TAG, "onAPIConfigurationError");
        Toast.makeText(this, "onAPIConfigurationError. Try open app later", Toast.LENGTH_LONG).show();
        mHandler.postDelayed(new Runnable() {
            @Override
            public void run() {
                finish();
            }
        }, 2000);
    }

    @Override
    public void showMessage(String message) {
        Toast.makeText(this, message, Toast.LENGTH_LONG).show();
    }

    @Override
    public void showProgress() {
        mTotalProgress.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideProgress() {
        mTotalProgress.setVisibility(View.GONE);
    }

    @Override
    public BasePresenter getPresenter() {
        return mPresenter;
    }

    /*
    public class SimpleItemRecyclerViewAdapter
            extends RecyclerView.Adapter<SimpleItemRecyclerViewAdapter.ViewHolder> {

        private final List<DummyContent.DummyItem> mValues;

        public SimpleItemRecyclerViewAdapter(List<DummyContent.DummyItem> items) {
            mValues = items;
        }

        @Override
        public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.movie_list_content, parent, false);
            return new ViewHolder(view);
        }

        @Override
        public void onBindViewHolder(final ViewHolder holder, int position) {
            holder.mItem = mValues.get(position);
            holder.mIdView.setText(mValues.get(position).id);
            holder.mContentView.setText(mValues.get(position).content);

            holder.mView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (mTwoPane) {
                        Bundle arguments = new Bundle();
                        arguments.putString(MovieDetailFragment.MOVIE_ID, holder.mItem.id);
                        MovieDetailFragment fragment = new MovieDetailFragment();
                        fragment.setArguments(arguments);
                        getSupportFragmentManager().beginTransaction()
                                .replace(R.id.movie_detail_container, fragment)
                                .commit();
                    } else {
                        Context context = v.getContext();
                        Intent intent = new Intent(context, MovieDetailActivity.class);
                        intent.putExtra(MovieDetailFragment.MOVIE_ID, holder.mItem.id);

                        context.startActivity(intent);
                    }
                }
            });
        }

        @Override
        public int getItemCount() {
            return mValues.size();
        }

        public class ViewHolder extends RecyclerView.ViewHolder {
            public final View mView;
            public final TextView mIdView;
            public final TextView mContentView;
            public DummyContent.DummyItem mItem;

            public ViewHolder(View view) {
                super(view);
                mView = view;
                mIdView = (TextView) view.findViewById(R.id.id);
                mContentView = (TextView) view.findViewById(R.id.content);
            }

            @Override
            public String toString() {
                return super.toString() + " '" + mContentView.getText() + "'";
            }
        }
    }*/
}
