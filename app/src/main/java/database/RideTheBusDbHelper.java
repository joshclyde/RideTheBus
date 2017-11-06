package database;


import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import database.RideTheBusContract.GameTable;
import database.RideTheBusContract.PlayerStateTable;
import database.RideTheBusContract.PlayerDetailsTable;
import database.RideTheBusContract.CardTable;

public class RideTheBusDbHelper extends SQLiteOpenHelper {

    // If you change the database schema, you must increment the database version.
    public static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "RideTheBus.db";

    private static final String SQL_CREATE_GAME =
            "CREATE TABLE " + GameTable.TABLE_NAME + " (" +
                    GameTable._ID + " INTEGER PRIMARY KEY," +
                    GameTable.COLUMN_PLAYER_ID + " INTEGER," +
                    GameTable.COLUMN_CARD_ID + " INTEGER," +
                    "FOREIGN KEY (" + GameTable.COLUMN_PLAYER_ID +
                    ") references " + PlayerStateTable.TABLE_NAME + "(" + PlayerStateTable._ID + ")," +
                    "FOREIGN KEY (" + GameTable.COLUMN_CARD_ID +
                    ") references " + CardTable.TABLE_NAME + "(" + CardTable._ID + ")" +
                    ")";

    private static final String SQL_CREATE_PLAYER_STATE =
            "CREATE TABLE " + PlayerStateTable.TABLE_NAME + " (" +
                    PlayerStateTable._ID + " INTEGER PRIMARY KEY," +
                    PlayerStateTable.COLUMN_GAME_ID + " INTEGER," +
                    PlayerStateTable.COLUMN_PLAYER_ID + " INTEGER," +
                    PlayerStateTable.COLUMN_TURN + " INTEGER," +
                    PlayerStateTable.COLUMN_DRINKS_TAKEN + " INTEGER," +
                    PlayerStateTable.COLUMN_MAX_DRINKS + " INTEGER," +
                    "FOREIGN KEY (" + PlayerStateTable.COLUMN_GAME_ID+
                    ") references " + GameTable.TABLE_NAME + "(" + GameTable._ID + ")," +
                    "FOREIGN KEY (" + PlayerStateTable.COLUMN_PLAYER_ID+
                    ") references " + PlayerDetailsTable.TABLE_NAME + "(" + PlayerDetailsTable._ID + ")" +
                    ")";

    private static final String SQL_CREATE_PLAYER_DETAILS =
            "CREATE TABLE " + PlayerDetailsTable.TABLE_NAME + " (" +
                    PlayerDetailsTable._ID + " INTEGER PRIMARY KEY," +
                    PlayerDetailsTable.COLUMN_NAME+ " INTEGER," +
                    PlayerDetailsTable.COLUMN_PIC + " INTEGER," +
                    PlayerDetailsTable.COLUMN_MAX_DRINKS+ " INTEGER" +
                    ")";

    private static final String SQL_CREATE_CARD =
            "CREATE TABLE " + CardTable.TABLE_NAME + " (" +
                    CardTable._ID + " INTEGER PRIMARY KEY," +
                    CardTable.COLUMN_GAME_ID + " INTEGER," +
                    CardTable.COLUMN_PLAYER_ID + " INTEGER," +
                    CardTable.COLUMN_VALUE + " INTEGER," +
                    CardTable.COLUMN_DIAMOND_ORDER + " INTEGER," +
                    CardTable.COLUMN_PLAYER_ORDER + " INTEGER," +
                    "FOREIGN KEY (" + CardTable.COLUMN_GAME_ID +
                    ") references " + GameTable.TABLE_NAME + "(" + GameTable._ID + ")," +
                    "FOREIGN KEY (" + CardTable.COLUMN_PLAYER_ID+
                    ") references " + PlayerStateTable.TABLE_NAME + "(" + PlayerStateTable._ID + ")" +
                    ")";

    private static final String SQL_DELETE_GAME =
            "DROP TABLE IF EXISTS " + GameTable.TABLE_NAME;
    private static final String SQL_DELETE_PLAYER_STATE =
            "DROP TABLE IF EXISTS " + PlayerStateTable.TABLE_NAME;
    private static final String SQL_DELETE_PLAYER_DETAILS =
            "DROP TABLE IF EXISTS " + PlayerDetailsTable.TABLE_NAME;
    private static final String SQL_DELETE_CARD =
            "DROP TABLE IF EXISTS " + CardTable.TABLE_NAME;

    public RideTheBusDbHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    public void onCreate(SQLiteDatabase db) {
        db.execSQL(SQL_CREATE_GAME);
        db.execSQL(SQL_CREATE_PLAYER_STATE);
        db.execSQL(SQL_CREATE_PLAYER_DETAILS);
        db.execSQL(SQL_CREATE_CARD);
    }

    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // This database is only a cache for online data, so its upgrade policy is
        // to simply to discard the data and start over
        db.execSQL(SQL_DELETE_GAME);
        db.execSQL(SQL_DELETE_PLAYER_STATE);
        db.execSQL(SQL_DELETE_PLAYER_DETAILS);
        db.execSQL(SQL_DELETE_CARD);
        onCreate(db);
    }
    public void onDowngrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        onUpgrade(db, oldVersion, newVersion);
    }
}
