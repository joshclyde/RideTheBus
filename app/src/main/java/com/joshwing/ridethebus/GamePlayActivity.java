package com.joshwing.ridethebus;

import android.app.Fragment;
import android.app.FragmentTransaction;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;

public class GamePlayActivity extends FragmentActivity {

    String[] samplePlayers = {"Wing Chung Chow", "Josh Clyde"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_play);

        if (findViewById(R.id.gamePlayFragmentContainer) != null) {

            for (int i = 0; i < samplePlayers.length; i++) {
                Stage1_1Fragment stage1 = new Stage1_1Fragment();
                Bundle args = new Bundle();
                args.putString("playerName", samplePlayers[i]);
                stage1.setArguments(args);
                getSupportFragmentManager().beginTransaction().
                        add(R.id.gamePlayFragmentContainer, stage1).commit();
            }



        }

    }
}
