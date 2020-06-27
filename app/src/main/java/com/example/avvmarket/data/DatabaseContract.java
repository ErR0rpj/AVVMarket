package com.example.avvmarket.data;

import android.net.Uri;
import android.provider.BaseColumns;

public final class DatabaseContract {

    private DatabaseContract(){}

    public static final String CONTENT_AUTHORITY = "com.example.avvmarket"; //Base package of app
    public static final Uri BASE_CONTENT_URI = Uri.parse("content://" + CONTENT_AUTHORITY);
    public static final String PATH_DB =  "stocks";

    public static abstract class StocksEntry implements BaseColumns{

        public static final Uri CONTENT_URI = Uri.withAppendedPath(BASE_CONTENT_URI, PATH_DB);

        public static final String TABLE_NAME = "stocks";
        public static final String _ID = "_id";
        public static final String COLUMN_NAME = "name";
        public static final String COLUMN_CODE = "code";
        public static final String COLUMN_STARTPRICE = "startprice";
        public static final String COLUMN_CURRENTPRICE = "currentprice";
        public static final String COLUMN_GANG = "gang";
        public static final String COLUMN_BUYPRICE = "buyprice";
        public static final String COLUMN_ISBUY = "isbuy";

        public static final int GANG_EXTRAS = 0;
        public static final int GANG_HEAD = 1;
        public static final int GANG_ADMIN = 2;
        public static final int GANG_HP3 = 3;
        public static final int GANG_TEACHERS = 4;

    }
}
