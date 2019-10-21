package com.example.a442projects_thisappslaps_co.Shop;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Pair;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.a442projects_thisappslaps_co.R;

import java.util.ArrayList;
import java.util.HashMap;

public class ShopFragment extends Fragment implements View.OnClickListener {

    private ShopController mShopController;

    private ImageButton mHomeImageButton;
    private RecyclerView mPopularProductsRecylerView;
    private RecyclerView mRecommendedRecyclerView;
    private RecyclerView mHalloweenRecyclerView;

    public ShopFragment() { }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mShopController = new ShopController();
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle bundle) {
        View view = inflater.inflate(R.layout.shop_fragment, container, false);

        initializeViewVariables(view);
        setListeners();
        initializeRecyclerViewsAndSetAdapters();

        return view;
    }

    private void initializeViewVariables(View view) {
        mHomeImageButton = view.findViewById(R.id.shop_home_image_button);
        mPopularProductsRecylerView =
                view.findViewById(R.id.shop_popular_products_horizontal_recycler_view);
        mRecommendedRecyclerView = view.findViewById(R.id.shop_recommended_recycler_view);
        mHalloweenRecyclerView = view.findViewById(R.id.shop_halloween_horizontal_recycler_view);
    }

    private void setListeners() {
        mHomeImageButton.setOnClickListener(this);
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
        if (view.getId() == R.id.shop_home_image_button) {
            assert getFragmentManager() != null;
            getFragmentManager().popBackStackImmediate();
        }
    }

    private class PopularProductsViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        private String mURL;

        PopularProductsViewHolder(LayoutInflater inflater, ViewGroup viewGroup) {
            super(inflater.inflate(R.layout.products_item, viewGroup, false));
            itemView.setOnClickListener(this);
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

    private class RecommendedProductsViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        private String mURL;

        RecommendedProductsViewHolder(LayoutInflater inflater, ViewGroup viewGroup) {
            super(inflater.inflate(R.layout.products_item, viewGroup, false));
            itemView.setOnClickListener(this);
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

    private class HalloweenProductsViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        private String mURL;

        HalloweenProductsViewHolder(LayoutInflater inflater, ViewGroup viewGroup) {
            super(inflater.inflate(R.layout.products_item, viewGroup, false));
            itemView.setOnClickListener(this);
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
