package com.example.a442projects_thisappslaps_co.Shop;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.a442projects_thisappslaps_co.Database.DatabaseCursorWrapper;
import com.example.a442projects_thisappslaps_co.Database.ShopDatabaseHelper;

import java.util.ArrayList;
import java.util.UUID;

import static com.example.a442projects_thisappslaps_co.Database.DatabaseSchema.ShopItemTable.NAME;
import static com.example.a442projects_thisappslaps_co.Database.DatabaseSchema.ShopItemTable.Cols.ADDED_TO_CART;
import static com.example.a442projects_thisappslaps_co.Database.DatabaseSchema.ShopItemTable.Cols.DESCRIPTION;
import static com.example.a442projects_thisappslaps_co.Database.DatabaseSchema.ShopItemTable.Cols.ID;
import static com.example.a442projects_thisappslaps_co.Database.DatabaseSchema.ShopItemTable.Cols.RESOURCE_NAME;
import static com.example.a442projects_thisappslaps_co.Database.DatabaseSchema.ShopItemTable.Cols.TITLE;
import static com.example.a442projects_thisappslaps_co.Database.DatabaseSchema.ShopItemTable.Cols.URL;

public class ShopController {

    private static ShopController sShopController;
    private SQLiteDatabase mSQLiteDatabase;

    public static ShopController getInstance(Context context) {
        if (sShopController == null) {
            sShopController = new ShopController(context);
        }

        return sShopController;
    }

    private ShopController(Context context) {
        mSQLiteDatabase = new ShopDatabaseHelper(context.getApplicationContext()).getWritableDatabase();
    }

    ArrayList<ShopItem> getShopList() {
        ArrayList<ShopItem> shopList = new ArrayList<>();

        try (DatabaseCursorWrapper cursorWrapper = queryShopList()) {
            cursorWrapper.moveToFirst();
            while (!cursorWrapper.isAfterLast()) {
                shopList.add(cursorWrapper.getShopItem());
                cursorWrapper.moveToNext();
            }
        }

        return shopList;
    }

    private DatabaseCursorWrapper queryShopList() {
        Cursor cursor = mSQLiteDatabase.query(NAME, null, null, null, null, null, null);
        return new DatabaseCursorWrapper(cursor);
    }

    public void addToDatabase(ShopItem shopItem) {
        mSQLiteDatabase.insert(NAME, null, getContentValues(shopItem));
    }

    public void updateEntry(ShopItem shopItem) {
        mSQLiteDatabase.update(NAME, getContentValues(shopItem), ID + "= '" + shopItem.getUUID() + "'", null);
    }

    private ContentValues getContentValues(ShopItem shopItem) {
        ContentValues contentValues = new ContentValues();

        contentValues.put(ID, shopItem.getUUID());
        contentValues.put(RESOURCE_NAME, shopItem.getResourceName());
        contentValues.put(URL, shopItem.getUrl());
        contentValues.put(TITLE, shopItem.getTitle());
        contentValues.put(DESCRIPTION, shopItem.getDescription());
        contentValues.put(ADDED_TO_CART, shopItem.isAddedToCart());

        return contentValues;
    }

