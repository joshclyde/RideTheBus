package com.joshwing.ridethebus

import android.content.ContentValues
import android.content.Intent
import android.content.SharedPreferences
import android.database.sqlite.SQLiteDatabase
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentActivity
import android.support.v4.app.FragmentTransaction
import android.util.Log
import android.widget.FrameLayout
import classes.DatabaseFunctions
import classes.PlayerDetails
import database.RideTheBusDbHelper

import database.RideTheBusContract.GameTable
import database.RideTheBusContract.PlayerStateTable
import database.RideTheBusContract.PlayerDetailsTable
import database.RideTheBusContract.CardTable

class GameSetup : FragmentActivity(),
        Over21Fragment.Over21DataPass,
        NumberOfPlayersFragment.NumberOfPlayersDataPass,
        NewPlayerFragment.NewPlayerDataPass {

    var NEW_PLAYER_INDEX_STATE = "NEW_PLAYER_INDEX_STATE"
    var newPlayerIndex: Int = 0
    var NEW_PLAYER_INDEX_COUNT = "NEW_PLAYER_INDEX_COUNT"
    var newPlayerCount: Int = 0
//    name
//    playerDetails[newPlayerIndex].drink = drink
//    playerDetails[newPlayerIndex].picId = picId
//    playerDetails[newPlayerIndex].maxDrinks = maxDrinks
    var PD_NAME = "PD_NAME_"
    var PD_DRINK = "PD_DRINK_"
    var PD_PIC_ID = "PD_PIC_ID_"
    var PD_MAX_DRINKS = "PD_MAX_DRINKS_"
    lateinit var playerDetails: Array<PlayerDetails>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_game_setup)



        if (findViewById<FrameLayout>(R.id.gameSetupFragmentContainer) != null) {
            if (savedInstanceState != null) {
                return;
            }

            //val fragmentManager = getFragmentManager()
            //val fragmentTransaction = fragmentManager.beginTransaction()
            val fragment: Fragment? = Over21Fragment()

            getSupportFragmentManager().beginTransaction()
                    .add(R.id.gameSetupFragmentContainer, fragment).commit();
            //fragmentTransaction.add(R.id.gameSetupFragmentContainer, fragment)
            //fragmentTransaction.commit()
        }
    }

    override fun onSaveInstanceState(outState: Bundle?) {
        super.onSaveInstanceState(outState)
        outState?.putInt(NEW_PLAYER_INDEX_STATE, newPlayerIndex)
        outState?.putInt(NEW_PLAYER_INDEX_COUNT, newPlayerCount)
        var i = 0
        while (i < newPlayerIndex) {
            outState?.putString(PD_NAME + i, playerDetails[i].name)
            outState?.putString(PD_DRINK + i, playerDetails[i].drink)
            outState?.putInt(PD_PIC_ID + i, playerDetails[i].picId)
            outState?.putInt(PD_MAX_DRINKS + i, playerDetails[i].maxDrinks)
            i++
        }
    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle?) {
        super.onRestoreInstanceState(savedInstanceState)
        newPlayerIndex = savedInstanceState!!.getInt(NEW_PLAYER_INDEX_STATE)
        newPlayerCount = savedInstanceState!!.getInt(NEW_PLAYER_INDEX_COUNT)
        playerDetails = Array<PlayerDetails>(newPlayerCount, {i -> PlayerDetails(i, "", "", 0, 0)})
        var i = 0
        while (i < newPlayerIndex) {
            playerDetails[i].name = savedInstanceState!!.getString(PD_NAME + i)
            playerDetails[i].drink = savedInstanceState!!.getString(PD_DRINK + i)
            playerDetails[i].picId = savedInstanceState!!.getInt(PD_PIC_ID + i)
            playerDetails[i].maxDrinks = savedInstanceState!!.getInt(PD_MAX_DRINKS + i)
            i++
        }
    }

    fun nextFragment(fragment: Fragment) {
        val transaction = supportFragmentManager.beginTransaction()
        transaction.replace(R.id.gameSetupFragmentContainer, fragment)
        transaction.addToBackStack(null)
        transaction.commit()
    }

    override fun over21Data(isOver21: Boolean) {
        if (isOver21) {
            nextFragment(NumberOfPlayersFragment())
        } else {
            nextFragment(Under21Fragment());
        }
    }

    override fun numberOfPlayersData(numberOfPlayers: Int) {
        newPlayerCount = numberOfPlayers
        playerDetails = Array<PlayerDetails>(numberOfPlayers, {i -> PlayerDetails(i, "", "", 0, 0)})
        newPlayerIndex = 0
        nextFragment(NewPlayerFragment())
    }

    override fun newPlayerData(name: String, drink: String, picId: Int, maxDrinks: Int) {
        playerDetails[newPlayerIndex].name = name
        playerDetails[newPlayerIndex].drink = drink
        playerDetails[newPlayerIndex].picId = picId
        playerDetails[newPlayerIndex].maxDrinks = maxDrinks

        if (newPlayerIndex + 1 < newPlayerCount) {
            newPlayerIndex++
            nextFragment(NewPlayerFragment())
        } else {
            // create database
            val dbHelper = RideTheBusDbHelper(this)
            val db = dbHelper.writableDatabase


            // GAME_TABLE
            val gameValues = ContentValues()
            gameValues.put(GameTable.COLUMN_CARD_ID, -1)
            gameValues.put(GameTable.COLUMN_PLAYER_ID, -1)
            val newGameId = db.insert(GameTable.TABLE_NAME, null, gameValues)

            // PLAYER_DETAILS and PLAYER_STATE
            for (i in 0..(newPlayerCount-1)) {
                val playerDetailsValues = ContentValues()
                playerDetailsValues.put(PlayerDetailsTable.COLUMN_NAME, playerDetails[i].name)
                playerDetailsValues.put(PlayerDetailsTable.COLUMN_PIC, playerDetails[i].picId)
                playerDetailsValues.put(PlayerDetailsTable.COLUMN_MAX_DRINKS, playerDetails[i].maxDrinks)
                val playerDetailsId = db.insert(PlayerDetailsTable.TABLE_NAME, null, playerDetailsValues)

                val playerStateValues = ContentValues()
                playerStateValues .put(PlayerStateTable.COLUMN_GAME_ID, newGameId)
                playerStateValues .put(PlayerStateTable.COLUMN_PLAYER_ID, playerDetailsId)
                playerStateValues .put(PlayerStateTable.COLUMN_TURN, i)
                playerStateValues .put(PlayerStateTable.COLUMN_DRINKS_TAKEN, 0)
                playerStateValues .put(PlayerStateTable.COLUMN_MAX_DRINKS, playerDetails[i].maxDrinks)
                db.insert(PlayerStateTable.TABLE_NAME, null, playerStateValues)
            }

            // CARD
            for (i in 0..51) {
                val cardValues = ContentValues()
                cardValues.put(CardTable.COLUMN_VALUE, i)
                cardValues.put(CardTable.COLUMN_GAME_ID, newGameId)
                cardValues.putNull(CardTable.COLUMN_PLAYER_ID)
                cardValues.put(CardTable.COLUMN_DIAMOND_ORDER, -1)
                cardValues.put(CardTable.COLUMN_PLAYER_ORDER, -1)
                db.insert(CardTable.TABLE_NAME, null, cardValues)
            }


            val sharedPref = applicationContext.getSharedPreferences(DatabaseFunctions.sharedPrefId, 0)

            val editor = sharedPref.edit()
            editor.putInt("numOfCardsFlipped", 0)
            editor.putBoolean("isStage1", true)
            editor.putLong("gameId", newGameId)
            editor.putInt(GamePlayActivity.indexString, 0)
            editor.putInt(GamePlayActivity.stage1NumOfCardsString, -1)
            editor.commit()

            val intent = Intent(this, GamePlayActivity::class.java)
            intent.putExtra("gameId", newGameId)
            startActivity(intent);
            finish();
        }
    }
}
