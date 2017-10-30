package com.joshwing.ridethebus

import android.content.Context
import android.net.Uri
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.NumberPicker


class NumberOfPlayersFragment : Fragment(), View.OnClickListener {

    lateinit var dataPasser: NumberOfPlayersDataPass
    lateinit var numberPicker: NumberPicker

    override fun onCreateView(inflater: LayoutInflater?,
                              container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {


        val v = inflater!!.inflate(R.layout.fragment_number_of_players, container, false)
        if (v != null) {
            numberPicker = v.findViewById<NumberPicker>(R.id.numberOfPlayersNumberPicker)
            numberPicker.minValue = 4
            numberPicker.maxValue = 8
            numberPicker.value = 6

            val confirmButton = v.findViewById<Button>(R.id.numberOfPlayersConfirmButton)
            confirmButton.setOnClickListener(this)
        }

        return v
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        dataPasser = context as NumberOfPlayersDataPass
    }

    override fun onClick(v: View) {
        when (v.getId()) {
            R.id.numberOfPlayersConfirmButton -> {
                dataPasser.numberOfPlayersData(numberPicker.value)
            }
        }
    }

    interface NumberOfPlayersDataPass {
        fun numberOfPlayersData(numberOfPlayers: Int)
    }
}
