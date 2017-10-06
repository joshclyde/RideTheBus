package com.joshwing.ridethebus

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log

private val TAG = "MyActivity"

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        Log.v(TAG, "logging onCreate...")
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
