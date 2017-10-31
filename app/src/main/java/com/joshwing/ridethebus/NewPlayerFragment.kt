package com.joshwing.ridethebus


import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.Spinner


class NewPlayerFragment : Fragment() {

    lateinit var dataPasser: NewPlayerFragment

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {

        val v = inflater!!.inflate(R.layout.fragment_new_player, container, false)
        if (v != null) {
            val spinner = v.findViewById<Spinner>(R.id.newPlayerDrinkSpinner)
            // Create an ArrayAdapter using the string array and a default spinner layout
            val adapter = ArrayAdapter.createFromResource(this.activity, R.array.drinkArray, android.R.layout.simple_spinner_item)
            // Specify the layout to use when the list of choices appears
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            // Apply the adapter to the spinner
            spinner.setAdapter(adapter)
        }
        return v
    }

    interface NewPlayerFragment {
        fun newPlayerData(name: String, drink: String, picId: Int, maxDrinks: Int)
    }

}
