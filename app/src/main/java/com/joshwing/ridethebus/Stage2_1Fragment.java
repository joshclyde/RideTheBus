package com.joshwing.ridethebus;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


/**
 * A simple {@link Fragment} subclass.
 */
public class Stage2_1Fragment extends Fragment {



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
        }



        return v;
    }

}
