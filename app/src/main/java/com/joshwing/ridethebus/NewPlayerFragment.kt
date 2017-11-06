package com.joshwing.ridethebus


import android.content.Context
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.EditText
import android.widget.Spinner


class NewPlayerFragment : Fragment(), View.OnClickListener {

    lateinit var dataPasser: NewPlayerDataPass
    lateinit var playerName: EditText
    lateinit var maxDrinks: EditText
    lateinit var drinkType: Spinner

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

            playerName = v.findViewById<EditText>(R.id.newPlayerNameEditText)
            maxDrinks= v.findViewById<EditText>(R.id.newPlayerMaxDrinksEditText)
            drinkType= spinner

            val completeButton = v.findViewById<Button>(R.id.newPlayerCompleteButton)
            completeButton.setOnClickListener(this)
        }
        return v
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        dataPasser = context as NewPlayerDataPass
    }

    override fun onClick(v: View) {
        when (v.id) {
            R.id.newPlayerCompleteButton -> {
                val playerNameVal = playerName.text.toString()
                val drinkTypeVal = drinkType.selectedItem.toString()
                val picIdVal = 1
                val maxDrinksVal = 20 // TODO: maxDrinks.text.toString().toInt()
                dataPasser.newPlayerData(playerNameVal, drinkTypeVal, picIdVal, maxDrinksVal)
            }
        }
    }

    interface NewPlayerDataPass {
        fun newPlayerData(name: String, drink: String, picId: Int, maxDrinks: Int)
    }

}
