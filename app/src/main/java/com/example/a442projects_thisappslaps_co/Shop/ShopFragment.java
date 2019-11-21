package com.example.a442projects_thisappslaps_co.Shop;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.example.a442projects_thisappslaps_co.R;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class ShopFragment extends Fragment implements View.OnClickListener {

    private ShopController mShopController;

    public ShopFragment() { }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle bundle) {
        View view = inflater.inflate(R.layout.shop_fragment, container, false);

        mShopController = ShopController.getInstance();

        Toolbar toolbar = view.findViewById(R.id.toolbar);
        toolbar.setNavigationOnClickListener(view1 -> {
            assert getFragmentManager() != null;
            getFragmentManager().popBackStackImmediate();
        });

        BottomNavigationView bottomNavigationView = view.findViewById(R.id.bottom_navigation);
        bottomNavigationView.setOnClickListener(this);

        return view;
    }


    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.home) {
            startFragment(new ShopHomeFragment(mShopController));
        }
        else if (view.getId() == R.id.favorites) {
            startFragment(new ShopCartFragment(mShopController));
        }
    }

    private void startFragment(Fragment fragment) {
        FragmentTransaction transaction = getFragmentManager().beginTransaction();
        transaction.replace(R.id.shop_fragment_container, fragment);
        transaction.commit();
    }
}
