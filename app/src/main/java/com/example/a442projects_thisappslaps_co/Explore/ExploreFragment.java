package com.example.a442projects_thisappslaps_co.Explore;

import androidx.fragment.app.Fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.appcompat.widget.Toolbar;

import androidx.fragment.app.FragmentTransaction;
import com.example.a442projects_thisappslaps_co.R;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class ExploreFragment extends Fragment implements View.OnClickListener {

    public ExploreFragment() { }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle bundle) {
        View view = inflater.inflate(R.layout.explore_fragment, container, false);

        Toolbar toolbar = view.findViewById(R.id.toolbar);
        toolbar.setNavigationOnClickListener(this);

        ExploreController exploreController = ExploreController.getInstance(getActivity());

        startFragment(new HomeFragment(exploreController));

        BottomNavigationView bottomNavBar = view.findViewById(R.id.bottom_navigation);
        bottomNavBar.setOnNavigationItemSelectedListener(menuItem -> {
            if (menuItem.getItemId() == R.id.home){
                startFragment(new HomeFragment(exploreController));
            }
            else if(menuItem.getItemId() == R.id.favorites){
                startFragment(new FavoritesFragment(exploreController));
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



