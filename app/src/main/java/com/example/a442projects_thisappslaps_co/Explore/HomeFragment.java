package com.example.a442projects_thisappslaps_co.Explore;

import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Rect;
import android.graphics.drawable.BitmapDrawable;
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

import java.util.ArrayList;
import java.util.List;

public class HomeFragment extends Fragment {

    private static ExploreController sExploreController;
    private ArticleAdapter adapter;
    private List<Article> articleList;
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

        articleList = new ArrayList<>();
        adapter = new ArticleAdapter(getContext(), articleList);

        RecyclerView.LayoutManager mLayoutManager = new GridLayoutManager(getContext(), 1);
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.addItemDecoration(new GridSpacingItemDecoration(2, dpToPx(10), true));
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(adapter);

        prepareArticles();

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
            }

            @Override
            public void onClick(View v) {
                if (v.getId() == R.id.favorite) {
                    sExploreController.addArticleToDatabase(mArticle);
                    mFavorite.setBackground(getResources().getDrawable(R.drawable.ic_star_icon, null));
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

    private void prepareArticles() {

        int[] covers = new int[]{
                R.drawable.article1,
                R.drawable.article2,
                R.drawable.article3,
                R.drawable.article4,
                R.drawable.article5,
                R.drawable.article6,
                R.drawable.article7,
                R.drawable.article8,
                R.drawable.article9,
                R.drawable.article10,
                R.drawable.article11,
                R.drawable.article12};

        Article a = new Article("Small Space Garden Strategies", "https://www.bhg.com/gardening/plans/small-space-garden-strategies/", covers[0]);
        articleList.add(a);

        a = new Article("Five Fabulous Garden Plans", "https://www.bhg.com/gardening/plans/easy/five-fab-garden-plans/", covers[1]);
        articleList.add(a);

        a = new Article("Easy-Care Summer Garden Plan", "https://www.bhg.com/gardening/plans/easy/easy-care-summer-garden-plan/", covers[2]);
        articleList.add(a);

        a = new Article("No-Fuss Garden Plans", "https://www.bhg.com/gardening/plans/easy/15-no-fuss-garden-plans/", covers[3]);
        articleList.add(a);

        a = new Article("25 Best Fall Plants and Flowers to Beautify Your Front Yard This Season", "https://www.countryliving.com/gardening/garden-ideas/g4662/fall-flowers/", covers[4]);
        articleList.add(a);

        a = new Article("Our Best Cactus Garden Tips for Creating a Stunning, at-Home Oasis", "https://www.countryliving.com/gardening/garden-ideas/a26265781/cactus-garden/", covers[5]);
        articleList.add(a);

        a = new Article("15 Best Low-Maintenance Flowers for Any Kind of Garden", "https://www.countryliving.com/gardening/garden-ideas/g27092607/low-maintenance-flowers/", covers[6]);
        articleList.add(a);

        a = new Article("22 Creative DIY Bench Ideas to Add to Your Garden This Year", "https://www.countryliving.com/gardening/garden-ideas/g3120/upcycled-garden-benches/", covers[7]);
        articleList.add(a);

        a = new Article("Colorful planting around an arbor", "https://www.gardengatemagazine.com/articles/garden-plans/entries/colorful-planting-around-an-arbor/", covers[8]);
        articleList.add(a);

        a = new Article("Budget-friendly garden border", "https://www.gardengatemagazine.com/articles/garden-plans/beds-borders/budget-friendly-garden-border/", covers[9]);
        articleList.add(a);

        a = new Article("Butterfly-friendly garden plan", "https://www.gardengatemagazine.com/articles/garden-plans/wildlife-friendly/create-a-butterfly-friendly-garden/", covers[10]);
        articleList.add(a);

        a = new Article("Plant a garden that will attract pollinators all season", "https://www.gardengatemagazine.com/articles/garden-plans/wildlife-friendly/plant-a-garden-that-will-attract-pollinators-all-season/", covers[11]);
        articleList.add(a);

        adapter.notifyDataSetChanged();
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
