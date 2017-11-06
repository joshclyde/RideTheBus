package com.joshwing.ridethebus

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentActivity
import android.support.v4.app.FragmentTransaction
import android.widget.FrameLayout
import classes.PlayerDetails

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


            val intent = Intent(this, GamePlayActivity::class.java)
            startActivity(intent)
        }
    }
}
