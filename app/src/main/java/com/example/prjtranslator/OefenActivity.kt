package com.example.prjtranslator

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.annotation.GlideModule


@GlideModule
class OefenActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.selectcat)
        val homeButton = findViewById<Button>(R.id.home)
        homeButton.setOnClickListener {
            val intent: Intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }
        val viewDieren = findViewById<View>(R.id.dieren)
        val viewEten = findViewById<View>(R.id.eten)
        val viewFruit = findViewById<View>(R.id.fruit)
        val viewGroente = findViewById<View>(R.id.groente)
        val viewInsecten = findViewById<View>(R.id.insecten)
        val viewKleding = findViewById<View>(R.id.kleding)
        val viewKleuren = findViewById<View>(R.id.kleuren)
        val viewWeer = findViewById<View>(R.id.weer)
        viewDieren.setOnClickListener {
            val intent : Intent = Intent(this, OefenActivity::class.java  )
            startActivity(intent)
        }
        viewEten.setOnClickListener {
            val intent : Intent = Intent(this, SpeelActivity::class.java  )
            startActivity(intent)
        }
        viewFruit.setOnClickListener {
            val intent : Intent = Intent(this, ScoreActivity::class.java  )
            startActivity(intent)
        }
        viewGroente.setOnClickListener {
            val intent : Intent = Intent(this, ScoreActivity::class.java  )
            startActivity(intent)
        }
        viewGroente.setOnClickListener {
            val intent : Intent = Intent(this, ScoreActivity::class.java  )
            startActivity(intent)
        }
        viewInsecten.setOnClickListener {
            val intent : Intent = Intent(this, ScoreActivity::class.java  )
            startActivity(intent)
        }
        viewKleding.setOnClickListener {
            val intent : Intent = Intent(this, ScoreActivity::class.java  )
            startActivity(intent)
        }
        viewKleuren.setOnClickListener {
            val intent : Intent = Intent(this, ScoreActivity::class.java  )
            startActivity(intent)
        }
        viewWeer.setOnClickListener {
            val intent : Intent = Intent(this, ScoreActivity::class.java  )
            startActivity(intent)
        }
    }

//    fun loadData() {
//        // define db
//        val db = Firebase.firestore
//        val categoryref = db.collection("catogories").document("oefen")
//        var url = "";
//        var image: HashMap<String, *> = HashMap<String, String>()
//        categoryref.get().addOnSuccessListener { document ->
//            if (document != null) {
////                println("Success ${document.data}")
//                val result = document.data;
//                image = result?.get("images") as HashMap<String, *>
//                url = image.get("egel").toString()
//                val iv: ImageView = findViewById(R.id.imageQuestion)
//                Glide.with(this@OefenActivity).load(url).into(iv)
//            }
//        }.addOnFailureListener { exception ->
//            println("Error ${exception}")
//        }
//    }
}