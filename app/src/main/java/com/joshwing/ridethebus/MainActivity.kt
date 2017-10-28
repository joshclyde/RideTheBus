package com.joshwing.ridethebus

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.support.v4.content.ContextCompat.startActivity
import android.content.Intent
import android.widget.Button


private val TAG = "MainActivity"

class MainActivity : AppCompatActivity(), View.OnClickListener {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val startGameButton = findViewById<Button>(R.id.startGameButton)
        startGameButton.setOnClickListener(this)

        Log.v(TAG, "logging onCreate...")
    }

    override fun onClick(v: View) {
        when (v.getId()) {
            R.id.startGameButton -> {
                val intent = Intent(this, GameSetup::class.java)
                startActivity(intent)
            }
        }
    }

    override fun onStart() {
        super.onStart()
        Log.v(TAG, "logging onStart...")
    }

    override fun onRestart() {
        super.onRestart()
        Log.v(TAG, "logging onRestart...")
    }

    override fun onResume() {
        super.onResume()
        Log.v(TAG, "logging onResume...")
    }

    override fun onPause() {
        super.onPause()
        Log.v(TAG, "logging onPause...")
    }

    override fun onStop() {
        super.onStop()
        Log.v(TAG, "logging onStop...")
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.v(TAG, "logging onDestroy...")
    }
}
