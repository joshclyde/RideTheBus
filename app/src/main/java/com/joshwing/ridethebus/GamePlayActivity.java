package com.joshwing.ridethebus;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;

import classes.DatabaseFunctions;
import database.RideTheBusDbHelper;

public class GamePlayActivity extends FragmentActivity
        implements  Stage1_1Fragment.stageOneListener,
                    Stage2_1Fragment.stageTwoListener {

//    GameLogic logic;
    String[] samplePlayers = {"Wing Chung Chow", "Josh Clyde"};
    int numOfPlayers = samplePlayers.length;
    long gameId;
    int index;
    int stage1NumOfCards;
    SharedPreferences sharedPref;
    static String isStage1 = "isStage1";
    static String numCardsFlipped = "numCardsFlipped";
    static String indexString = "indexString";
    static String stage1NumOfCardsString = "stage1NumOfCardsString";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_play);

        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        gameId = bundle.getLong("gameId");
        // logic = this.loadDatabase(gameId);

        sharedPref = getApplicationContext().getSharedPreferences(DatabaseFunctions.sharedPrefId, 0);
        boolean isStage1 = sharedPref.getBoolean(this.isStage1, true);
        index = sharedPref.getInt(this.indexString, 0);
        stage1NumOfCards = sharedPref.getInt(this.stage1NumOfCardsString, -1);

        if (findViewById(R.id.gamePlayFragmentContainer) != null) {

//            if (logic.isStart()) {
//                logic.startGame();
//            }
//
//            if (logic.isStage1()) {
//            }

            if (isStage1) {
                Stage1_1Fragment stage1 = new Stage1_1Fragment();
                Bundle args = new Bundle();
//            args.putString("playerName", samplePlayers[0]);
                RideTheBusDbHelper dbHelper = new RideTheBusDbHelper(this);
                if (stage1NumOfCards == -1) {
                    DatabaseFunctions.nextCard(dbHelper, gameId);
                    stage1NumOfCards = 0;
                    SharedPreferences.Editor editor = sharedPref.edit();
                    editor.putInt(this.stage1NumOfCardsString, 0);
                    editor.commit();
                }
                args.putString("playerName", DatabaseFunctions.getCurrentPlayerName(dbHelper, gameId));
                args.putInt(this.indexString, index);
                args.putInt(this.stage1NumOfCardsString, stage1NumOfCards);
                stage1.setArguments(args);
                android.support.v4.app.FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
                ft.add(R.id.gamePlayFragmentContainer, stage1, samplePlayers[0]);
                ft.commit();
            } else {

                Stage2_1Fragment stage2 = new Stage2_1Fragment();
                Bundle args = new Bundle();
                //Switch between 4 or 5
                args.putInt("maxNumRows", 4);
                int numOfCardsFlipped = sharedPref.getInt(this.numCardsFlipped, 0);
                args.putInt(this.numCardsFlipped, numOfCardsFlipped);
                stage2.setArguments(args);
                android.support.v4.app.FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
                ft.replace(R.id.gamePlayFragmentContainer, stage2);
                ft.commit();
            }

        }

    }

    //Implement next Player method, simply replace the old one IF there is a next player
    //o.w. move on to stage 2
    @Override
    public void nextPlayer(){
        RideTheBusDbHelper dbHelper = new RideTheBusDbHelper(this);
        index++;

        if (index < DatabaseFunctions.getNumberOfPlayers(dbHelper, gameId)) {

            SharedPreferences.Editor editor = sharedPref.edit();
            editor.putInt(this.stage1NumOfCardsString, 0);
            editor.putInt(this.indexString, index);
            editor.commit();

            Stage1_1Fragment stage1 = new Stage1_1Fragment();
            Bundle args = new Bundle();
//            args.putString("playerName", samplePlayers[index]);
            args.putString("playerName", DatabaseFunctions.getCurrentPlayerName(dbHelper, gameId));
            args.putInt("index", index);
            args.putInt("numOfCards", 0);
            stage1.setArguments(args);
            android.support.v4.app.FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
            ft.replace(R.id.gamePlayFragmentContainer, stage1);
            ft.commit();
        } else{
            SharedPreferences.Editor editor = sharedPref.edit();
            editor.putBoolean(this.isStage1, false);
            editor.putInt(this.stage1NumOfCardsString, -1);
            editor.putInt(this.indexString, -1);
            editor.commit();

            Stage2_1Fragment stage2 = new Stage2_1Fragment();
            Bundle args = new Bundle();
            //Switch between 4 or 5
            args.putInt("maxNumRows", 4);
            stage2.setArguments(args);
            android.support.v4.app.FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
            ft.replace(R.id.gamePlayFragmentContainer, stage2);
            ft.commit();
        }
    }
    boolean takeDrink = false;
    @Override
    public int doNextCard(int choice) {
        RideTheBusDbHelper dbHelper = new RideTheBusDbHelper(this);
        int card = DatabaseFunctions.getCurrentCard(dbHelper, gameId);
        takeDrink = DatabaseFunctions.isTakeDrink(dbHelper, gameId, choice);
        DatabaseFunctions.nextCard(dbHelper, gameId);

        SharedPreferences.Editor editor = sharedPref.edit();
        editor.putInt(this.stage1NumOfCardsString, 1 + sharedPref.getInt(this.stage1NumOfCardsString, 0));
        editor.commit();

        return card;
    }
    @Override
    public int getPlayerCard(int playerIndex, int cardIndex) {
        RideTheBusDbHelper dbHelper = new RideTheBusDbHelper(this);
        int card = DatabaseFunctions.getCardForPlayer(dbHelper, gameId, playerIndex, cardIndex);
        return card;
    }
    @Override
    public boolean shouldTakeDrink() {
        return takeDrink;
    }

    @Override
    public int flipStage2() {
        RideTheBusDbHelper dbHelper = new RideTheBusDbHelper(this);
        int cardVal = DatabaseFunctions.flipStage2(dbHelper, gameId);
        DatabaseFunctions.nextDiamondCard(dbHelper, gameId);

//        val sharedPref = applicationContext.getSharedPreferences(DatabaseFunctions.sharedPrefId, 0)
//        val editor = sharedPref.edit()
//
//        editor.putLong("gameId", newGameId)
//        editor.commit()
        SharedPreferences.Editor editor = sharedPref.edit();
        editor.putInt(this.numCardsFlipped, 1 + sharedPref.getInt(this.numCardsFlipped, 0));
        editor.commit();

        return cardVal;
    }

    @Override
    public int getCardAtPosition(int pos) {
        return DatabaseFunctions.getCardAtDiamondPos(new RideTheBusDbHelper(this), gameId, pos);
    }

    @Override
    public void finishGame() {
        DatabaseFunctions.deleteAllTables(new RideTheBusDbHelper(this));
        SharedPreferences.Editor editor = sharedPref.edit();
        editor.putLong("gameId", -1L);
        editor.putInt(this.numCardsFlipped, 0);
        editor.putBoolean(this.isStage1, true);
        editor.commit();
        this.finish();
    }

}
