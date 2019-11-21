package com.example.a442projects_thisappslaps_co.Explore;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.a442projects_thisappslaps_co.R;

import java.util.ArrayList;
import java.util.List;

public class FavoritesFragment extends Fragment {

    private static ExploreController sExploreController;
    private RecyclerView mFavoritesRecyclerView;
    private FavoriteRecyclerAdapter mFavoritesRecyclerAdapter;
    private ArrayList<Article> mFavoritesArticleList;

    FavoritesFragment(ExploreController exploreController){
        sExploreController = exploreController;
    }

    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle bundle){
        View view = inflater.inflate(R.layout.explore_content, container, false);

        mFavoritesArticleList = new ArrayList<>();

        for (Article article : sExploreController.getArticleList()) {
            if (article.isFavorited()) {
                mFavoritesArticleList.add(article);
            }
        }

        mFavoritesRecyclerView = view.findViewById(R.id.recycler_view);
        mFavoritesRecyclerView.setLayoutManager(new GridLayoutManager(getContext(), 1));
        mFavoritesRecyclerAdapter = new FavoriteRecyclerAdapter(mFavoritesArticleList);
        mFavoritesRecyclerView.setAdapter(mFavoritesRecyclerAdapter);

        return view;
    }

    private class FavoritesViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        private ImageView mThumbnailImageView;
        private TextView mTitleTextView;
        private Article mArticle;
        private ImageButton mIsFavoritedButton;

        public FavoritesViewHolder(LayoutInflater inflater, ViewGroup viewGroup) {
            super(inflater.inflate(R.layout.explore_item, viewGroup, false));
            mThumbnailImageView = itemView.findViewById(R.id.article_img);
            mTitleTextView = itemView.findViewById(R.id.article_title);
            mIsFavoritedButton = itemView.findViewById(R.id.favorite);
            mIsFavoritedButton.setOnClickListener(this);
            itemView.setOnClickListener(this);
        }

        public void bind(Article article){
            mArticle = article;
            Glide.with(getContext()).load(article.getThumbnail()).into(mThumbnailImageView);
            mTitleTextView.setText(article.getName());
            mIsFavoritedButton.setImageDrawable(getResources().getDrawable(R.drawable.ic_star_icon, null));
        }

        @Override
        public void onClick(View v) {
            if (v.getId() == R.id.favorite) {
                mArticle.setFavorited(false);
                mFavoritesArticleList.remove(mArticle);
                sExploreController.updateArticle(mArticle);
                mFavoritesRecyclerAdapter.notifyDataSetChanged();
                return;
            }
            Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(mArticle.getUrl()));
            startActivity(browserIntent);
        }
    }

    private class FavoriteRecyclerAdapter extends RecyclerView.Adapter<FavoritesViewHolder> {

        private List<Article> mFavoritesArticleList;

        public FavoriteRecyclerAdapter(List<Article> favoritesArticleList) {
            mFavoritesArticleList = favoritesArticleList;
        }

        @Override
        public FavoritesViewHolder onCreateViewHolder(ViewGroup viewGroup, int parent) {
            return new FavoritesViewHolder(LayoutInflater.from(getActivity()), viewGroup);
        }

        @Override
        public void onBindViewHolder(@NonNull FavoritesViewHolder holder, int position) {
            holder.bind(mFavoritesArticleList.get(position));
        }

        @Override
        public int getItemCount() {
            return mFavoritesArticleList.size();
        }
    }
}
