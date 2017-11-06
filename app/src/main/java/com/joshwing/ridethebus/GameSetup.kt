package com.joshwing.ridethebus

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentActivity
import android.support.v4.app.FragmentTransaction
import android.widget.FrameLayout

class GameSetup : FragmentActivity(),
        Over21Fragment.Over21DataPass,
        NumberOfPlayersFragment.NumberOfPlayersDataPass {

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
        } else {
            nextFragment(Under21Fragment());
        }
    }

    override fun numberOfPlayersData(numberOfPlayers: Int) {
        nextFragment(NewPlayerFragment())
    }
}
