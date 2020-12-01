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

        fun oefenOnClickListeners() {
            viewDieren.setOnClickListener {
                val intent : Intent = Intent(this, OefenActivity::class.java  )
                intent.putExtra("com.example.prjtranslator.info", "dieren");
                startActivity(intent)
            }
            viewEten.setOnClickListener {
                val intent : Intent = Intent(this, OefenActivity::class.java  )
                intent.putExtra("com.example.prjtranslator.info", "eten");
                startActivity(intent)
            }
            viewFruit.setOnClickListener {
                val intent : Intent = Intent(this, OefenActivity::class.java  )
                intent.putExtra("com.example.prjtranslator.info", "fruit");
                startActivity(intent)
            }
            viewGroente.setOnClickListener {
                val intent : Intent = Intent(this, OefenActivity::class.java  )
                intent.putExtra("com.example.prjtranslator.info", "groente");
                startActivity(intent)
            }
            viewInsecten.setOnClickListener {
                val intent : Intent = Intent(this, OefenActivity::class.java  )
                intent.putExtra("com.example.prjtranslator.info", "insecten");
                startActivity(intent)
            }
            viewKleding.setOnClickListener {
                val intent : Intent = Intent(this, OefenActivity::class.java  )
                intent.putExtra("com.example.prjtranslator.info", "kleding");
                startActivity(intent)
            }
            viewKleuren.setOnClickListener {
                val intent : Intent = Intent(this, OefenActivity::class.java  )
                intent.putExtra("com.example.prjtranslator.info", "kleuren");
                startActivity(intent)
            }
            viewWeer.setOnClickListener {
                val intent : Intent = Intent(this, OefenActivity::class.java  )
                intent.putExtra("com.example.prjtranslator.info", "weer");
                startActivity(intent)
            }

        }

        fun speelOnClickListeners() {
            viewDieren.setOnClickListener {
                val intent : Intent = Intent(this, SpeelActivity::class.java  )
                intent.putExtra("com.example.prjtranslator.info", "dieren");
                startActivity(intent)
            }
            viewEten.setOnClickListener {
                val intent : Intent = Intent(this, SpeelActivity::class.java  )
                intent.putExtra("com.example.prjtranslator.info", "eten");
                startActivity(intent)
            }
            viewFruit.setOnClickListener {
                val intent : Intent = Intent(this, SpeelActivity::class.java  )
                intent.putExtra("com.example.prjtranslator.info", "fruit");
                startActivity(intent)
            }
            viewGroente.setOnClickListener {
                val intent : Intent = Intent(this, SpeelActivity::class.java  )
                intent.putExtra("com.example.prjtranslator.info", "groente");
                startActivity(intent)
            }
            viewInsecten.setOnClickListener {
                val intent : Intent = Intent(this, SpeelActivity::class.java  )
                intent.putExtra("com.example.prjtranslator.info", "insecten");
                startActivity(intent)
            }
            viewKleding.setOnClickListener {
                val intent : Intent = Intent(this, SpeelActivity::class.java  )
                intent.putExtra("com.example.prjtranslator.info", "kleding");
                startActivity(intent)
            }
            viewKleuren.setOnClickListener {
                val intent : Intent = Intent(this, SpeelActivity::class.java  )
                intent.putExtra("com.example.prjtranslator.info", "kleuren");
                startActivity(intent)
            }
            viewWeer.setOnClickListener {
                val intent : Intent = Intent(this, SpeelActivity::class.java  )
                intent.putExtra("com.example.prjtranslator.info", "weer");
                startActivity(intent)
            }

        }

        fun scoreOnClickListeners() {
            viewDieren.setOnClickListener {
                val intent : Intent = Intent(this, ScoreActivity::class.java  )
                intent.putExtra("com.example.prjtranslator.info", "dieren");
                startActivity(intent)
            }
            viewEten.setOnClickListener {
                val intent : Intent = Intent(this, ScoreActivity::class.java  )
                intent.putExtra("com.example.prjtranslator.info", "eten");
                startActivity(intent)
            }
            viewFruit.setOnClickListener {
                val intent : Intent = Intent(this, ScoreActivity::class.java  )
                intent.putExtra("com.example.prjtranslator.info", "fruit");
                startActivity(intent)
            }
            viewGroente.setOnClickListener {
                val intent : Intent = Intent(this, ScoreActivity::class.java  )
                intent.putExtra("com.example.prjtranslator.info", "groente");
                startActivity(intent)
            }
            viewInsecten.setOnClickListener {
                val intent : Intent = Intent(this, ScoreActivity::class.java  )
                intent.putExtra("com.example.prjtranslator.info", "insecten");
                startActivity(intent)
            }
            viewKleding.setOnClickListener {
                val intent : Intent = Intent(this, ScoreActivity::class.java  )
                intent.putExtra("com.example.prjtranslator.info", "kleding");
                startActivity(intent)
            }
            viewKleuren.setOnClickListener {
                val intent : Intent = Intent(this, ScoreActivity::class.java  )
                intent.putExtra("com.example.prjtranslator.info", "kleuren");
                startActivity(intent)
            }
            viewWeer.setOnClickListener {
                val intent : Intent = Intent(this, ScoreActivity::class.java  )
                intent.putExtra("com.example.prjtranslator.info", "weer");
                startActivity(intent)
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