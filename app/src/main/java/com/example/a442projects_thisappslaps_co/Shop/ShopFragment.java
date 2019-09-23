package com.example.a442projects_thisappslaps_co.Shop;

import android.os.Bundle;
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
        ArrayList<Integer> dummyList = mShopController.createDummyList();

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

    private class PopularProductsViewHolder extends RecyclerView.ViewHolder {

        PopularProductsViewHolder(LayoutInflater inflater, ViewGroup viewGroup) {
            super(inflater.inflate(R.layout.products_item, viewGroup, false));
        }

        void bind(int drawable) {
            itemView.setBackgroundResource(drawable);
        }
    }

    private class PopularProductsAdapter extends RecyclerView.Adapter<PopularProductsViewHolder> {

        ArrayList<Integer> mPopularProductsDrawableArrayList;

        PopularProductsAdapter(ArrayList<Integer> popularProductsDrawableArrayList) {
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

    private class RecommendedProductsViewHolder extends RecyclerView.ViewHolder {

        RecommendedProductsViewHolder(LayoutInflater inflater, ViewGroup viewGroup) {
            super(inflater.inflate(R.layout.products_item, viewGroup, false));
        }

        void bind(int drawable) {
            itemView.setBackgroundResource(drawable);
        }
    }

    private class RecommendedProductsAdapter extends
            RecyclerView.Adapter<RecommendedProductsViewHolder> {

        ArrayList<Integer> mRecommendedProductsDrawableArrayList;

        RecommendedProductsAdapter(ArrayList<Integer> recommendedProductsDrawableArrayList) {
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

    private class HalloweenProductsViewHolder extends RecyclerView.ViewHolder {

        HalloweenProductsViewHolder(LayoutInflater inflater, ViewGroup viewGroup) {
            super(inflater.inflate(R.layout.products_item, viewGroup, false));
        }

        void bind(int drawable) {
            itemView.setBackgroundResource(drawable);
        }
    }

    private class HalloweenProductsAdapter extends
            RecyclerView.Adapter<HalloweenProductsViewHolder> {

        ArrayList<Integer> mHalloweenProductsDrawableArrayList;

        HalloweenProductsAdapter(ArrayList<Integer> halloweenProductsDrawableArrayList) {
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
