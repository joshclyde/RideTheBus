package com.joshwing.ridethebus;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;


/**
 * A simple {@link Fragment} subclass.
 */
public class Stage1_1Fragment extends Fragment implements View.OnClickListener {


    public Stage1_1Fragment() {
        // Required empty public constructor
    }

    LinearLayout redOrBlack, higherOrLower, betweenOutside, suit;



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v =  inflater.inflate(R.layout.fragment_stage1_1, container, false);
        String playerName = this.getArguments().getString("playerName");
        TextView playerNameView = v.findViewById(R.id.playerName);
        playerNameView.setText(playerName);

        redOrBlack = v.findViewById(R.id.redOrBlack);
        higherOrLower = v.findViewById(R.id.higherOrLower);
        higherOrLower.setVisibility(View.GONE);
        betweenOutside = v.findViewById(R.id.betweenOrOutside);
        betweenOutside.setVisibility(View.GONE);
        suit = v.findViewById(R.id.suit);
        suit.setVisibility(View.GONE);

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


    public void onClick(View v) {
        switch (v.getId()) {
            case  R.id.Red: {
                redOrBlack.setVisibility(View.GONE);
                higherOrLower.setVisibility(View.VISIBLE);
                break;
            }

            case R.id.Black: {
                redOrBlack.setVisibility(View.GONE);
                higherOrLower.setVisibility(View.VISIBLE);
                break;
            }

            case  R.id.Higher: {
                higherOrLower.setVisibility(View.GONE);
                betweenOutside.setVisibility(View.VISIBLE);
                break;
            }

            case R.id.Lower: {
                higherOrLower.setVisibility(View.GONE);
                betweenOutside.setVisibility(View.VISIBLE);
                break;
            }

            case  R.id.Between: {
                betweenOutside.setVisibility(View.GONE);
                suit.setVisibility(View.VISIBLE);
                break;
            }

            case R.id.Outside: {
                betweenOutside.setVisibility(View.GONE);
                suit.setVisibility(View.VISIBLE);
                break;
            }

            case  R.id.Diamonds: {
                getActivity().finish();
                break;
            }

            case R.id.Hearts: {
                getActivity().finish();
                break;
            }

            case  R.id.Clubs: {
                getActivity().finish();
                break;
            }

            case R.id.Spades: {
                getActivity().finish();
                break;
            }

            //.... etc
        }
    }



}
