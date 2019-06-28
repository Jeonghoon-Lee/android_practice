package ca.masoudbozorgi.johnabbott_ipd.db;

public class SQLCommands {

    final public static String DATABASE_NAME       = "flower_database";
    final public static String SCHEMA_VERSION      = "1";

    final public static String TABLE_NAME          = "flower";
    final public static String COLUMN_ID           = "_id";

    final public static String COLUMN_CATEGORY     = "category";
    final public static String COLUMN_NAME         = "name";
    final public static String COLUMN_INSTRUCTIONS = "instructions";
    final public static String COLUMN_PRICE        = "price";
    final public static String COLUMN_PHOTO        = "photo";

    final public static String[] TABLE_COLUMNS     = {"_id", "category", "name", "instructions", "price", "photo"};

    final public static String CREATE_TABLE        = "CREATE TABLE flower(_id INTEGER PRIMARY KEY , category TEXT , name TEXT , instructions TEXT , price DOUBLE , photo TEXT );";
}
