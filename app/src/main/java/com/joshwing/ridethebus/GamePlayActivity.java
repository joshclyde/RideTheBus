package com.joshwing.ridethebus;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v4.app.FragmentActivity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import database.RideTheBusContract.GameTable;
import database.RideTheBusContract.PlayerStateTable;
import database.RideTheBusContract.PlayerDetailsTable;
import database.RideTheBusContract.CardTable;
import database.RideTheBusDbHelper;

public class GamePlayActivity extends FragmentActivity {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_play);
        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        long gameId = bundle.getLong("gameId");
        //Log.d("gamesetup gameid", gameId.toString());
        this.loadDatabase(gameId);
    }

    private void loadDatabase(Long gameId) {
        RideTheBusDbHelper dbHelper = new RideTheBusDbHelper(this);
        SQLiteDatabase db = dbHelper.getReadableDatabase();

        // Define a projection that specifies which columns from the database
        // you will actually use after this query.
        String[] gameProjection = {
                GameTable._ID,
                GameTable.COLUMN_CARD_ID,
                GameTable.COLUMN_PLAYER_ID
        };

        // Filter results WHERE "title" = 'My Title'
        String selection = GameTable._ID + " = ?";
        String[] selectionArgs = { Long.toString(gameId) };

        // How you want the results sorted in the resulting Cursor
        String sortOrder =
                GameTable._ID + " DESC";

        Cursor gameCursor = db.query(
                GameTable.TABLE_NAME,                     // The table to query
                gameProjection,                           // The columns to return
                selection,                                // The columns for the WHERE clause
                selectionArgs,                            // The values for the WHERE clause
                null,                                     // don't group the rows
                null,                                     // don't filter by row groups
                sortOrder                                 // The sort turn
        );

        gameCursor.moveToNext();
        Log.d("GamePlayActivity", Long.toString(gameCursor.getLong(gameCursor.getColumnIndexOrThrow(GameTable._ID))));
        gameCursor.close();
    }
}
