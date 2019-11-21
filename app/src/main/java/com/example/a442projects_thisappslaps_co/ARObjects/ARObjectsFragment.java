package com.example.a442projects_thisappslaps_co.ARObjects;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.util.Pair;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.a442projects_thisappslaps_co.R;
import java.util.ArrayList;


public class ARObjectsFragment extends Fragment implements View.OnClickListener {

    private RecyclerView mARObjectsRecyclerView;
    private AddObjectListener mAddObjectListener;
    private Toolbar mToolbar;

    public ARObjectsFragment(AddObjectListener addObjectListener) {
        mAddObjectListener = addObjectListener;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle bundle) {
        View view = inflater.inflate(R.layout.ar_fragment, container, false);

        initializeViewVariable(view);
        setARObjectsAdapter();

        mToolbar.setNavigationOnClickListener(this);

        return view;
    }

    private void initializeViewVariable(View view) {
        mARObjectsRecyclerView = view.findViewById(R.id.ar_objects_recycler_view);
        mToolbar = view.findViewById(R.id.toolbar);
    }

    private void setARObjectsAdapter() {
        mARObjectsRecyclerView.setLayoutManager(new GridLayoutManager(getContext(), 3));
        mARObjectsRecyclerView.setAdapter(
                new ARObjectsAdapter(new ARObjectsController().createARObjectThumbnail(), getContext()));
    }

    @Override
    public void onClick(View view) {
        popBackStack();
    }

    public void popBackStack(){
        assert getFragmentManager() != null;
        getFragmentManager().popBackStackImmediate();
    }

    private class ARObjectsHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        private String mURI;

        ARObjectsHolder(LayoutInflater inflater, ViewGroup viewGroup) {
            super(inflater.inflate(R.layout.ar_object_item, viewGroup, false));
            itemView.setOnClickListener(this);
        }

        void bind(Pair<Integer, String> pair) {
            itemView.setBackgroundResource(pair.first);
            mURI = pair.second;

        }

        @Override
        public void onClick(View v) {
            mAddObjectListener.addObject(Uri.parse(mURI));
            popBackStack();
        }
    }

    private class ARObjectsAdapter extends RecyclerView.Adapter<ARObjectsHolder> {

        private ArrayList<Pair<Integer, String>> mARObjectDrawableResList;
        private Context mContext;

        ARObjectsAdapter(ArrayList<Pair<Integer, String>> arObjectDrawableResList, Context context) {
            mARObjectDrawableResList = arObjectDrawableResList;
            mContext = context;
        }

        @NonNull
        @Override
        public ARObjectsHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            return new ARObjectsHolder(LayoutInflater.from(mContext), parent);
        }

        @Override
        public void onBindViewHolder(@NonNull ARObjectsHolder holder, int position) {
            holder.bind(mARObjectDrawableResList.get(position));
        }

        @Override
        public int getItemCount() {
            return mARObjectDrawableResList.size();
        }
    }
}

