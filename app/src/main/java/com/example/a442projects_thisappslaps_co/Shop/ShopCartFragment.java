package com.example.a442projects_thisappslaps_co.Shop;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.a442projects_thisappslaps_co.R;

import java.util.ArrayList;

public class ShopCartFragment extends Fragment {

    private ShopController mShopController;
    private ArrayList<ShopItem> mShoppingList;
    private ShoppingCartAdapter mShoppingCartAdapter;

    public ShopCartFragment(ShopController shopController) {
        mShopController = shopController;
    }

    @Override
    public void onCreate(Bundle onSavedInstanceState) {
        super.onCreate(onSavedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle bundle) {
        View view = inflater.inflate(R.layout.shop_list_fragment, container, false);

        mShoppingList = new ArrayList<>();

        for (ShopItem shopItem : mShopController.getShopList()) {
            if (shopItem.isAddedToCart()) {
                mShoppingList.add(shopItem);
            }
        }

        RecyclerView shoppingListRecyclerView = view.findViewById(R.id.shopping_list_recycler_view);
        shoppingListRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        mShoppingCartAdapter = new ShoppingCartAdapter(getContext());
        shoppingListRecyclerView.setAdapter(mShoppingCartAdapter);

        return view;
    }

    private class ShoppingCartViewHolder extends RecyclerView.ViewHolder {

        public ShoppingCartViewHolder(LayoutInflater inflater, ViewGroup container) {
            super(inflater.inflate(R.layout.shopping_cart_list_item, container, false));
        }

        void bind(ShopItem shopItem) {

        }
    }

    private class ShoppingCartAdapter extends RecyclerView.Adapter<ShoppingCartViewHolder> {

        private Context mContext;

        public ShoppingCartAdapter(Context context) {
            mContext = context;
        }

        @NonNull
        @Override
        public ShoppingCartViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            return new ShoppingCartViewHolder(LayoutInflater.from(mContext), parent);
        }

        @Override
        public void onBindViewHolder(@NonNull ShoppingCartViewHolder holder, int position) {
            holder.bind(mShoppingList.get(position));
        }

        @Override
        public int getItemCount() {
            return mShoppingList.size();
        }
    }
}
