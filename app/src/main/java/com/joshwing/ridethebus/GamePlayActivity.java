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

    //Implement next Player method, simply replace the old one IF there is a next player
    //o.w. move on to stage 2
    @Override
    public void nextPlayer(){
        RideTheBusDbHelper dbHelper = new RideTheBusDbHelper(this);
        index++;
        if (index < DatabaseFunctions.getNumberOfPlayers(dbHelper, gameId)) {
            Stage1_1Fragment stage1 = new Stage1_1Fragment();
            Bundle args = new Bundle();
//            args.putString("playerName", samplePlayers[index]);
            args.putString("playerName", DatabaseFunctions.getCurrentPlayerName(dbHelper, gameId));
            args.putInt("index", index);
            stage1.setArguments(args);
            android.support.v4.app.FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
            ft.replace(R.id.gamePlayFragmentContainer, stage1);
            ft.commit();
        } else{
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

        return card;
    };
    @Override
    public boolean shouldTakeDrink() {
        return takeDrink;
    }

    @Override
    public int flipStage2() {
        RideTheBusDbHelper dbHelper = new RideTheBusDbHelper(this);
        int cardVal = DatabaseFunctions.flipStage2(dbHelper, gameId);
        DatabaseFunctions.nextDiamondCard(dbHelper, gameId);

        return cardVal;
    }

    @Override
    public void finishGame() {
        DatabaseFunctions.deleteAllTables(new RideTheBusDbHelper(this));
        this.finish();
    }

}