    public ArrayList<ShopItem> createDummyList() {
        ArrayList<ShopItem> list = new ArrayList<>();

        list.add(new ShopItem(
                UUID.randomUUID().toString().replaceAll("[.-]", ""),
                "red_tulip",
                "https://www.dutchgrown.com/products/tulip-red-power?gclid=" +
                        "CjwKCAiA_MPuBRB5EiwAHTTvMUx52iCbEXHs9NMRTrm5a1EtZR-TZrUCK_e" +
                        "FgWdek92Gk9A8tm9SYBoCxk4QAvD_BwE",
                "Red Tulip",
                "Great gift for the holidays",
                false));

        list.add(new ShopItem(
                UUID.randomUUID().toString().replaceAll("[.-]", ""),
                "yellow_mums",
                "https://www.bluestoneperennials.com/MXF.html?utm_source=" +
                        "Froogle&utm_medium=cse&utm_campaign=Feed&utm_source=google&utm" +
                        "_medium=cpc&utm_campaign=NB_PLA_CloseStates_GOOG&utm_term=shopp" +
                        "ing&utm_content=subbwC8ly|pcrid|40107396943|pmt||pkw||pdv|c|&&gclid=C" +
                        "jwKCAiA_MPuBRB5EiwAHTTvMQ_3_i6-UqRGKCLVHFJFikq80Hcgv11DSUXedz2fVpfQ" +
                        "DcAr0ug6fxoCCNUQAvD_BwE",
                "Yellow Mums",
                "Awesome gift for mom!",
                false));

        list.add(new ShopItem(
                UUID.randomUUID().toString().replaceAll("[.-]", ""),
                "basil",
                "https://www.homedepot.com/p/Bonnie-Plants-2-32-Qt-Basil-Sweet-Basil-Genovese-" +
                        "5010/308557707",
                "Basil",
                "Great for home vegetable garden!",
                false));
        list.add(new ShopItem(
                UUID.randomUUID().toString().replaceAll("[.-]", ""),
                "purple_myrtle",
                "https://www.homedepot.com/p/Purple-Magic-Crape-Myrtle-CRMPMA03G/300122231",
                "Purple Myrtle",
                "With large, lightly fragrant flower clusters that bloom from early " +
                        "June until frost, it can be grown as a broad shrub or as a tree.",
                false));
        list.add(new ShopItem(
                UUID.randomUUID().toString().replaceAll("[.-]", ""),
                "red_myrtle",
                "https://www.homedepot.com/p/Black-Diamond-Red-Hot-Black-Diamond-Crape-Myrtle-" +
                        "CRMBRH03G/300122180",
                "Red Myrtle",
                "Crape Myrtles are excellent specimen trees, as well as natural privacy " +
                        "barriers. Many customers choose to line their driveways with this stunning tree.",
                false));
        list.add(new ShopItem(
                UUID.randomUUID().toString().replaceAll("[.-]", ""),
                "citrus",
                "https://www.homedepot.com/p/Van-Zyverden-Lemon-Citrus-Tree-Improved-Meyer-1-" +
                        "Plant-83903/307782974",
                "Citrus",
                "Grow a colorful array of fruit! These trees are ideal for growing in " +
                        "containers both indoors and outside.",
                false));
        list.add(new ShopItem(
                UUID.randomUUID().toString().replaceAll("[.-]", ""),
                "maple",
                "https://www.homedepot.com/p/Online-Orchards-Crimson-King-Maple-Tree-Bare-" +
                        "Root-SHNM001/307854325",
                "Maple",
                "The Autumn Blaze Maple (Acer x freemanii (Jeffersred) is a hybrid of" +
                        " red maple and silver maple.",
                false));
        list.add(new ShopItem(
                UUID.randomUUID().toString().replaceAll("[.-]", ""),
                "mint",
                "https://www.homedepot.com/p/Bonnie-Plants-4-5-in-Mint-Chocolate-5105/308557370",
                "Mint",
                "Great for filling the herb garden with aroma, color and texture.",
                false));
        list.add(new ShopItem(
                UUID.randomUUID().toString().replaceAll("[.-]", ""),
                "strawberries",
                "https://www.homedepot.com/p/Bonnie-Plants-4-5-in-19-3-oz-Quinault-0101/" +
                        "308557862",
                "Strawberries",
                "You'll love the ease of preparation, planting and care of your new plant.",
                false));
        list.add(new ShopItem(
                UUID.randomUUID().toString().replaceAll("[.-]", ""),
                "peppers",
                "https://www.homedepot.com/p/Bonnie-Plants-4-5-in-19-3-oz-Pepper-Red-Bell-" +
                        "2101/308557592",
                "Red Bell Peppers",
                "High yielding plant produces thick, sweet fleshed fruits maturing" +
                        " from green to a brilliant bright red skin.",
                false));
        list.add(new ShopItem(
                UUID.randomUUID().toString().replaceAll("[.-]", ""),
                "squash",
                "https://www.homedepot.com/p/Bonnie-Plants-4-5-in-Squash-Zucchini-Black-" +
                        "Beauty-4302/308557650",
                "Squash",
                "Easy to plant, and very low maintenance with a high turnout!",
                false));
        list.add(new ShopItem(
                UUID.randomUUID().toString().replaceAll("[.-]", ""),
                "cypress",
                "https://www.homedepot.com/p/2-5-Qt-Leyland-Cypress-Live-Evergreen-Tree-Rich" +
                        "-Green-Foliage-1237Q/205407895",
                "Cypress",
                " It will grow three to four feet a year and can reach 50 feet in 15 years.",
                false));
        list.add(new ShopItem(
                UUID.randomUUID().toString().replaceAll("[.-]", ""),
                "succulent",
                "https://www.homedepot.com/p/Home-Botanicals-2-in-Assorted-Succulent-" +
                        "Collection-of-20-A20/301274106",
                "Succulent",
                "Succulent arrangements are an increasingly popular choice to use for" +
                        " weddings, events or party favors ",
                false));
        list.add(new ShopItem(UUID.randomUUID().toString().replaceAll("[.-]", ""),
                "aqua_orchid",
                "https://www.homedepot.com/p/DecoBlooms-5-in-Orchid-Aqua-Color-Fused-in" +
                        "-Container-DB9066/306813012",
                "Aqua Orchid",
                "Rare and exotic, the blue Phalaenopsis orchid is simply breath taking!",
                false));
        list.add(new ShopItem(
                UUID.randomUUID().toString().replaceAll("[.-]", ""),
                "palm",
                "https://www.homedepot.com/p/Costa-Farms-Cateracterum-Palm-in-9-25-in-" +
                        "Grower-Pot-10CAT/203380908",
                "Palm",
                "Deep green, feather-like fronds with a graceful drape.",
                false));
        list.add(new ShopItem(
                UUID.randomUUID().toString().replaceAll("[.-]", ""),
                "aloe",
                "https://www.homedepot.com/p/Altman-Plants-3-5-in-Aloe-Vera-Plant-0881032" +
                        "/301706507",
                "Aloe",
                "This is the Aloe Vera medicine plant, it has many medical uses.",
                false));
        list.add(new ShopItem(
                UUID.randomUUID().toString().replaceAll("[.-]", ""),
                "bonsai",
                "https://www.homedepot.com/p/Brussel-s-Bonsai-Green-Mound-Juniper-Bonsai-" +
                        "Outdoor-DT-7079GMJ/100651255",
                "Bonsai",
                "Surprise someone special with one of our most popular bonsai plants!",
                false));
        list.add(new ShopItem(
                UUID.randomUUID().toString().replaceAll("[.-]", ""),
                "mulch",
                "https://www.homedepot.com/p/2-cu-ft-Cypress-Mulch-Blend-52050045/203579960",
                "Mulch",
                "With a rich a very deep aroma, you will leave your garden looking fresher than ever!",
                false));
        list.add(new ShopItem(
                UUID.randomUUID().toString().replaceAll("[.-]", ""),
                "miraclegro",
                "https://www.homedepot.com/p/Miracle-Gro-50-qt-Potting-Soil-Mix-72790430/" +
                        "206553441",
                "Mirclegro",
                "Help your garden blossom to the fullest it can.",
                false));
        list.add(new ShopItem(
                UUID.randomUUID().toString().replaceAll("[.-]", ""),
                "black_mulch",
                "https://www.homedepot.com/p/Earthgro-2-cu-ft-Black-Mulch-88552180/202585783",
                "Black Mulch",
                "This earthy texture gives your garden that extra pop it deserves.",
                false));
        list.add(new ShopItem(
                UUID.randomUUID().toString().replaceAll("[.-]", ""),
                "lawn_soil",
                "https://www.homedepot.com/p/Scotts-Turf-Builder-1-5-cu-ft-Lawn-Soil-" +
                        "79559750/202714346",
                "Lawn Soil",
                "Treat your lawn the way it wants to be treated.",
                false));
        list.add(new ShopItem(
                UUID.randomUUID().toString().replaceAll("[.-]", ""),
                "straw",
                "https://www.homedepot.com/p/Baled-Wheat-Straw-875333/202535381",
                "Straw",
                "Great for animal bedding",
                false));

        return list;
    }
}
