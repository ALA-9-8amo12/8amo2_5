package com.example.prjtranslator

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity

class CategoryActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.selectcat)
        // home button / listener
        val homeButton = findViewById<Button>(R.id.home)

        homeButton.setOnClickListener {
            val intent: Intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }

        // dieren listeners

        val viewDieren = findViewById<View>(R.id.dieren)
        val viewEten = findViewById<View>(R.id.eten)
        val viewFruit = findViewById<View>(R.id.fruit)
        val viewGroente = findViewById<View>(R.id.groente)
        val viewInsecten = findViewById<View>(R.id.insecten)
        val viewKleding = findViewById<View>(R.id.kleding)
        val viewKleuren = findViewById<View>(R.id.kleuren)
        val viewWeer = findViewById<View>(R.id.weer)

        val categoryname : String = intent.getStringExtra("com.example.prjtranslator.info").toString();
        val AllViews : ArrayList<View> = arrayListOf<View>(viewDieren, viewEten, viewFruit, viewGroente, viewInsecten, viewKleding, viewKleuren, viewWeer)

        fun oefenOnClickListeners() {
           for( view in AllViews){
               val viewIdName: String = resources.getResourceEntryName(view.id)
               view.setOnClickListener {
                   val intent: Intent = Intent(this, OefenActivity::class.java)
                   intent.putExtra("com.example.prjtranslator.info", viewIdName)
                   startActivity(intent)
               }
           }
        }

        fun speelOnClickListeners() {
            for( view in AllViews){
                val viewIdName: String = resources.getResourceEntryName(view.id)
                view.setOnClickListener {
                    val intent: Intent = Intent(this, SpeelActivity::class.java)
                    intent.putExtra("com.example.prjtranslator.info", viewIdName)
                    startActivity(intent)
                }
            }
        }

        fun scoreOnClickListeners() {
            for( view in AllViews){
                val viewIdName: String = resources.getResourceEntryName(view.id)
                view.setOnClickListener {
                    val intent: Intent = Intent(this, ScoreActivity::class.java)
                    intent.putExtra("com.example.prjtranslator.info", viewIdName)
                    startActivity(intent)
                }
            }
        }

        // decide what activity
        when (categoryname) {
            "oefen" -> oefenOnClickListeners();
            "speel" -> speelOnClickListeners();
            "score" -> scoreOnClickListeners();
            else -> {
                print("Incorrect intent. no function was called.")
            }
        }
    }
}