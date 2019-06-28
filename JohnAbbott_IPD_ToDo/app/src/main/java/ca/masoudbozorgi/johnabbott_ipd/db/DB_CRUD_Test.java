package ca.masoudbozorgi.johnabbott_ipd.db;

import android.content.ContentValues;
import android.database.Cursor;

import java.util.ArrayList;

import ca.masoudbozorgi.johnabbott_ipd.model.Flower;

public class DB_CRUD_Test {

    ArrayList<Flower> flowerArrayList;

    // Database .....................
    DBManager dbManager;
    Cursor cursor;
    //...............................

    Flower modelItem_0, modelItem_1, modelItem_2, modelItem_3, modelItem_4,
           modelItem_5, modelItem_6, modelItem_7, modelItem_8, modelItem_9 ;

    /*
    *
    *  SQLite --> SQLiteDatabase -->                                          |--> Cursor        --> Array of Objects
    *       SQLiteOpenHelper [Create DB and Tables]  -->                      |
    *           sqLiteOpenHelper.getWritableDatabase() [Writable access to DB]|
    *                                                                         |
    *                                                                         |<-- ContentValue  <-- Java Object
    * */

    public DB_CRUD_Test(DBManager dbManager) {
        this.dbManager = dbManager;

        initializeModel();
        insertDummyDataToDB();
        readFromDB();
        deleteFromDB();
    }

    private void initializeModel() {

        flowerArrayList = new ArrayList<>();

        modelItem_0 = new Flower(2391, "cat0", "name0_db", "Some instruction for test reading from DB 0", 10, "california_snow.jpg");
        modelItem_1 = new Flower(2391, "cat1", "name1_db", "Some instruction for test reading from DB 1", 11, "princess_flower.jpg");
        modelItem_2 = new Flower(2392, "cat2", "name2_db", "Some instruction for test reading from DB 2", 12, "haight_ashbury.jpg");
        modelItem_3 = new Flower(2393, "cat3", "name3_db", "Some instruction for test reading from DB 3", 13, "mona_lavender.jpg");
        modelItem_4 = new Flower(2394, "cat4", "name4_db", "Some instruction for test reading from DB 4", 14, "camellia.jpg");
        modelItem_5 = new Flower(2395, "cat5", "name5_db", "Some instruction for test reading from DB 5", 15, "bougainvillea.jpg");
        modelItem_6 = new Flower(2396, "cat6", "name6_db", "Some instruction for test reading from DB 6", 16, "rosa_burgundy.jpg");
        modelItem_7 = new Flower(2397, "cat7", "name7_db", "Some instruction for test reading from DB 7", 17, "rosa_iceberg.jpg");
        modelItem_8 = new Flower(2398, "cat8", "name8_db", "Some instruction for test reading from DB 8", 18, "bonsai.jpg");
        modelItem_9 = new Flower(2399, "cat9", "name9_db", "Some instruction for test reading from DB 9", 19, "calibrachoa.jpg");

        flowerArrayList.add(modelItem_0);
        flowerArrayList.add(modelItem_1);
        flowerArrayList.add(modelItem_2);
        flowerArrayList.add(modelItem_3);
        flowerArrayList.add(modelItem_4);
        flowerArrayList.add(modelItem_5);
        flowerArrayList.add(modelItem_6);
        flowerArrayList.add(modelItem_7);
        flowerArrayList.add(modelItem_8);
        flowerArrayList.add(modelItem_9);
    }

    private void insertDummyDataToDB() {

        // Insert an object in DB ------------------------------------------------------------------
        ContentValues contentValues_0 = dbManager.javaObjectToContentValue(modelItem_0);
        ContentValues contentValues_1 = dbManager.javaObjectToContentValue(modelItem_1);
        ContentValues contentValues_2 = dbManager.javaObjectToContentValue(modelItem_2);
        ContentValues contentValues_3 = dbManager.javaObjectToContentValue(modelItem_3);
        ContentValues contentValues_4 = dbManager.javaObjectToContentValue(modelItem_4);
        ContentValues contentValues_5 = dbManager.javaObjectToContentValue(modelItem_5);
        ContentValues contentValues_6 = dbManager.javaObjectToContentValue(modelItem_6);
        ContentValues contentValues_7 = dbManager.javaObjectToContentValue(modelItem_7);
        ContentValues contentValues_8 = dbManager.javaObjectToContentValue(modelItem_8);
        ContentValues contentValues_9 = dbManager.javaObjectToContentValue(modelItem_9);

        dbManager.insertInTable("flower", contentValues_0);
        dbManager.insertInTable(SQLCommands.TABLE_NAME, contentValues_1);
        dbManager.insertInTable(SQLCommands.TABLE_NAME, contentValues_2);
        dbManager.insertInTable(SQLCommands.TABLE_NAME, contentValues_3);
        dbManager.insertInTable(SQLCommands.TABLE_NAME, contentValues_4);
        dbManager.insertInTable(SQLCommands.TABLE_NAME, contentValues_5);
        dbManager.insertInTable(SQLCommands.TABLE_NAME, contentValues_6);
        dbManager.insertInTable(SQLCommands.TABLE_NAME, contentValues_7);
        dbManager.insertInTable(SQLCommands.TABLE_NAME, contentValues_8);
        dbManager.insertInTable(SQLCommands.TABLE_NAME, contentValues_9);
        //------------------------------------------------------------------------------------------
    }

    private void readFromDB() {
        // Read from DB ----------------------------------------------------------------------------
        cursor = dbManager.queryInTable(SQLCommands.TABLE_NAME,
                SQLCommands.TABLE_COLUMNS,
                null,
                null);
        System.out.println("----------------------- Read from database after insertion");
        System.out.println(dbManager.cursorToArrayList(cursor));
        //------------------------------------------------------------------------------------------
    }

    private void deleteFromDB() {
        // Delete from DB --------------------------------------------------------------------------
        dbManager.deleteRowFromTable(SQLCommands.TABLE_NAME,
                SQLCommands.COLUMN_NAME + "=? OR " +
                        SQLCommands.COLUMN_NAME + "=?"
                , new String[]{"name8_db", "name9_db"});
        //------------------------------------------------------------------------------------------


        // General Simple Read from DB to check existence of deleted items in DB -------------------
        cursor = dbManager.queryInTable(SQLCommands.TABLE_NAME,
                SQLCommands.TABLE_COLUMNS,
                null,
                null);
        System.out.println("----------------------- Read from database after deletion");
        System.out.println(dbManager.cursorToArrayList(cursor));
    }
}
