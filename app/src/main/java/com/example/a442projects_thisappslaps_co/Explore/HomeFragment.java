package com.example.a442projects_thisappslaps_co.Explore;

import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Rect;
import android.net.Uri;
import android.os.Bundle;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.a442projects_thisappslaps_co.R;

import java.util.List;

public class HomeFragment extends Fragment {

    private static ExploreController sExploreController;
    private ArticleAdapter adapter;
    private RecyclerView recyclerView;

    public HomeFragment(ExploreController exploreController) {
        sExploreController = exploreController;
    }

    @Override
    public void onCreate(Bundle onSaveInstanceState) {
        super.onCreate(onSaveInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle bundle) {
        View view = inflater.inflate(R.layout.explore_content, container, false);

        recyclerView = view.findViewById(R.id.recycler_view);

        adapter = new ArticleAdapter(getContext(), sExploreController.getArticleList());

        RecyclerView.LayoutManager mLayoutManager = new GridLayoutManager(getContext(), 1);
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.addItemDecoration(new GridSpacingItemDecoration(2, dpToPx(10), true));
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(adapter);

        return view;
    }

    public class ArticleAdapter extends RecyclerView.Adapter<ArticleAdapter.MyViewHolder> {

        private Context mContext;
        private List<Article> articleList;

        public class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
            public TextView title;
            public ImageView articleImg;
            public ImageButton mFavorite;
            private Article mArticle;

            public MyViewHolder(View view) {
                super(view);
                title = view.findViewById(R.id.article_title);
                articleImg = view.findViewById(R.id.article_img);
                itemView.setOnClickListener(this);
                mFavorite = view.findViewById(R.id.favorite);
                mFavorite.setOnClickListener(this);
            }

            void bind(Article article) {
                mArticle = article;
                mFavorite.setImageDrawable(getResources().getDrawable(article.isFavorited()
                        ? R.drawable.ic_star_icon
                        : R.drawable.ic_star_border, null));
            }

            @Override
            public void onClick(View v) {
                if (v.getId() == R.id.favorite) {
                    mArticle.setFavorited(!mArticle.isFavorited());
                    sExploreController.updateArticle(mArticle);
                    mFavorite.setImageDrawable(getResources().getDrawable(mArticle.isFavorited()
                            ? R.drawable.ic_star_icon
                            : R.drawable.ic_star_border, null));
                    return;
                }
                Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(mArticle.getUrl()));
                startActivity(browserIntent);
            }
        }

        public ArticleAdapter(Context mContext, List<Article> articleList) {
            this.mContext = mContext;
            this.articleList = articleList;
        }

        @Override
        public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View itemView = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.explore_item, parent, false);

            return new MyViewHolder(itemView);
        }

        @Override
        public void onBindViewHolder(final MyViewHolder holder, int position) {
            Article article = articleList.get(position);
            holder.title.setText(article.getName());
            holder.bind(article);
            // loading article images using Glide library
            Glide.with(mContext).load(article.getThumbnail()).into(holder.articleImg);
        }
        @Override
        public int getItemCount() {
            return articleList.size();
        }
    }

    public class GridSpacingItemDecoration extends RecyclerView.ItemDecoration {

        private int spanCount;
        private int spacing;
        private boolean includeEdge;

        public GridSpacingItemDecoration(int spanCount, int spacing, boolean includeEdge) {
            this.spanCount = spanCount;
            this.spacing = spacing;
            this.includeEdge = includeEdge;
        }

        @Override
        public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
            int position = parent.getChildAdapterPosition(view); // item position
            int column = position % spanCount; // item column

            if (includeEdge) {
                outRect.left = spacing - column * spacing / spanCount; // spacing - column * ((1f / spanCount) * spacing)
                outRect.right = (column + 1) * spacing / spanCount; // (column + 1) * ((1f / spanCount) * spacing)

                if (position < spanCount) { // top edge
                    outRect.top = spacing;
                }
                outRect.bottom = spacing; // item bottom
            } else {
                outRect.left = column * spacing / spanCount; // column * ((1f / spanCount) * spacing)
                outRect.right = spacing - (column + 1) * spacing / spanCount; // spacing - (column + 1) * ((1f /    spanCount) * spacing)
                if (position >= spanCount) {
                    outRect.top = spacing; // item top
                }
            }
        }
    }

    private int dpToPx(int dp) {
        Resources r = getResources();
        return Math.round(TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp, r.getDisplayMetrics()));
    }
}
