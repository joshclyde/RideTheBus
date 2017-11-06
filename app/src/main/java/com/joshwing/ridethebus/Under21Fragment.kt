package com.joshwing.ridethebus


import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button


/**
 * A simple [Fragment] subclass.
 */
class Under21Fragment : Fragment() {


    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        val v = inflater!!.inflate(R.layout.fragment_under21, container, false)
        if (v != null) {
            var button = v.findViewById<Button>(R.id.mainMenuButton)
            button.setOnClickListener(object: View.OnClickListener{
                override fun onClick(v: View): Unit{
                    activity.finish()
                }
            })

        }

        return v

    }



}// Required empty public constructor
