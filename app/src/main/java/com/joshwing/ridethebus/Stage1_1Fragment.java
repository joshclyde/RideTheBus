package com.joshwing.ridethebus;


import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import classes.CardFunctions;
import classes.DatabaseFunctions;
import database.RideTheBusDbHelper;


/**
 * A simple {@link Fragment} subclass.
 */
public class Stage1_1Fragment extends Fragment implements View.OnClickListener {

    stageOneListener dataPasser;

    public Stage1_1Fragment() {
        // Required empty public constructor
    }

    LinearLayout redOrBlack, higherOrLower, betweenOutside, suit;
    ImageView card1, card2, card3, card4, card0;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v =  inflater.inflate(R.layout.fragment_stage1_1, container, false);
        String playerName = this.getArguments().getString("playerName");
        TextView playerNameView = v.findViewById(R.id.playerName);
        playerNameView.setText(playerName);

        // gets all cards
        card1 = v.findViewById(R.id.stage1Card1);
        card1.setVisibility(View.INVISIBLE);
        card2 = v.findViewById(R.id.stage1Card2);
        card3 = v.findViewById(R.id.stage1Card3);
        card4 = v.findViewById(R.id.stage1Card4);
        card0 = v.findViewById(R.id.stage1Card0);

        //Hides all buttons execept Red or Black
        redOrBlack = v.findViewById(R.id.redOrBlack);
        higherOrLower = v.findViewById(R.id.higherOrLower);
        higherOrLower.setVisibility(View.GONE);
        betweenOutside = v.findViewById(R.id.betweenOrOutside);
        betweenOutside.setVisibility(View.GONE);
        suit = v.findViewById(R.id.suit);
        suit.setVisibility(View.GONE);

        //Set listeners
        Button red = (Button) v.findViewById(R.id.Red);
        red.setOnClickListener(this);
        Button black = (Button) v.findViewById(R.id.Black);
        black.setOnClickListener(this);
        Button higher = (Button) v.findViewById(R.id.Higher);
        higher.setOnClickListener(this);
        Button lower = (Button) v.findViewById(R.id.Lower);
        lower.setOnClickListener(this);
        Button between = (Button) v.findViewById(R.id.Between);
        between.setOnClickListener(this);
        Button outside = (Button) v.findViewById(R.id.Outside);
        outside.setOnClickListener(this);
        Button diamonds = (Button) v.findViewById(R.id.Diamonds);
        diamonds.setOnClickListener(this);
        Button hearts = (Button) v.findViewById(R.id.Hearts);
        hearts.setOnClickListener(this);
        Button clubs = (Button) v.findViewById(R.id.Clubs);
        clubs.setOnClickListener(this);
        Button spades = (Button) v.findViewById(R.id.Spades);
        spades.setOnClickListener(this);


        return v;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        Log.d("Here 1", "hi");
        dataPasser = (stageOneListener) context;
        Log.d("Here 2", "hi");
    }

    private void drawCard(ImageView cardView, int image) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            cardView.setImageDrawable(getResources().getDrawable(image, this.getContext().getTheme()));
        } else {
            cardView.setImageDrawable(getResources().getDrawable(image));
        }
    }

    //Listeners for all the buttons
    //Missing: check to see if their answer was correct
    public void onClick(View v) {
        switch (v.getId()) {
            case  R.id.Red: {
                redOrBlack.setVisibility(View.GONE);
                card1.setVisibility(View.VISIBLE);
                int card = dataPasser.doNextCard(0);
                drawCard(card1, CardFunctions.getImage(card));
                card2.setVisibility(View.INVISIBLE);
                higherOrLower.setVisibility(View.VISIBLE);
                break;
            }

            case R.id.Black: {
                redOrBlack.setVisibility(View.GONE);
                card1.setVisibility(View.VISIBLE);
                int card = dataPasser.doNextCard(1);
                drawCard(card1, CardFunctions.getImage(card));
                card2.setVisibility(View.INVISIBLE);
                higherOrLower.setVisibility(View.VISIBLE);
                break;
            }

            case  R.id.Higher: {
                higherOrLower.setVisibility(View.GONE);
                card2.setVisibility(View.VISIBLE);
                int card = dataPasser.doNextCard(0);
                drawCard(card2, CardFunctions.getImage(card));
                card3.setVisibility(View.INVISIBLE);
                betweenOutside.setVisibility(View.VISIBLE);
                break;
            }

            case R.id.Lower: {
                higherOrLower.setVisibility(View.GONE);
                card2.setVisibility(View.VISIBLE);
                int card = dataPasser.doNextCard(1);
                drawCard(card2, CardFunctions.getImage(card));
                card3.setVisibility(View.INVISIBLE);
                betweenOutside.setVisibility(View.VISIBLE);
                break;
            }

            case  R.id.Between: {
                betweenOutside.setVisibility(View.GONE);
                card3.setVisibility(View.VISIBLE);
                int card = dataPasser.doNextCard(0);
                drawCard(card3, CardFunctions.getImage(card));
                card4.setVisibility(View.INVISIBLE);
                suit.setVisibility(View.VISIBLE);
                break;
            }

            case R.id.Outside: {
                betweenOutside.setVisibility(View.GONE);
                card3.setVisibility(View.VISIBLE);
                int card = dataPasser.doNextCard(1);
                drawCard(card3, CardFunctions.getImage(card));
                card4.setVisibility(View.INVISIBLE);
                suit.setVisibility(View.VISIBLE);
                break;
            }

            case  R.id.Diamonds: {
                int card = dataPasser.doNextCard(0);
                dataPasser.nextPlayer();
                break;
            }

            case R.id.Hearts: {
                try{
                    int card = dataPasser.doNextCard(1);
                    dataPasser.nextPlayer();
                }catch (ClassCastException cce){

                }
                break;
            }

            case  R.id.Clubs: {
                try{
                    int card = dataPasser.doNextCard(2);
                    dataPasser.nextPlayer();
                }catch (ClassCastException cce){

                }
                break;
            }

            case R.id.Spades: {
                try{
                    int card = dataPasser.doNextCard(3);
                    dataPasser.nextPlayer();
                }catch (ClassCastException cce){

                }
                break;
            }

            //.... etc
        }
    }

    //Interface method for next player; implemented in activity
    public interface stageOneListener{
        public void nextPlayer();
        public int doNextCard(int choice);
    }

}
