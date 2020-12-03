package com.example.prjtranslator

import android.content.Intent
import android.media.AudioManager
import android.media.MediaPlayer
import android.os.Bundle
import android.provider.MediaStore
import android.widget.Button
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.bumptech.glide.annotation.GlideModule
import com.google.firebase.firestore.DocumentReference
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase


@GlideModule
class OefenActivity : AppCompatActivity() {
    private val db = Firebase.firestore
    private lateinit var categoryref : DocumentReference;
    private lateinit var categoryname : String;

    override fun onCreate(savedInstanceState: Bundle?) {
        // intent coming over from caterogyactivity
        categoryname = intent.getStringExtra("com.example.prjtranslator.info").toString();
        categoryref = db.collection("catogories").document(categoryname)
        println("catname " + categoryname)
        //
        super.onCreate(savedInstanceState)
        setContentView(R.layout.oefen)

        val homeButton = findViewById<Button>(R.id.home)
        homeButton.setOnClickListener {
            val intent: Intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }

        val playBtn = findViewById<Button>(R.id.playBtn);
        playBtn.setOnClickListener {

            val mediaPlayer: MediaPlayer? = MediaPlayer().apply {
                setDataSource("https://firebasestorage.googleapis.com/v0/b/project-translator-cac7a.appspot.com/o/Dieren%2Fdieren01_geit.mp3?alt=media&token=3b50f4f2-f82a-4cfa-a1a4-82210607e863")
                prepare()
                start()
            }
        }

        loadData();

    }

    fun loadData() {
        categoryref.get().addOnSuccessListener { document ->
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