package app.estat.mob.db;

import android.content.Context;

import app.estat.mob.db.dao.DaoMaster;
import app.estat.mob.db.dao.DaoSession;

public class DbManager {
    private final static String DB_NAME = "eStatistic-db";

    private DaoSession mDaoSession;

    public DaoSession getDaoSession(Context context) {
        if (mDaoSession == null) {
            mDaoSession= new DaoMaster(new DbOpenHelper(context, DB_NAME, null)
                    .getWritableDatabase()).newSession();
        }
        return mDaoSession;
    }
}
