package com.example.a442projects_thisappslaps_co.Explore;

import androidx.fragment.app.Fragment;

import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.util.Pair;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.widget.Toolbar;

import androidx.annotation.NonNull;
import androidx.appcompat.content.res.AppCompatResources;
import androidx.core.graphics.drawable.DrawableCompat;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.a442projects_thisappslaps_co.DeviceSpecUtil;
import com.example.a442projects_thisappslaps_co.MainActivity;
import com.example.a442projects_thisappslaps_co.R;
import com.google.android.material.bottomnavigation.BottomNavigationMenu;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.ArrayList;
import java.util.List;

public class ExploreFragment extends Fragment implements View.OnClickListener {

    private ExploreController mExploreController;

    public ExploreFragment() {}

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle bundle) {
        View view = inflater.inflate(R.layout.explore_fragment, container, false);

        mExploreController = new ExploreController(getContext());

        Toolbar toolbar = view.findViewById(R.id.toolbar);
        toolbar.setNavigationOnClickListener(this);

        startFragment(new HomeFragment(mExploreController));

        BottomNavigationView bottomNavBar = view.findViewById(R.id.bottom_navigation);
        bottomNavBar.setOnNavigationItemSelectedListener(menuItem -> {
            if (menuItem.getItemId() == R.id.home){
                startFragment(new HomeFragment(mExploreController));
            }
            else if(menuItem.getItemId() == R.id.favorites){
                startFragment(new FavoritesFragment(mExploreController));
            }
            return true;
        });

        return view;
    }

    // for the back button on the toolbar
    @Override
    public void onClick(View view) {
//        if (view.getId() == R.id.toolbar) {
        assert getFragmentManager() != null;
        getFragmentManager().popBackStackImmediate();
//        }
    }

    private void startFragment(Fragment fragment) {
        FragmentTransaction transaction = getFragmentManager().beginTransaction();
        transaction.replace(R.id.explore_fragment_container, fragment);
        transaction.commit();
    }
}



