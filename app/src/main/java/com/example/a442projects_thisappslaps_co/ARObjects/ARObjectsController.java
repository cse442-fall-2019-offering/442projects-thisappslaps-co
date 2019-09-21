package com.example.a442projects_thisappslaps_co.ARObjects;

import com.example.a442projects_thisappslaps_co.R;

import java.util.ArrayList;

public class ARObjectsController {

    public ARObjectsController() { }

    public ArrayList<Integer> createARObjectsDummyList() {
        ArrayList<Integer> dummyList = new ArrayList<>();

        for (int i = 0; i < 50; i++) {
            if (i % 10 == 0) {
                dummyList.add(R.drawable.pink_rose);
            }
            else if (i % 5 == 0) {
                dummyList.add(R.drawable.red_rose);
            }
            else if (i % 4 == 0) {
                dummyList.add(R.drawable.bush);
            }
            else if (i % 3 == 0) {
                dummyList.add(R.drawable.tree_1);
            }
            else if (i % 2 == 0) {
                dummyList.add(R.drawable.tree_2);
            }
            else {
                dummyList.add(R.drawable.tree_3);
            }
        }

        return dummyList;
    }
}
