package com.joshwing.ridethebus


import android.content.Context
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.provider.MediaStore
import android.content.Intent
import android.graphics.Bitmap
import android.app.Activity.RESULT_OK
import android.widget.*


class NewPlayerFragment : Fragment(), View.OnClickListener {

    lateinit var dataPasser: NewPlayerDataPass
    lateinit var playerName: EditText
    lateinit var maxDrinks: EditText
    lateinit var drinkType: Spinner
    lateinit var mImageView: ImageView

    val REQUEST_IMAGE_CAPTURE = 1


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
            val takePictureButton = v.findViewById<Button>(R.id.takePicture)
            takePictureButton.setOnClickListener(this)
            mImageView = v.findViewById(R.id.playerPicture)
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

            R.id.takePicture -> {
                dispatchTakePictureIntent()
            }

        }
    }

    private fun dispatchTakePictureIntent() {
        val takePictureIntent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
        if (takePictureIntent.resolveActivity(activity.getPackageManager()) != null) {
            startActivityForResult(takePictureIntent, REQUEST_IMAGE_CAPTURE)
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if (requestCode == REQUEST_IMAGE_CAPTURE && resultCode == RESULT_OK) {
            val extras = data!!.extras
            val imageBitmap = extras!!.get("data") as Bitmap
            mImageView.setImageBitmap(imageBitmap)
        }
    }

    interface NewPlayerDataPass {
        fun newPlayerData(name: String, drink: String, picId: Int, maxDrinks: Int)
    }

}
