package com.joshwing.ridethebus

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.support.v4.content.ContextCompat.startActivity
import android.content.Intent
import android.content.SharedPreferences
import android.os.PersistableBundle
import android.widget.Button
import classes.DatabaseFunctions


private val TAG = "MainActivity"

class MainActivity : AppCompatActivity(), View.OnClickListener {

    val GAME_ID_STATE = "GAME_ID_STATE";
    var gameId = -1L

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val startGameButton = findViewById<Button>(R.id.startGameButton)
        startGameButton.setOnClickListener(this)

        val sharedPref = applicationContext.getSharedPreferences(DatabaseFunctions.sharedPrefId, 0)
        gameId = sharedPref.getLong("gameId", -1L)
        if (gameId != -1L) {
            val loadGameButton = findViewById<Button>(R.id.loadGameButton)
            loadGameButton.setOnClickListener(this)
            loadGameButton.visibility = View.VISIBLE
        } else {
            val loadGameButton = findViewById<Button>(R.id.loadGameButton)
            loadGameButton.visibility = View.GONE
        }

        Log.v(TAG, "logging onCreate...")
    }

    override fun onSaveInstanceState(outState: Bundle?) {
        super.onSaveInstanceState(outState)
        outState?.putLong(GAME_ID_STATE, gameId);
    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle?) {
        super.onRestoreInstanceState(savedInstanceState)
        gameId = savedInstanceState!!.getLong(GAME_ID_STATE);
    }

    override fun onClick(v: View) {
        when (v.getId()) {
            R.id.startGameButton -> {
                val intent = Intent(this, GameSetup::class.java)
                startActivity(intent)
            }
            R.id.loadGameButton -> {
                val intent = Intent(this, GamePlayActivity::class.java)
                intent.putExtra("gameId", gameId)
                startActivity(intent);
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


        val sharedPref = applicationContext.getSharedPreferences(DatabaseFunctions.sharedPrefId, 0)
        gameId = sharedPref.getLong("gameId", -1L)
        if (gameId != -1L) {
            val loadGameButton = findViewById<Button>(R.id.loadGameButton)
            loadGameButton.setOnClickListener(this)
            loadGameButton.visibility = View.VISIBLE
        } else {
            val loadGameButton = findViewById<Button>(R.id.loadGameButton)
            loadGameButton.visibility = View.GONE
        }
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
