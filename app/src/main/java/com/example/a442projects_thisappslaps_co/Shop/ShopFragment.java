package com.example.a442projects_thisappslaps_co.Shop;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Pair;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.Toolbar;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.a442projects_thisappslaps_co.R;

import java.util.ArrayList;
import java.util.HashMap;

public class ShopFragment extends Fragment implements View.OnClickListener {

    private ShopController mShopController;

    private RecyclerView mPopularProductsRecylerView;
    private RecyclerView mRecommendedRecyclerView;
    private RecyclerView mHalloweenRecyclerView;

    private ConstraintLayout mHeaderOneConstraintLayout;
    private ConstraintLayout mHeaderTwoConstraintLayout;

    public ShopFragment() { }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mShopController = new ShopController();
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle bundle) {
        View view = inflater.inflate(R.layout.shop_fragment, container, false);

        Toolbar toolbar = view.findViewById(R.id.toolbar);
        toolbar.setNavigationOnClickListener(view1 -> {
            assert getFragmentManager() != null;
            getFragmentManager().popBackStackImmediate();
        });

        initializeViewVariables(view);
        initializeRecyclerViewsAndSetAdapters();

        mHeaderOneConstraintLayout.setOnClickListener(this);
        mHeaderTwoConstraintLayout.setOnClickListener(this);

        return view;
    }

    private void initializeViewVariables(View view) {
        mPopularProductsRecylerView =
                view.findViewById(R.id.shop_popular_products_horizontal_recycler_view);
        mRecommendedRecyclerView = view.findViewById(R.id.shop_recommended_recycler_view);
        mHalloweenRecyclerView = view.findViewById(R.id.shop_halloween_horizontal_recycler_view);
        mHeaderOneConstraintLayout = view.findViewById(R.id.header_1);
        mHeaderTwoConstraintLayout = view.findViewById(R.id.header_2);
    }

    private void initializeRecyclerViewsAndSetAdapters() {
        ArrayList<Pair<Integer, String>> dummyList = mShopController.createDummyList();

        mPopularProductsRecylerView.setLayoutManager(
                new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false));
        mPopularProductsRecylerView.setHorizontalScrollBarEnabled(true);
        mPopularProductsRecylerView.setAdapter(new PopularProductsAdapter(dummyList));

        mRecommendedRecyclerView.setLayoutManager(
                new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false));
        mRecommendedRecyclerView.setHorizontalScrollBarEnabled(true);
        mRecommendedRecyclerView.setAdapter(new RecommendedProductsAdapter(dummyList));

        mHalloweenRecyclerView.setLayoutManager(
                new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false));
        mHalloweenRecyclerView.setHorizontalScrollBarEnabled(true);
        mHalloweenRecyclerView.setAdapter(new HalloweenProductsAdapter(dummyList));
    }

    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.header_1) {
            // TODO
        }
        else if (view.getId() == R.id.header_2) {
            // TODO
        }
    }

    private class PopularProductsViewHolder extends RecyclerView.ViewHolder
            implements View.OnClickListener, View.OnLongClickListener {

        private String mURL;

        PopularProductsViewHolder(LayoutInflater inflater, ViewGroup viewGroup) {
            super(inflater.inflate(R.layout.products_item, viewGroup, false));
            itemView.setOnClickListener(this);
            itemView.setOnLongClickListener(this);
        }

        void bind(Pair<Integer, String> drawable) {
            itemView.setBackgroundResource(drawable.first);
            mURL = drawable.second;
        }

        @Override
        public void onClick(View v) {
            Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(mURL));
            startActivity(browserIntent);
        }

        @Override
        public boolean onLongClick(View view) {
            Toast.makeText(getContext(), "Added to Wish List", Toast.LENGTH_SHORT).show();
            return true;
        }
    }

    private class PopularProductsAdapter extends RecyclerView.Adapter<PopularProductsViewHolder> {

        ArrayList<Pair<Integer, String>> mPopularProductsDrawableArrayList;

        PopularProductsAdapter(ArrayList<Pair<Integer, String>> popularProductsDrawableArrayList) {
            mPopularProductsDrawableArrayList = popularProductsDrawableArrayList;
        }

        @NonNull
        @Override
        public PopularProductsViewHolder onCreateViewHolder(
                @NonNull ViewGroup parent, int viewType) {
            return new PopularProductsViewHolder(LayoutInflater.from(getActivity()), parent);
        }

        @Override
        public void onBindViewHolder(@NonNull PopularProductsViewHolder holder, int position) {
            holder.bind(mPopularProductsDrawableArrayList.get(position));
        }

        @Override
        public int getItemCount() {
            return mPopularProductsDrawableArrayList.size();
        }
    }

    private class RecommendedProductsViewHolder extends RecyclerView.ViewHolder
            implements View.OnClickListener, View.OnLongClickListener {

        private String mURL;

        RecommendedProductsViewHolder(LayoutInflater inflater, ViewGroup viewGroup) {
            super(inflater.inflate(R.layout.products_item, viewGroup, false));
            itemView.setOnClickListener(this);
            itemView.setOnLongClickListener(this);
        }

        void bind(Pair<Integer,String> drawable) {
            itemView.setBackgroundResource(drawable.first);
            mURL = drawable.second;
        }

        @Override
        public void onClick(View v) {
            Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(mURL));
            startActivity(browserIntent);
        }

        @Override
        public boolean onLongClick(View view) {
            Toast.makeText(getContext(), "Added to Wish List", Toast.LENGTH_SHORT).show();
            return true;
        }
    }

    private class RecommendedProductsAdapter extends
            RecyclerView.Adapter<RecommendedProductsViewHolder> {

        ArrayList<Pair<Integer, String>> mRecommendedProductsDrawableArrayList;

        RecommendedProductsAdapter(ArrayList<Pair<Integer, String>> recommendedProductsDrawableArrayList) {
            mRecommendedProductsDrawableArrayList = recommendedProductsDrawableArrayList;
        }

        @NonNull
        @Override
        public RecommendedProductsViewHolder onCreateViewHolder(
                @NonNull ViewGroup parent, int viewType) {
            return new RecommendedProductsViewHolder(LayoutInflater.from(getActivity()), parent);
        }

        @Override
        public void onBindViewHolder(@NonNull RecommendedProductsViewHolder holder, int position) {
            holder.bind(mRecommendedProductsDrawableArrayList.get(position));
        }

        @Override
        public int getItemCount() {
            return mRecommendedProductsDrawableArrayList.size();
        }
    }

    private class HalloweenProductsViewHolder extends RecyclerView.ViewHolder
            implements View.OnClickListener, View.OnLongClickListener {

        private String mURL;

        HalloweenProductsViewHolder(LayoutInflater inflater, ViewGroup viewGroup) {
            super(inflater.inflate(R.layout.products_item, viewGroup, false));
            itemView.setOnClickListener(this);
            itemView.setOnLongClickListener(this);
        }

        void bind(Pair<Integer,String> drawable){
            itemView.setBackgroundResource(drawable.first);
            mURL = drawable.second;
        }

        @Override
        public void onClick(View v) {
            Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(mURL));
            startActivity(browserIntent);
        }

        @Override
        public boolean onLongClick(View view) {
            Toast.makeText(getContext(), "Added to Wish List", Toast.LENGTH_SHORT).show();
            return true;
        }
    }

    private class HalloweenProductsAdapter extends
            RecyclerView.Adapter<HalloweenProductsViewHolder> {

        ArrayList<Pair<Integer, String>> mHalloweenProductsDrawableArrayList;

        HalloweenProductsAdapter(ArrayList<Pair<Integer, String>> halloweenProductsDrawableArrayList) {
            mHalloweenProductsDrawableArrayList = halloweenProductsDrawableArrayList;
        }

        @NonNull
        @Override
        public HalloweenProductsViewHolder onCreateViewHolder(
                @NonNull ViewGroup parent, int viewType) {
            return new HalloweenProductsViewHolder(LayoutInflater.from(getActivity()), parent);
        }

        @Override
        public void onBindViewHolder(@NonNull HalloweenProductsViewHolder holder, int position) {
            holder.bind(mHalloweenProductsDrawableArrayList.get(position));
        }

        @Override
        public int getItemCount() {
            return mHalloweenProductsDrawableArrayList.size();
        }
    }
}
