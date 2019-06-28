package ca.masoudbozorgi.johnabbott_ipd.db;

import android.content.Context;
import android.database.DatabaseErrorHandler;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by masoudbozorgi on 2018-03-08.
 */

public class DBOpenHelper extends SQLiteOpenHelper {

    // Optional ----------------------------------------------
    public DBOpenHelper(Context context){

        this(
                context,
                SQLCommands.DATABASE_NAME,
                null,
                Integer.valueOf(SQLCommands.SCHEMA_VERSION),
                null);
    }

    public DBOpenHelper(Context context,
                        String name,
                        SQLiteDatabase.CursorFactory factory,
                        int version) {

        super(context, name, factory, version);
    }
    //--------------------------------------------------------


    public DBOpenHelper(Context context,
                        String name,
                        SQLiteDatabase.CursorFactory factory,
                        int version,
                        DatabaseErrorHandler errorHandler) {

        super(context, name, factory, version, errorHandler);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(SQLCommands.CREATE_TABLE);
    }


    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
