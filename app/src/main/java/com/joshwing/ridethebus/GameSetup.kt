package com.joshwing.ridethebus

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentActivity
import android.widget.FrameLayout

class GameSetup : FragmentActivity() {

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
}
