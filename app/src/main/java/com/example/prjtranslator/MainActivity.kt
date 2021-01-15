package com.example.prjtranslator

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import kotlinx.android.synthetic.main.oefen.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val viewOefen = findViewById<View>(R.id.view_oefen)
        val viewSpeel= findViewById<View>(R.id.view_speel)

        viewOefen.setOnClickListener {
            val intent : Intent = Intent(this, CategoryActivity::class.java  )
            intent.putExtra("com.example.prjtranslator.info", "oefen");
            intent.putExtra("com.example.prjtranslator.mode", "oefen");
            startActivity(intent)
        }
        viewSpeel.setOnClickListener {
            val intent : Intent = Intent(this, CategoryActivity::class.java  )
            intent.putExtra("com.example.prjtranslator.info", "speel");
            intent.putExtra("com.example.prjtranslator.mode", "speel");
            startActivity(intent)
        }
    }
}