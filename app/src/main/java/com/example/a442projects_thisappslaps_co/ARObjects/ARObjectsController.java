package com.example.a442projects_thisappslaps_co.ARObjects;

import android.util.Pair;

import com.example.a442projects_thisappslaps_co.R;

import java.util.ArrayList;

public class ARObjectsController {

    public ARObjectsController() { }

    public ArrayList<Pair<Integer, String>> createARObjectThumbnail() {
        ArrayList<Pair<Integer, String>> thumbnailList = new ArrayList<>();
        thumbnailList.add(new Pair<>(R.drawable.ar01, "ar01.sfb"));
        thumbnailList.add(new Pair<>(R.drawable.ar02, "ar02.sfb"));
        thumbnailList.add(new Pair<>(R.drawable.ar03, "ar03.sfb"));
        thumbnailList.add(new Pair<>(R.drawable.ar04, "ar04.sfb"));
        thumbnailList.add(new Pair<>(R.drawable.ar05, "ar05.sfb"));
        thumbnailList.add(new Pair<>(R.drawable.ar06, "ar06.sfb"));
        thumbnailList.add(new Pair<>(R.drawable.ar07, "ar07.sfb"));
        thumbnailList.add(new Pair<>(R.drawable.ar08, "ar08.sfb"));
        thumbnailList.add(new Pair<>(R.drawable.ar09, "ar09.sfb"));
        thumbnailList.add(new Pair<>(R.drawable.ar10, "ar10.sfb"));
        thumbnailList.add(new Pair<>(R.drawable.ar11, "ar11.sfb"));
        thumbnailList.add(new Pair<>(R.drawable.ar12, "ar12.sfb"));
        thumbnailList.add(new Pair<>(R.drawable.ar13, "ar13.sfb"));
        thumbnailList.add(new Pair<>(R.drawable.ar14, "ar14.sfb"));
        thumbnailList.add(new Pair<>(R.drawable.ar15, "ar15.sfb"));
        thumbnailList.add(new Pair<>(R.drawable.ar16, "ar16.sfb"));
        thumbnailList.add(new Pair<>(R.drawable.ar17, "ar17.sfb"));
        thumbnailList.add(new Pair<>(R.drawable.ar18, "ar18.sfb"));
        thumbnailList.add(new Pair<>(R.drawable.ar19, "ar19.sfb"));

        return thumbnailList;
    }
}
