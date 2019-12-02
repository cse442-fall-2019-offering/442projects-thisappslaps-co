package com.example.a442projects_thisappslaps_co.Shop;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.a442projects_thisappslaps_co.R;

import java.util.ArrayList;
import java.util.Collections;

public class ShopHomeFragment extends Fragment
        implements View.OnClickListener, View.OnLongClickListener {

    private ShopController mShopController;

    private RecyclerView mPopularProductsRecyclerView;
    private RecyclerView mRecommendedRecyclerView;
    private RecyclerView mHalloweenRecyclerView;

    private ConstraintLayout mHeaderOneConstraintLayout;
    private ConstraintLayout mHeaderTwoConstraintLayout;

    private ShopItem mHeaderOneShopItem;
    private ShopItem mHeaderTwoShopItem;

    ShopHomeFragment(ShopController shopController) {
        mShopController = shopController;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle bundle) {
        View view = inflater.inflate(R.layout.shop_home_fragment, container, false);

        initializeViewVariables(view);
        initializeRecyclerViewsAndSetAdapters();

        for (ShopItem shopItem : mShopController.getShopList()) {
            if (shopItem.getTitle().equals("Red Tulip")) {
                mHeaderOneShopItem = shopItem;
            }
            else if (shopItem.getTitle().equals("Yellow Mums")) {
                mHeaderTwoShopItem = shopItem;
            }
        }

        mHeaderOneConstraintLayout.setOnClickListener(this);
        mHeaderTwoConstraintLayout.setOnClickListener(this);
        mHeaderOneConstraintLayout.setOnLongClickListener(this);
        mHeaderTwoConstraintLayout.setOnLongClickListener(this);

        return view;
    }


    private void initializeViewVariables(View view) {
        mPopularProductsRecyclerView =
                view.findViewById(R.id.shop_popular_products_horizontal_recycler_view);
        mRecommendedRecyclerView = view.findViewById(R.id.shop_recommended_recycler_view);
        mHalloweenRecyclerView = view.findViewById(R.id.shop_halloween_horizontal_recycler_view);
        mHeaderOneConstraintLayout = view.findViewById(R.id.header_1);
        mHeaderTwoConstraintLayout = view.findViewById(R.id.header_2);
    }

    private void initializeRecyclerViewsAndSetAdapters() {
        ArrayList<ShopItem> shopList = mShopController.getShopList();

        mPopularProductsRecyclerView.setLayoutManager(
                new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false));
        mPopularProductsRecyclerView.setHorizontalScrollBarEnabled(true);
        mPopularProductsRecyclerView.setAdapter(new PopularProductsAdapter(shopList));

        ArrayList<ShopItem> shopList2 = new ArrayList<>(shopList);
        Collections.shuffle(shopList2);

        mRecommendedRecyclerView.setLayoutManager(
                new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false));
        mRecommendedRecyclerView.setHorizontalScrollBarEnabled(true);
        mRecommendedRecyclerView.setAdapter(new RecommendedProductsAdapter(shopList2));

        ArrayList<ShopItem> shopList3 = new ArrayList<>(shopList);
        Collections.shuffle(shopList3);

        mHalloweenRecyclerView.setLayoutManager(
                new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false));
        mHalloweenRecyclerView.setHorizontalScrollBarEnabled(true);
        mHalloweenRecyclerView.setAdapter(new HalloweenProductsAdapter(shopList3));
    }

    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.header_1) {
            Intent browserIntent =
                    new Intent(Intent.ACTION_VIEW, Uri.parse(mHeaderOneShopItem.getUrl()));
            startActivity(browserIntent);
            Toast.makeText(getContext(), "Here are some Tulips", Toast.LENGTH_SHORT).show();
        }
        else if (view.getId() == R.id.header_2) {
            Intent browserIntent =
                    new Intent(Intent.ACTION_VIEW, Uri.parse(mHeaderTwoShopItem.getUrl()));
            startActivity(browserIntent);
            Toast.makeText(getContext(), "Here are some Mums", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public boolean onLongClick(View view) {
        if (view.getId() == R.id.header_1) {
            mHeaderOneShopItem.setIsAddedToCart(true);
            mShopController.updateEntry(mHeaderOneShopItem);
            Toast.makeText(getContext(), "Added to Wish List", Toast.LENGTH_SHORT).show();
            return true;
        }
        else if (view.getId() == R.id.header_2) {
            mHeaderTwoShopItem.setIsAddedToCart(true);
            mShopController.updateEntry(mHeaderTwoShopItem);
            Toast.makeText(getContext(), "Added to Wish List", Toast.LENGTH_SHORT).show();
            return true;
        }
        return false;
    }

    private class PopularProductsViewHolder extends RecyclerView.ViewHolder
            implements View.OnClickListener, View.OnLongClickListener {

        private ShopItem mShopItem;

        PopularProductsViewHolder(LayoutInflater inflater, ViewGroup viewGroup) {
            super(inflater.inflate(R.layout.products_item, viewGroup, false));
            itemView.setOnClickListener(this);
            itemView.setOnLongClickListener(this);
        }

        void bind(ShopItem shopItem) {
            itemView.setBackgroundResource(getResources()
                    .getIdentifier(
                            shopItem.getResourceName(),
                            "drawable",
                            getContext().getPackageName()));
            mShopItem = shopItem;
        }

        @Override
        public void onClick(View v) {
            Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(mShopItem.getUrl()));
            startActivity(browserIntent);
        }

        @Override
        public boolean onLongClick(View view) {
            mShopItem.setIsAddedToCart(true);
            mShopController.updateEntry(mShopItem);
            Toast.makeText(getContext(), "Added to Wish List", Toast.LENGTH_SHORT).show();
            return true;
        }
    }

    private class PopularProductsAdapter extends RecyclerView.Adapter<PopularProductsViewHolder> {

        ArrayList<ShopItem> mPopularProductsDrawableArrayList;

        PopularProductsAdapter(ArrayList<ShopItem> popularProductsDrawableArrayList) {
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

        private ShopItem mShopItem;

        RecommendedProductsViewHolder(LayoutInflater inflater, ViewGroup viewGroup) {
            super(inflater.inflate(R.layout.products_item, viewGroup, false));
            itemView.setOnClickListener(this);
            itemView.setOnLongClickListener(this);
        }

        void bind(ShopItem shopItem) {
            itemView.setBackgroundResource(getResources()
                    .getIdentifier(
                            shopItem.getResourceName(),
                            "drawable",
                            getContext().getPackageName()));
            mShopItem = shopItem;
        }

        @Override
        public void onClick(View v) {
            Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(mShopItem.getUrl()));
            startActivity(browserIntent);
        }

        @Override
        public boolean onLongClick(View view) {
            mShopItem.setIsAddedToCart(true);
            mShopController.updateEntry(mShopItem);
            Toast.makeText(getContext(), "Added to Wish List", Toast.LENGTH_SHORT).show();
            return true;
        }
    }

    private class RecommendedProductsAdapter extends
            RecyclerView.Adapter<RecommendedProductsViewHolder> {

        ArrayList<ShopItem> mRecommendedProductsDrawableArrayList;

        RecommendedProductsAdapter(ArrayList<ShopItem> recommendedProductsDrawableArrayList) {
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

        private ShopItem mShopItem;

        HalloweenProductsViewHolder(LayoutInflater inflater, ViewGroup viewGroup) {
            super(inflater.inflate(R.layout.products_item, viewGroup, false));
            itemView.setOnClickListener(this);
            itemView.setOnLongClickListener(this);
        }

        void bind(ShopItem shopItem){
            itemView.setBackgroundResource(getResources()
                    .getIdentifier(
                            shopItem.getResourceName(),
                            "drawable",
                            getContext().getPackageName()));
            mShopItem = shopItem;
        }

        @Override
        public void onClick(View v) {
            Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(mShopItem.getUrl()));
            startActivity(browserIntent);
        }

        @Override
        public boolean onLongClick(View view) {
            mShopItem.setIsAddedToCart(true);
            mShopController.updateEntry(mShopItem);
            Toast.makeText(getContext(), "Added to Wish List", Toast.LENGTH_SHORT).show();
            return true;
        }
    }

    private class HalloweenProductsAdapter extends
            RecyclerView.Adapter<HalloweenProductsViewHolder> {

        ArrayList<ShopItem> mHalloweenProductsDrawableArrayList;

        HalloweenProductsAdapter(ArrayList<ShopItem> halloweenProductsDrawableArrayList) {
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
