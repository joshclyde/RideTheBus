package com.joshwing.ridethebus;

import android.app.FragmentManager;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.app.Fragment;
import android.app.FragmentTransaction;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.util.Log;

import classes.Card;
import classes.DatabaseFunctions;
import classes.Game;
import classes.GameLogic;
import classes.PlayerState;
import database.RideTheBusContract.GameTable;
import database.RideTheBusContract.PlayerStateTable;
import database.RideTheBusContract.PlayerDetailsTable;
import database.RideTheBusContract.CardTable;
import database.RideTheBusDbHelper;

public class GamePlayActivity extends FragmentActivity
        implements  Stage1_1Fragment.stageOneListener {

//    GameLogic logic;
    String[] samplePlayers = {"Wing Chung Chow", "Josh Clyde"};
    long gameId;
    int index;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_play);

        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        gameId = bundle.getLong("gameId");
        // logic = this.loadDatabase(gameId);


        if (findViewById(R.id.gamePlayFragmentContainer) != null) {

//            if (logic.isStart()) {
//                logic.startGame();
//            }
//
//            if (logic.isStage1()) {
//            }

            Stage1_1Fragment stage1 = new Stage1_1Fragment();
            Bundle args = new Bundle();
//            args.putString("playerName", samplePlayers[0]);
            RideTheBusDbHelper dbHelper = new RideTheBusDbHelper(this);
            DatabaseFunctions.nextCard(dbHelper, gameId);
            args.putString("playerName", DatabaseFunctions.getCurrentPlayerName(dbHelper, gameId));
            args.putInt("index", 0);

            stage1.setArguments(args);
            android.support.v4.app.FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
            ft.add(R.id.gamePlayFragmentContainer, stage1, samplePlayers[0]);
            ft.commit();

        }

    }

    private GameLogic loadDatabase(Long gameId) {
        RideTheBusDbHelper dbHelper = new RideTheBusDbHelper(this);
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        Game game = getGame(db, gameId);
        PlayerState[] playerStates = getPlayerStates(db, gameId);
        Card[] cards = getCards(db, gameId);
        return new GameLogic(game, playerStates, cards);
    }

    private Game getGame(SQLiteDatabase db, Long gameId) {
        // columns getting
        String[] projection = {
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

        Cursor cursor = db.query(
                GameTable.TABLE_NAME,                     // The table to query
                projection,                           // The columns to return
                selection,                                // The columns for the WHERE clause
                selectionArgs,                            // The values for the WHERE clause
                null,                                     // don't group the rows
                null,                                     // don't filter by row groups
                sortOrder                                 // The sort turn
        );

        cursor.moveToNext();
        long gamePlayerId = cursor.getLong(cursor.getColumnIndexOrThrow(GameTable.COLUMN_PLAYER_ID));
        long gameCardId = cursor.getLong(cursor.getColumnIndexOrThrow(GameTable.COLUMN_CARD_ID));
        Game game = new Game(gameId, gamePlayerId, gameCardId);
        cursor.close();

        return game;
    }

    private PlayerState[] getPlayerStates(SQLiteDatabase db, Long gameId) {
        // columns getting
        String[] projection = {
                PlayerStateTable._ID,
                PlayerStateTable.COLUMN_GAME_ID,
                PlayerStateTable.COLUMN_PLAYER_ID,
                PlayerStateTable.COLUMN_TURN,
                PlayerStateTable.COLUMN_MAX_DRINKS,
                PlayerStateTable.COLUMN_DRINKS_TAKEN
        };

        // Filter results WHERE "title" = 'My Title'
        String selection = PlayerStateTable.COLUMN_GAME_ID + " = ?";
        String[] selectionArgs = { Long.toString(gameId) };

        // How you want the results sorted in the resulting Cursor
        String sortOrder =
                PlayerStateTable.COLUMN_TURN + " ASC";

        Cursor cursor = db.query(
                PlayerStateTable.TABLE_NAME,                     // The table to query
                projection,                           // The columns to return
                selection,                                // The columns for the WHERE clause
                selectionArgs,                            // The values for the WHERE clause
                null,                                     // don't group the rows
                null,                                     // don't filter by row groups
                sortOrder                                 // The sort turn
        );

        PlayerState[] playerStates = new PlayerState[cursor.getCount()];
        int counter = 0;
        while (cursor.moveToNext()) {
            long playerId = cursor.getLong(cursor.getColumnIndexOrThrow(PlayerStateTable._ID));
            long playerGameId = cursor.getLong(cursor.getColumnIndexOrThrow(PlayerStateTable.COLUMN_GAME_ID));
            long playerPlayerId = cursor.getLong(cursor.getColumnIndexOrThrow(PlayerStateTable.COLUMN_PLAYER_ID));
            int playerTurn= cursor.getInt(cursor.getColumnIndexOrThrow(PlayerStateTable.COLUMN_TURN));
            int playerDrinksTaken = cursor.getInt(cursor.getColumnIndexOrThrow(PlayerStateTable.COLUMN_DRINKS_TAKEN));
            int playerMaxDrinks = cursor.getInt(cursor.getColumnIndexOrThrow(PlayerStateTable.COLUMN_MAX_DRINKS));
            playerStates[counter] = new PlayerState(playerId, playerGameId, playerPlayerId, playerTurn, playerDrinksTaken, playerMaxDrinks);
        }
        cursor.close();

        return playerStates;
    }

    private Card[] getCards(SQLiteDatabase db, Long gameId) {
        // columns getting
        String[] projection = {
                CardTable._ID,
                CardTable.COLUMN_GAME_ID,
                CardTable.COLUMN_PLAYER_ID,
                CardTable.COLUMN_VALUE,
                CardTable.COLUMN_PLAYER_ORDER,
                CardTable.COLUMN_DIAMOND_ORDER
        };

        // Filter results WHERE "title" = 'My Title'
        String selection = CardTable.COLUMN_GAME_ID + " = ?";
        String[] selectionArgs = { Long.toString(gameId) };

        // How you want the results sorted in the resulting Cursor
        String sortOrder =
                CardTable.COLUMN_VALUE + " ASC";

        Cursor cursor = db.query(
                CardTable.TABLE_NAME,                     // The table to query
                projection,                           // The columns to return
                selection,                                // The columns for the WHERE clause
                selectionArgs,                            // The values for the WHERE clause
                null,                                     // don't group the rows
                null,                                     // don't filter by row groups
                sortOrder                                 // The sort turn
        );

        Card[] cards = new Card[cursor.getCount()]; // should be 52 cards

        int counter = 0;
        while (cursor.moveToNext()) {
            long cardId = cursor.getLong(cursor.getColumnIndexOrThrow(CardTable._ID));
            long playerId = cursor.getLong(cursor.getColumnIndexOrThrow(CardTable.COLUMN_PLAYER_ID));
            int diamondOrder = cursor.getInt(cursor.getColumnIndexOrThrow(CardTable.COLUMN_DIAMOND_ORDER));
            int playerOrder = cursor.getInt(cursor.getColumnIndexOrThrow(CardTable.COLUMN_PLAYER_ORDER));
            int value = cursor.getInt(cursor.getColumnIndexOrThrow(CardTable.COLUMN_VALUE));

            cards[counter] = new Card(cardId, gameId, diamondOrder, playerId, playerOrder, value);
        }
        cursor.close();

        return cards;
    }

//    private void nextFragment(Fragment fragment) {
//        android.support.v4.app.FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
//        transaction.replace(R.id.gamePlayFragmentContainer, fragment);
//        transaction.addToBackStack(null);
//        transaction.commit();
//    }


    //Implement next Player method, simply replace the old one IF there is a next player
    //o.w. move on to stage 2
    @Override
    public void nextPlayer(){
        RideTheBusDbHelper dbHelper = new RideTheBusDbHelper(this);
        index++;
        if (index < 2) {
            Stage1_1Fragment stage1 = new Stage1_1Fragment();
            Bundle args = new Bundle();
//            args.putString("playerName", samplePlayers[index]);
            args.putString("playerName", DatabaseFunctions.getCurrentPlayerName(dbHelper, gameId));
            args.putInt("index", index);
            stage1.setArguments(args);
            android.support.v4.app.FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
            ft.replace(R.id.gamePlayFragmentContainer, stage1, samplePlayers[index]);
            ft.commit();
        } else{

        }
    }
    boolean takeDrink = false;
    @Override
    public int doNextCard(int choice) {
        RideTheBusDbHelper dbHelper = new RideTheBusDbHelper(this);
        int card = DatabaseFunctions.getCurrentCard(dbHelper, gameId);
        takeDrink = DatabaseFunctions.isTakeDrink(dbHelper, gameId, choice);
        DatabaseFunctions.nextCard(dbHelper, gameId);

        return card;
    };
    @Override
    public boolean shouldTakeDrink() {
        return takeDrink;
    }

}
