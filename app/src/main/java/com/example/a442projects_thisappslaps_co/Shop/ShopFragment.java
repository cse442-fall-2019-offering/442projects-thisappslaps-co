package com.example.a442projects_thisappslaps_co.Shop;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.Toolbar;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.a442projects_thisappslaps_co.R;

import java.util.ArrayList;
import java.util.Collections;

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
        ArrayList<ShopItem> dummyList = mShopController.createDummyList();

        mPopularProductsRecylerView.setLayoutManager(
                new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false));
        mPopularProductsRecylerView.setHorizontalScrollBarEnabled(true);
        mPopularProductsRecylerView.setAdapter(new PopularProductsAdapter(dummyList));

        ArrayList<ShopItem> dummyList2 = new ArrayList<>(dummyList);
        Collections.shuffle(dummyList2);

        mRecommendedRecyclerView.setLayoutManager(
                new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false));
        mRecommendedRecyclerView.setHorizontalScrollBarEnabled(true);
        mRecommendedRecyclerView.setAdapter(new RecommendedProductsAdapter(dummyList2));

        ArrayList<ShopItem> dummyList3 = new ArrayList<>(dummyList);
        Collections.shuffle(dummyList3);

        mHalloweenRecyclerView.setLayoutManager(
                new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false));
        mHalloweenRecyclerView.setHorizontalScrollBarEnabled(true);
        mHalloweenRecyclerView.setAdapter(new HalloweenProductsAdapter(dummyList3));
    }

    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.header_1) {
            Intent browserIntent = new Intent(Intent.ACTION_VIEW,
                    Uri.parse("https://www.dutchgrown.com/products/tulip-red-power?gclid=" +
                            "CjwKCAiA_MPuBRB5EiwAHTTvMUx52iCbEXHs9NMRTrm5a1EtZR-TZrUCK_e" +
                            "FgWdek92Gk9A8tm9SYBoCxk4QAvD_BwE"));
            startActivity(browserIntent);
            Toast.makeText(getContext(), "Here are some Tulips", Toast.LENGTH_SHORT).show();
        }
        else if (view.getId() == R.id.header_2) {
            Intent browserIntent = new Intent(Intent.ACTION_VIEW,
                    Uri.parse("https://www.bluestoneperennials.com/MXF.html?utm_source=" +
                            "Froogle&utm_medium=cse&utm_campaign=Feed&utm_source=google&utm" +
                            "_medium=cpc&utm_campaign=NB_PLA_CloseStates_GOOG&utm_term=shopp" +
                            "ing&utm_content=subbwC8ly|pcrid|40107396943|pmt||pkw||pdv|c|&&gclid=C" +
                            "jwKCAiA_MPuBRB5EiwAHTTvMQ_3_i6-UqRGKCLVHFJFikq80Hcgv11DSUXedz2fVpfQ" +
                            "DcAr0ug6fxoCCNUQAvD_BwE"));
            startActivity(browserIntent);
            Toast.makeText(getContext(), "Here are some Mums", Toast.LENGTH_SHORT).show();
        }
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
            itemView.setBackgroundResource(shopItem.getResourceId());
            mShopItem = shopItem;
        }

        @Override
        public void onClick(View v) {
            Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(mShopItem.getUrl()));
            startActivity(browserIntent);
        }

        @Override
        public boolean onLongClick(View view) {

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
            itemView.setBackgroundResource(shopItem.getResourceId());
            mShopItem = shopItem;
        }

        @Override
        public void onClick(View v) {
            Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(mShopItem.getUrl()));
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
            itemView.setBackgroundResource(shopItem.getResourceId());
            mShopItem = shopItem;
        }

        @Override
        public void onClick(View v) {
            Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(mShopItem.getUrl()));
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
