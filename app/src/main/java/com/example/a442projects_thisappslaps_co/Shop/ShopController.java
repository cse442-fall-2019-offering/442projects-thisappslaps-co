package com.example.a442projects_thisappslaps_co.Shop;

import com.example.a442projects_thisappslaps_co.R;
import java.util.ArrayList;

class ShopController {

    ShopController() { }

    ArrayList<ShopItem> createDummyList() {
        ArrayList<ShopItem> list = new ArrayList<>();

        list.add(new ShopItem(R.drawable.basil, "https://www.homedepot.com/p/Bonnie-Plants-2-32-Qt-Basil-Sweet-Basil-Genovese-5010/308557707"));
        list.add(new ShopItem(R.drawable.purple_myrtle, "https://www.homedepot.com/p/Purple-Magic-Crape-Myrtle-CRMPMA03G/300122231"));
        list.add(new ShopItem(R.drawable.red_myrtle, "https://www.homedepot.com/p/Black-Diamond-Red-Hot-Black-Diamond-Crape-Myrtle-CRMBRH03G/300122180"));
        list.add(new ShopItem(R.drawable.citrus, "https://www.homedepot.com/p/Van-Zyverden-Lemon-Citrus-Tree-Improved-Meyer-1-Plant-83903/307782974"));
        list.add(new ShopItem(R.drawable.maple, "https://www.homedepot.com/p/Online-Orchards-Crimson-King-Maple-Tree-Bare-Root-SHNM001/307854325"));
        list.add(new ShopItem(R.drawable.mint, "https://www.homedepot.com/p/Bonnie-Plants-4-5-in-Mint-Chocolate-5105/308557370"));
        list.add(new ShopItem(R.drawable.strawberries, "https://www.homedepot.com/p/Bonnie-Plants-4-5-in-19-3-oz-Quinault-0101/308557862"));
        list.add(new ShopItem(R.drawable.peppers, "https://www.homedepot.com/p/Bonnie-Plants-4-5-in-19-3-oz-Pepper-Red-Bell-2101/308557592"));
        list.add(new ShopItem(R.drawable.squash, "https://www.homedepot.com/p/Bonnie-Plants-4-5-in-Squash-Zucchini-Black-Beauty-4302/308557650"));
        list.add(new ShopItem(R.drawable.cypress, "https://www.homedepot.com/p/2-5-Qt-Leyland-Cypress-Live-Evergreen-Tree-Rich-Green-Foliage-1237Q/205407895"));
        list.add(new ShopItem(R.drawable.succulent, "https://www.homedepot.com/p/Home-Botanicals-2-in-Assorted-Succulent-Collection-of-20-A20/301274106"));
        list.add(new ShopItem(R.drawable.aqua_orchid, "https://www.homedepot.com/p/DecoBlooms-5-in-Orchid-Aqua-Color-Fused-in-Container-DB9066/306813012"));
        list.add(new ShopItem(R.drawable.palm, "https://www.homedepot.com/p/Costa-Farms-Cateracterum-Palm-in-9-25-in-Grower-Pot-10CAT/203380908"));
        list.add(new ShopItem(R.drawable.aloe, "https://www.homedepot.com/p/Altman-Plants-3-5-in-Aloe-Vera-Plant-0881032/301706507"));
        list.add(new ShopItem(R.drawable.bonsai, "https://www.homedepot.com/p/Brussel-s-Bonsai-Green-Mound-Juniper-Bonsai-Outdoor-DT-7079GMJ/100651255"));
        list.add(new ShopItem(R.drawable.mulch, "https://www.homedepot.com/p/2-cu-ft-Cypress-Mulch-Blend-52050045/203579960"));
        list.add(new ShopItem(R.drawable.miraclegro, "https://www.homedepot.com/p/Miracle-Gro-50-qt-Potting-Soil-Mix-72790430/206553441"));
        list.add(new ShopItem(R.drawable.black_mulch, "https://www.homedepot.com/p/Earthgro-2-cu-ft-Black-Mulch-88552180/202585783"));
        list.add(new ShopItem(R.drawable.lawn_soil, "https://www.homedepot.com/p/Scotts-Turf-Builder-1-5-cu-ft-Lawn-Soil-79559750/202714346"));
        list.add(new ShopItem(R.drawable.straw, "https://www.homedepot.com/p/Baled-Wheat-Straw-875333/202535381"));

        return list;
    }
}
