package app.estat.mob.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;
import org.greenrobot.greendao.DbUtils;
import org.greenrobot.greendao.database.Database;
import java.io.IOException;
import java.util.Locale;
import app.estat.mob.db.dao.DaoMaster;

public class DbOpenHelper extends DaoMaster.OpenHelper {
    private final static String DB_FILE = "db-schema-v%d.sql";

    private final static String TAG = DbOpenHelper.class.getName();

    private Context mContext;

    public DbOpenHelper(Context context, String name, SQLiteDatabase.CursorFactory factory) {
        super(context, name, factory);

        mContext = context;
    }

    @Override
    public void onCreate(Database db) {
        String initScript = String.format(Locale.getDefault(), DB_FILE, DaoMaster.SCHEMA_VERSION);
        try {
            Log.d(TAG, "Executing " + initScript + " script");
            DbUtils.executeSqlScript(mContext, db, initScript);
        } catch (IOException e) {
            Log.e(TAG, "An error occurred while executing " + initScript + " script");
        }
    }

    @Override
    public void onUpgrade(Database db, int oldVersion, int newVersion) {

    }
}
