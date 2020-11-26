package com.example.prjtranslator

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.bumptech.glide.annotation.GlideModule
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase


@GlideModule
class OefenActivity : AppCompatActivity() {
    private val db = Firebase.firestore
    private val categoryRef = db.collection("catogories").document("dieren")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.oefen)
        val homeButton = findViewById<Button>(R.id.home)
        homeButton.setOnClickListener {
            val intent: Intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }
        //call function to get data from firebase
        loadData();
    }

    fun loadData() {
        categoryRef.get().addOnSuccessListener { document ->
            if (document != null) {
                val image = document.data?.get("images") as HashMap<String, *>
                // invoke function that updates the ui with the image hashmap
                updateUi(image)
            }
        }.addOnFailureListener { exception ->
            println("Error ${exception}")
        }
    }

    fun updateUi(image: HashMap<String, *>){
        var url = image.get("egel").toString()
        val iv: ImageView = findViewById(R.id.imageQuestion)
        Glide.with(this@OefenActivity).load(url).into(iv)
    }
}