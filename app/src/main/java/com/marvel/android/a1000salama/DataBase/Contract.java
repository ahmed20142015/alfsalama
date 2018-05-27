package com.marvel.android.a1000salama.DataBase;

import android.content.ContentResolver;
import android.content.ContentUris;
import android.net.Uri;
import android.provider.BaseColumns;

/**
 * Created by ahmed on 26/01/18.
 */

public class Contract {


    public static final String CONTENT_AUTHORITY = "com.marvel.android.alfsalama";
        public static final Uri BASE_CONTENT_URI = Uri.parse("content://" + CONTENT_AUTHORITY);
        public static final String PATH_MOVIE = "alfsamala";
public static final class alfsalamaEntry implements BaseColumns {
    public static final Uri CONTENT_URI = BASE_CONTENT_URI.buildUpon().appendPath(PATH_MOVIE).build();
    public static final String CONTENT_TYPE =
            ContentResolver.CURSOR_DIR_BASE_TYPE + "/" + CONTENT_AUTHORITY + "/" + PATH_MOVIE;
    public static final String CONTENT_ITEM_TYPE =
            ContentResolver.CURSOR_ITEM_BASE_TYPE + "/" + CONTENT_AUTHORITY + "/" + PATH_MOVIE;
    public static final String SystemMessagesTable = "SystemMessages";
    public static final String SPTable = "ServiceProviders";
    public static final String COLUMN_SystemMessages_ID = "sys_message_id";
    public static final String COLUMN_SystemMessages_message = "message";
    public static final String COLUMN_SP_ID = "SP_ID ";
    public static final String COLUMN_SPXcor = "SPXcor";
    public static final String COLUMN_SPYcor = "SPYcor";
    public static final String COLUMN_Logo = "Logo";
    public static final String COLUMN_SPName = "SPName";
    public static final String COLUMN_SPBranchID = "SPBranchID";




    public static final String GOVERNRATETABLE = "GOVERNRATE";
    public static final String COLUMN_GOVERNRATE_ID = "GOVERNRATE_ID ";
    public static final String COLUMN_GOVERNRATE_NAME= "GOVERNRATE_NAME";

    ///////
    public static final String CITYETABLE = "CITY";
    public static final String COLUMN_CITY_ID = "CITY_ID ";
    public static final String COLUMN_CITY_NAME= "CITY_NAME";
    public static final String COLUMN_CITY_GOV_ID= "GOV_ID";


    ////

    public static final String AREATABLE = "AREA";
    public static final String COLUMN_AREA_ID = "AREA_ID ";
    public static final String COLUMN_AREA_NAME= "AREA_NAME";
    public static final String COLUMN_AREA_CITY_ID= "AREA_CITY_ID";




    public static final String CATTable = "CAT";
    public static final String COLUMN_CAT_NAME = "CATNAME ";
    public static final String COLUMN_CAT_ID= "CATID ";
    public static final String COLUMN_CAT_IDID= "ID ";



    public static Uri buildalfsalamaUri(long id) {
        return ContentUris.withAppendedId(CONTENT_URI, id);
    }
}
}