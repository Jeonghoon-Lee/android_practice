package ca.masoudbozorgi.johnabbott_ipd.db;

import android.app.Application;
import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;

import ca.masoudbozorgi.johnabbott_ipd.model.Flower;

/**
 * Created by masoudbozorgi on 2018-03-08.
 */

public class DBManager extends Application {
//  Attention: DBManager extends Application -------------

    // Manually must be added to AndroidManifest.xml
// <application
//        android:name=".db.DBManager"
// </application>
//--------------------------------------------------------

    public DBOpenHelper dbOpenHelper;
    public SQLiteDatabase sqLiteDatabase;


    //----------------------------------------------------
    @Override
    public void onCreate() {
        super.onCreate();

        // Create Database
        dbOpenHelper = new DBOpenHelper(this);

        // Create a reference to database for CRUD
        sqLiteDatabase = dbOpenHelper.getWritableDatabase();
        //sqLiteDatabase.execSQL("PRAGMA foreign_keys=ON");
    }

    @Override
    public void onTerminate() {
        super.onTerminate();
    }
    //----------------------------------------------------


    public long insertInTable(String tableName, ContentValues values) {
        return sqLiteDatabase.insert(tableName,
                null, values);
    }

    public Cursor queryInTable(String tableName, String[] columns, String selection, String[] selectionArgs) {
        return sqLiteDatabase.query(tableName, columns,
                selection,
                selectionArgs,
                null,
                null,
                null);
    }

    public int deleteRowFromTable(String tableName, String selection, String[] selectionArgs) {
        return sqLiteDatabase.delete(tableName,
                selection,
                selectionArgs);
    }

    public int updateTable(String tableName, ContentValues values, String whereClause, String[] whereArgs) {
        return sqLiteDatabase.update(tableName,
                values,
                whereClause,
                whereArgs);
    }



    public ContentValues javaObjectToContentValue(Flower flower) {

        ContentValues contentValues = new ContentValues();

        contentValues.put(SQLCommands.COLUMN_NAME, flower.getName());
        contentValues.put(SQLCommands.COLUMN_CATEGORY, flower.getCategory());
        contentValues.put(SQLCommands.COLUMN_INSTRUCTIONS, flower.getInstructions());
        contentValues.put(SQLCommands.COLUMN_PRICE, flower.getPrice());
        contentValues.put(SQLCommands.COLUMN_PHOTO, flower.getPhoto());

        return contentValues;
    }

    public ArrayList<Flower> cursorToArrayList(Cursor cursor) {

        ArrayList<Flower> flowers = new ArrayList<>();
        cursor.moveToFirst();

        if (cursor.getCount() != 0) {
            do {
                //Flower modelItem_0 = new Flower(2391, "cat0", "name0", "ins0", 10, "california_snow.jpg");
                Flower flower = new Flower();

                flower.setProductId(cursor.getInt(0));
                flower.setCategory(cursor.getString(1));
                flower.setName(cursor.getString(2));
                flower.setInstructions(cursor.getString(3));
                flower.setPrice(cursor.getDouble(4));
                flower.setPhoto(cursor.getString(5));

                flowers.add(flower);

            } while (cursor.moveToNext());
        }
        return flowers;
    }
}
