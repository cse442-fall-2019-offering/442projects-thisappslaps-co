package com.example.a442projects_thisappslaps_co.Explore;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.a442projects_thisappslaps_co.Database.ArticleDatabaseHelper;
import com.example.a442projects_thisappslaps_co.Database.DatabaseCursorWrapper;
import com.example.a442projects_thisappslaps_co.R;

import java.util.ArrayList;
import java.util.UUID;

import static com.example.a442projects_thisappslaps_co.Database.DatabaseSchema.ArticleTable.Cols.FAVORITED;
import static com.example.a442projects_thisappslaps_co.Database.DatabaseSchema.ArticleTable.Cols.ID;
import static com.example.a442projects_thisappslaps_co.Database.DatabaseSchema.ArticleTable.Cols.THUMBNAIL;
import static com.example.a442projects_thisappslaps_co.Database.DatabaseSchema.ArticleTable.Cols.URI;
import static com.example.a442projects_thisappslaps_co.Database.DatabaseSchema.ArticleTable.Cols.TITLE;
import static com.example.a442projects_thisappslaps_co.Database.DatabaseSchema.ArticleTable.NAME;

public class ExploreController {

    private static ExploreController sExploreController;
    private SQLiteDatabase mSQLiteDatabase;

    public static ExploreController getInstance(Context context) {
        if (sExploreController == null) {
            sExploreController = new ExploreController(context);
        }

        return sExploreController;
    }

    private ExploreController(Context context){
        mSQLiteDatabase = new ArticleDatabaseHelper(context.getApplicationContext()).getWritableDatabase();
    }

    ArrayList<Article> getArticleList() {
        ArrayList<Article> articles = new ArrayList<>();

        try (DatabaseCursorWrapper cursorWrapper = queryProjects()) {
            cursorWrapper.moveToFirst();
            while (!cursorWrapper.isAfterLast()) {
                articles.add(cursorWrapper.getArticle());
                cursorWrapper.moveToNext();
            }
        }

        return articles;
    }

    private DatabaseCursorWrapper queryProjects() {
        Cursor cursor = mSQLiteDatabase.query(NAME, null, null, null, null, null, null);
        return new DatabaseCursorWrapper(cursor);
    }

    public void addArticleToDatabase(Article article) {
        mSQLiteDatabase.insert(NAME, null, getContentValues(article));
    }

    public void updateArticle(Article article) {
        mSQLiteDatabase.update(NAME, getContentValues(article), ID + "= '" + article.getUUID() + "'", null);
    }

    private static ContentValues getContentValues(Article article) {
        ContentValues values = new ContentValues();
        values.put(ID, article.getUUID());
        values.put(URI, article.getUrl());
        values.put(THUMBNAIL, article.getThumbnail());
        values.put(TITLE, article.getName());
        values.put(FAVORITED, article.isFavorited() ? 1 : 0);
        return values;
    }

    public ArrayList<Article> prepareArticles() {

        ArrayList<Article> articleList = new ArrayList<>();

        int[] covers = new int[]{
                R.drawable.article1,
                R.drawable.article2,
                R.drawable.article3,
                R.drawable.article4,
                R.drawable.article5,
                R.drawable.article6,
                R.drawable.article7,
                R.drawable.article8,
                R.drawable.article9,
                R.drawable.article10,
                R.drawable.article11,
                R.drawable.article12};

        Article a = new Article(
                UUID.randomUUID().toString().replaceAll("[.-]", ""),
                "Small Space Garden Strategies",
                "https://www.bhg.com/gardening/plans/small-space-garden-strategies/",
                covers[0],
                false);
        articleList.add(a);

        a = new Article(
                UUID.randomUUID().toString().replaceAll("-", ""),
                "Five Fabulous Garden Plans",
                "https://www.bhg.com/gardening/plans/easy/five-fab-garden-plans/",
                covers[1],
                false);
        articleList.add(a);

        a = new Article(
                UUID.randomUUID().toString().replaceAll("-", ""),
                "Easy-Care Summer Garden Plan",
                "https://www.bhg.com/gardening/plans/easy/easy-care-summer-garden-plan/",
                covers[2],
                false);
        articleList.add(a);

        a = new Article(
                UUID.randomUUID().toString().replaceAll("-", ""),
                "No-Fuss Garden Plans",
                "https://www.bhg.com/gardening/plans/easy/15-no-fuss-garden-plans/",
                covers[3],
                false);
        articleList.add(a);

        a = new Article(
                UUID.randomUUID().toString().replaceAll("-", ""),
                "25 Best Fall Plants and Flowers to Beautify Your Front Yard This Season",
                "https://www.countryliving.com/gardening/garden-ideas/g4662/fall-flowers/",
                covers[4],
                false);
        articleList.add(a);

        a = new Article(
                UUID.randomUUID().toString().replaceAll("-", ""),
                "Our Best Cactus Garden Tips for Creating a Stunning, at-Home Oasis",
                "https://www.countryliving.com/gardening/garden-ideas/a26265781/cactus-garden/",
                covers[5],
                false);
        articleList.add(a);

        a = new Article(
                UUID.randomUUID().toString().replaceAll("-", ""),
                "15 Best Low-Maintenance Flowers for Any Kind of Garden",
                "https://www.countryliving.com/gardening/garden-ideas/g27092607/" +
                        "low-maintenance-flowers/",
                covers[6],
                false);
        articleList.add(a);

        a = new Article(
                UUID.randomUUID().toString().replaceAll("-", ""),
                "22 Creative DIY Bench Ideas to Add to Your Garden This Year",
                "https://www.countryliving.com/gardening/garden-ideas/g3120/" +
                        "upcycled-garden-benches/",
                covers[7],
                false);
        articleList.add(a);

        a = new Article(
                UUID.randomUUID().toString().replaceAll("-", ""),
                "Colorful planting around an arbor",
                "https://www.gardengatemagazine.com/articles/garden-plans/entries/" +
                        "colorful-planting-around-an-arbor/",
                covers[8],
                false);
        articleList.add(a);

        a = new Article(
                UUID.randomUUID().toString().replaceAll("-", ""),
                "Budget-friendly garden border",
                "https://www.gardengatemagazine.com/articles/garden-plans/beds-borders/" +
                        "budget-friendly-garden-border/",
                covers[9],
                false);
        articleList.add(a);

        a = new Article(
                UUID.randomUUID().toString().replaceAll("-", ""),
                "Butterfly-friendly garden plan",
                "https://www.gardengatemagazine.com/articles/garden-plans/wildlife-friendly/" +
                        "create-a-butterfly-friendly-garden/",
                covers[10],
                false);
        articleList.add(a);

        a = new Article(
                UUID.randomUUID().toString().replaceAll("-", ""),
                "Plant a garden that will attract pollinators all season",
                "https://www.gardengatemagazine.com/articles/garden-plans/wildlife-friendly/" +
                        "plant-a-garden-that-will-attract-pollinators-all-season/",
                covers[11],
                false);
        articleList.add(a);

        return articleList;
    }
}
