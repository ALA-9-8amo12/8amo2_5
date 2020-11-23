package com.example.prjtranslator

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val viewOefen = findViewById<View>(R.id.view_oefen)
        val viewSpeel= findViewById<View>(R.id.view_speel)
        val viewScore= findViewById<View>(R.id.view_score)
        viewOefen.setOnClickListener {
            val intent : Intent = Intent(this, OefenActivity::class.java  )
            startActivity(intent)
        }
        viewSpeel.setOnClickListener {
            val intent : Intent = Intent(this, SpeelActivity::class.java  )
            startActivity(intent)
        }
        viewScore.setOnClickListener {
            val intent : Intent = Intent(this, ScoreActivity::class.java  )
            startActivity(intent)
        }

    }
}