package com.example.a442projects_thisappslaps_co.ARObjects;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.a442projects_thisappslaps_co.R;
import com.google.ar.sceneform.rendering.ViewRenderable;

import java.util.ArrayList;


public class ARObjectsFragment extends Fragment implements View.OnClickListener, OnItemClickListener {

    private RecyclerView mARObjectsRecyclerView;
    private ImageButton mBackButton;

    View arrayView[];
    ViewRenderable name_plant;
    ImageView bamboo, buddha, frog, grass, lupine, sunflower, tree;

    int selected = 1;

    public ARObjectsFragment() { }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        setContentView(R.layout.ar_fragment);

        //View
//        ar02 = (ImageView)findViewById(R.id.ar02);
//        ar04 = (ImageView)findViewById(R.id.ar04);
//        ar08 = (ImageView)findViewById(R.id.ar08);
//        ar09 = (ImageView)findViewById(R.id.ar09);
//        ar11 = (ImageView)findViewById(R.id.ar11);
//        ar17 = (ImageView)findViewById(R.id.ar17);
//        ar18 = (ImageView)findViewById(R.id.ar18);


    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle bundle) {
        View view = inflater.inflate(R.layout.ar_fragment, container, false);

        initializeViewVariable(view);
        mBackButton.setOnClickListener(this);
        setARObjectsAdapter();

        return view;
    }

    private void initializeViewVariable(View view) {
        mBackButton = view.findViewById(R.id.back_image_btn);
        mARObjectsRecyclerView = view.findViewById(R.id.ar_objects_recycler_view);
    }

    private void setARObjectsAdapter() {
        mARObjectsRecyclerView.setLayoutManager(new GridLayoutManager(getContext(), 3));
        mARObjectsRecyclerView.setAdapter(
                new ARObjectsAdapter(new ARObjectsController().createARObjectThumbnail(), this));
    }

    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.back_image_btn) {
            assert getFragmentManager() != null;
            getFragmentManager().popBackStackImmediate();
        }
    }

    @Override
    public void onItemClick(int position) {
        //Send Fragment Value
        assert getFragmentManager() != null;
        getFragmentManager().popBackStackImmediate();
    }

    private class ARObjectsHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        OnItemClickListener listener;
        ARObjectsHolder(LayoutInflater inflater, ViewGroup viewGroup, OnItemClickListener onItemClickListener) {
            super(inflater.inflate(R.layout.ar_object_item, viewGroup, false));
            this.listener = onItemClickListener;
            itemView.setOnClickListener(this);
        }

        void bind(Integer drawableRes) {
            itemView.setBackgroundResource(drawableRes);
        }

        @Override
        public void onClick(View v) {
            listener.onItemClick(getAdapterPosition());

        }
    }

    private class ARObjectsAdapter extends RecyclerView.Adapter<ARObjectsHolder> {

        private ArrayList<Integer> mARObjectDrawableResList;
        private OnItemClickListener listener;

        ARObjectsAdapter(ArrayList<Integer> arObjectDrawableResList, OnItemClickListener listener) {
            mARObjectDrawableResList = arObjectDrawableResList;
            this.listener = listener;
        }

        @NonNull
        @Override
        public ARObjectsHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            return new ARObjectsHolder(LayoutInflater.from(getContext()), parent, listener);
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

//    private void initializedGallery() {
//
//    }

}

