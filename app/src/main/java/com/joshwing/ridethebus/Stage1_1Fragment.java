package com.joshwing.ridethebus;


import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

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
    TextView takeDrinkView, noDrinkView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v =  inflater.inflate(R.layout.fragment_stage1_1, container, false);
        String playerName = this.getArguments().getString("playerName");
        int playerIndex = this.getArguments().getInt(GamePlayActivity.indexString);
        int cardIndex = this.getArguments().getInt(GamePlayActivity.stage1NumOfCardsString);
        TextView playerNameView = v.findViewById(R.id.playerName);
        playerNameView.setText(playerName);

        // gets all cards
        card1 = v.findViewById(R.id.stage1Card1);
        card1.setVisibility(View.INVISIBLE);
        card2 = v.findViewById(R.id.stage1Card2);
        card3 = v.findViewById(R.id.stage1Card3);
        card4 = v.findViewById(R.id.stage1Card4);
        card0 = v.findViewById(R.id.stage1Card0);

       // takeDrinkView = v.findViewById(R.id.stage1TakeDrink);
      //  takeDrinkView.setVisibility(View.GONE);
      //  noDrinkView = v.findViewById(R.id.stage1NotDrink);
      //  noDrinkView.setVisibility(View.GONE);

        //Hides all buttons execept Red or Black
        redOrBlack = v.findViewById(R.id.redOrBlack);
        higherOrLower = v.findViewById(R.id.higherOrLower);
        higherOrLower.setVisibility(View.GONE);
        betweenOutside = v.findViewById(R.id.betweenOrOutside);
        betweenOutside.setVisibility(View.GONE);
        suit = v.findViewById(R.id.suit);
        suit.setVisibility(View.GONE);

        //Set listeners
        Button endGame = (Button) v.findViewById(R.id.stage1EndGame);
        endGame.setOnClickListener(this);
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

        if (cardIndex >= 1) {
            int tempCard = dataPasser.getPlayerCard(playerIndex, 0);
            redOrBlack.setVisibility(View.GONE);
            cardTransition(card1, card2, card0, tempCard);
            higherOrLower.setVisibility(View.VISIBLE);
        }

        if (cardIndex >= 2) {
            int tempCard = dataPasser.getPlayerCard(playerIndex, 1);
            higherOrLower.setVisibility(View.GONE);
            cardTransition(card2, card3, card0, tempCard);
            betweenOutside.setVisibility(View.VISIBLE);
        }

        if (cardIndex >= 3) {
            int tempCard = dataPasser.getPlayerCard(playerIndex, 2);
            betweenOutside.setVisibility(View.GONE);
            cardTransition(card3, card4, card0, tempCard);
            suit.setVisibility(View.VISIBLE);
        }




        return v;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        dataPasser = (stageOneListener) context;
    }

    private void drawCard(ImageView cardView, int image) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            cardView.setImageDrawable(getResources().getDrawable(image, this.getContext().getTheme()));
        } else {
            cardView.setImageDrawable(getResources().getDrawable(image));
        }
    }

    int timeToWait = 1000;

    //Listeners for all the buttons
    //Missing: check to see if their answer was correct
    public void onClick(View v) {
        switch (v.getId()) {

            case  R.id.stage1EndGame:{
                getActivity().finish();
                break;
            }

            case  R.id.Red: {
                final int card = dataPasser.doNextCard(0);
                redOrBlack.setVisibility(View.GONE);
                drawCard(card0, CardFunctions.getImage(card));
                doToast(dataPasser.shouldTakeDrink());
                Handler handler = new Handler();
                handler.postDelayed(new Runnable() {
                    public void run() {
                        cardTransition(card1, card2, card0, card);
                        higherOrLower.setVisibility(View.VISIBLE);
                    }
                }, timeToWait);

                break;
            }

            case R.id.Black: {
                final int card = dataPasser.doNextCard(1);
                redOrBlack.setVisibility(View.GONE);
                drawCard(card0, CardFunctions.getImage(card));
                doToast(dataPasser.shouldTakeDrink());
                Handler handler = new Handler();
                handler.postDelayed(new Runnable() {
                    public void run() {
                        cardTransition(card1, card2, card0, card);
                        higherOrLower.setVisibility(View.VISIBLE);
                    }
                }, timeToWait);

                break;
            }

            case  R.id.Higher: {
                final int card = dataPasser.doNextCard(0);
                higherOrLower.setVisibility(View.GONE);
                drawCard(card0, CardFunctions.getImage(card));
                doToast(dataPasser.shouldTakeDrink());
                Handler handler = new Handler();
                handler.postDelayed(new Runnable() {
                    public void run() {
                        cardTransition(card2, card3, card0, card);
                        betweenOutside.setVisibility(View.VISIBLE);
                    }
                }, timeToWait);

                break;
            }

            case R.id.Lower: {
                final int card = dataPasser.doNextCard(1);
                higherOrLower.setVisibility(View.GONE);
                drawCard(card0, CardFunctions.getImage(card));
                doToast(dataPasser.shouldTakeDrink());
                Handler handler = new Handler();
                handler.postDelayed(new Runnable() {
                    public void run() {
                        cardTransition(card2, card3, card0, card);
                        betweenOutside.setVisibility(View.VISIBLE);
                    }
                }, timeToWait);
                break;
            }

            case  R.id.Between: {
                final int card = dataPasser.doNextCard(0);
                betweenOutside.setVisibility(View.GONE);
                drawCard(card0, CardFunctions.getImage(card));
                doToast(dataPasser.shouldTakeDrink());
                Handler handler = new Handler();
                handler.postDelayed(new Runnable() {
                    public void run() {
                        cardTransition(card3, card4, card0, card);
                        suit.setVisibility(View.VISIBLE);
                    }
                }, timeToWait);
                break;
            }

            case R.id.Outside: {
                final int card = dataPasser.doNextCard(1);
                betweenOutside.setVisibility(View.GONE);
                drawCard(card0, CardFunctions.getImage(card));
                doToast(dataPasser.shouldTakeDrink());
                Handler handler = new Handler();
                handler.postDelayed(new Runnable() {
                    public void run() {
                        cardTransition(card3, card4, card0, card);
                        suit.setVisibility(View.VISIBLE);
                    }
                }, timeToWait);
                break;
            }

            case  R.id.Diamonds: {
                final int card = dataPasser.doNextCard(0);
                suit.setVisibility(View.GONE);
                drawCard(card0, CardFunctions.getImage(card));
                doToast(dataPasser.shouldTakeDrink());
                Handler handler = new Handler();
                handler.postDelayed(new Runnable() {
                    public void run() {
                        dataPasser.nextPlayer();
                    }
                }, timeToWait);
                break;
            }

            case R.id.Hearts: {
                final int card = dataPasser.doNextCard(1);
                suit.setVisibility(View.GONE);
                drawCard(card0, CardFunctions.getImage(card));
                doToast(dataPasser.shouldTakeDrink());
                Handler handler = new Handler();
                handler.postDelayed(new Runnable() {
                    public void run() {
                        dataPasser.nextPlayer();
                    }
                }, timeToWait);
                break;
            }

            case  R.id.Clubs: {
                final int card = dataPasser.doNextCard(2);
                suit.setVisibility(View.GONE);
                drawCard(card0, CardFunctions.getImage(card));
                doToast(dataPasser.shouldTakeDrink());
                Handler handler = new Handler();
                handler.postDelayed(new Runnable() {
                    public void run() {
                        dataPasser.nextPlayer();
                    }
                }, timeToWait);
                break;
            }

            case R.id.Spades: {
                final int card = dataPasser.doNextCard(3);
                suit.setVisibility(View.GONE);
                drawCard(card0, CardFunctions.getImage(card));
                doToast(dataPasser.shouldTakeDrink());
                Handler handler = new Handler();
                handler.postDelayed(new Runnable() {
                    public void run() {
                        dataPasser.nextPlayer();
                    }
                }, timeToWait);
                break;
            }

            //.... etc
        }
    }

    public void cardTransition(ImageView cardAdd, ImageView cardRemove, ImageView cardBase, int cardVal) {
        cardAdd.setVisibility(View.VISIBLE);
        drawCard(cardAdd, CardFunctions.getImage(cardVal));
        drawCard(cardBase, R.drawable.back);
        cardRemove.setVisibility(View.INVISIBLE);
    }

    public void doToast(boolean shouldDrink) {
//        if (shouldDrink) {
//            Context context = this.getContext().getApplicationContext();
//            CharSequence text = "Drink!";
//            int duration = Toast.LENGTH_SHORT;
//
//            Toast toast = Toast.makeText(context, text, duration);
//            toast.show();
//        } else {
//            Context context = this.getContext().getApplicationContext();
//            CharSequence text = "No drink.";
//            int duration = Toast.LENGTH_SHORT;
//
//            Toast toast = Toast.makeText(context, text, duration);
//            toast.show();
//        }
    }

    //Interface method for next player; implemented in activity
    public interface stageOneListener{
        public void nextPlayer();
        public int doNextCard(int choice);
        public boolean shouldTakeDrink();
        public int getPlayerCard(int playerIndex, int cardIndex);
    }

}
