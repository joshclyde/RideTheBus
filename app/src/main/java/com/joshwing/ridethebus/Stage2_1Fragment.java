package com.joshwing.ridethebus;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;


/**
 * A simple {@link Fragment} subclass.
 */
public class Stage2_1Fragment extends Fragment implements View.OnTouchListener {

    ImageView currentCard;
    ImageView[] pyramidOfCards;
    int totalNumCards;
    int cardIndex;

    public Stage2_1Fragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View v =  inflater.inflate(R.layout.fragment_stage2_1, container, false);
        int maxNumRows = this.getArguments().getInt("maxNumRows");

        if(maxNumRows != 5){
            v.findViewById(R.id.topDownRow5).setVisibility(View.GONE);
            v.findViewById(R.id.bottomUpRow4).setVisibility(View.GONE);
            totalNumCards = 16;
        } else {
            totalNumCards = 25;
        }
        v.setOnTouchListener(this);
        //Makes Java Objects from XML Card elements
        pyramidOfCards = indexAllCards(v, maxNumRows);
        currentCard = v.findViewById(R.id.r1c1);
        cardIndex = 0;

        return v;
    }


    @Override
    public boolean onTouch(View v, MotionEvent motionEvent){
        boolean pressed = false;
        switch (motionEvent.getAction()){
            case MotionEvent.ACTION_DOWN:
                pressed = true;
                break;

            case MotionEvent.ACTION_MOVE:
                //User is moving around on the screen
                break;

            case MotionEvent.ACTION_UP:
                pressed = false;
                break;
        }

        if(pressed){
            currentCard.setImageResource(R.drawable.clubs_2);
            if (cardIndex < totalNumCards){
                currentCard = pyramidOfCards[cardIndex];
                cardIndex++;
            }
            return true;
        }
        return false;
    }

    public ImageView[] indexAllCards(View v, int maxRows){
        ImageView[] pyramidOfCards = new ImageView[totalNumCards];
        pyramidOfCards[0] = v.findViewById(R.id.r1c1);

        pyramidOfCards[1] = v.findViewById(R.id.r2c1);
        pyramidOfCards[2] = v.findViewById(R.id.r2c2);

        pyramidOfCards[3] = v.findViewById(R.id.r3c1);
        pyramidOfCards[4] = v.findViewById(R.id.r3c2);
        pyramidOfCards[5] = v.findViewById(R.id.r3c3);

        pyramidOfCards[6] = v.findViewById(R.id.r4c1);
        pyramidOfCards[7] = v.findViewById(R.id.r4c2);
        pyramidOfCards[8] = v.findViewById(R.id.r4c3);
        pyramidOfCards[9] = v.findViewById(R.id.r4c4);

        if(maxRows == 5){
            pyramidOfCards[10] = v.findViewById(R.id.r5c1);
            pyramidOfCards[11] = v.findViewById(R.id.r5c2);
            pyramidOfCards[12] = v.findViewById(R.id.r5c3);
            pyramidOfCards[13] = v.findViewById(R.id.r5c4);
            pyramidOfCards[14] = v.findViewById(R.id.r5c5);


            pyramidOfCards[totalNumCards-10] = v.findViewById(R.id.r6c1);
            pyramidOfCards[totalNumCards-9] = v.findViewById(R.id.r6c2);
            pyramidOfCards[totalNumCards-8] = v.findViewById(R.id.r6c3);
            pyramidOfCards[totalNumCards-7] = v.findViewById(R.id.r6c4);
        }

        pyramidOfCards[totalNumCards-6] = v.findViewById(R.id.r7c1);
        pyramidOfCards[totalNumCards-5] = v.findViewById(R.id.r7c2);
        pyramidOfCards[totalNumCards-4] = v.findViewById(R.id.r7c3);

        pyramidOfCards[totalNumCards-3] = v.findViewById(R.id.r8c1);
        pyramidOfCards[totalNumCards-2] = v.findViewById(R.id.r8c2);

        pyramidOfCards[totalNumCards-1] = v.findViewById(R.id.r9c1);

        return pyramidOfCards;
    }



}
