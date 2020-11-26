package com.example.prjtranslator

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.bumptech.glide.annotation.GlideModule

import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import org.json.JSONObject


@GlideModule
class OefenActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.oefen)
        val homeButton = findViewById<Button>(R.id.home)
        homeButton.setOnClickListener {
            val intent: Intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }
        loadData();
    }

    fun loadData() {
        // define db
        val db = Firebase.firestore
        val categoryref = db.collection("catogories").document("dieren")
        var url = "";
        var image: HashMap<String, *> = HashMap<String, String>()
        categoryref.get().addOnSuccessListener { document ->
            if (document != null) {
//                println("Success ${document.data}")
                val result = document.data;
                image = result?.get("images") as HashMap<String, *>
                url = image.get("egel").toString()
                val iv: ImageView = findViewById(R.id.imageQuestion)
                Glide.with(this@OefenActivity).load(url).into(iv)
            }
        }.addOnFailureListener { exception ->
            println("Error ${exception}")
        }
    }
}