package com.joshwing.ridethebus

import android.content.ContentValues
import android.content.Intent
import android.database.sqlite.SQLiteDatabase
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentActivity
import android.support.v4.app.FragmentTransaction
import android.util.Log
import android.widget.FrameLayout
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

    var newPlayerIndex: Int = 0
    var newPlayerCount: Int = 0
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

    fun nextFragment(fragment: Fragment) {
        val transaction = supportFragmentManager.beginTransaction()
        transaction.replace(R.id.gameSetupFragmentContainer, fragment)
        transaction.addToBackStack(null)
        transaction.commit()
    }

    override fun over21Data(isOver21: Boolean) {
        if (isOver21) {
            nextFragment(NumberOfPlayersFragment())
        }
    }

    override fun numberOfPlayersData(numberOfPlayers: Int) {
        newPlayerCount = numberOfPlayers
        playerDetails = Array<PlayerDetails>(numberOfPlayers, {i -> PlayerDetails(i, "", "", 0, 0)})
        newPlayerIndex = 0
        nextFragment(NewPlayerFragment())
    }

    override fun newPlayerData(name: String, drink: String, picId: Int, maxDrinks: Int) {
        if (newPlayerIndex + 1 < newPlayerCount) {
            playerDetails[newPlayerIndex].name = name
            playerDetails[newPlayerIndex].drink = drink
            playerDetails[newPlayerIndex].picId = picId
            playerDetails[newPlayerIndex].maxDrinks = maxDrinks

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

            val intent = Intent(this, GamePlayActivity::class.java)
            Log.d("gamesetup gameid", newGameId.toString())
            intent.putExtra("gameId", newGameId)
            startActivity(intent)
        }
    }
}
