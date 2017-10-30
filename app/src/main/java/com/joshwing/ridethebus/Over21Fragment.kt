package com.joshwing.ridethebus

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button

class Over21Fragment : Fragment(), View.OnClickListener {

    lateinit var dataPasser: Over21DataPass

    override fun onCreateView(inflater: LayoutInflater?,
                              container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val v = inflater!!.inflate(R.layout.fragment_over21, container, false)
        if (v != null) {
            val yesButton = v.findViewById<Button>(R.id.over21YesButton)
            yesButton.setOnClickListener(this)
            val noButton = v.findViewById<Button>(R.id.over21NoButton)
            noButton.setOnClickListener(this)
        }

        return v
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        dataPasser = context as Over21DataPass
    }

    override fun onClick(v: View) {
        when (v.getId()) {
            R.id.over21YesButton -> dataPasser.over21Data(true)
            R.id.over21NoButton -> dataPasser.over21Data(false)
        }
    }

    interface Over21DataPass {
        fun over21Data(isOver21: Boolean)
    }
}
