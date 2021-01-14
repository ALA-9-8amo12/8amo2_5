package com.example.prjtranslator
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class ScoreActivity : AppCompatActivity() {

    private lateinit var cato:String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.score)
        val homeButton = findViewById<Button>(R.id.home)
        homeButton.setOnClickListener {
            val intent : Intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }
        cato = intent.getStringExtra("categoryName").toString()
        println("oi");
    }
}