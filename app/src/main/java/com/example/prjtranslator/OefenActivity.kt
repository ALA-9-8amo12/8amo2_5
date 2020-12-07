package com.example.prjtranslator

import android.content.Intent
import android.media.MediaPlayer
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.bumptech.glide.annotation.GlideModule
import com.google.firebase.database.*
import java.util.*
import kotlin.collections.ArrayList


@GlideModule
class OefenActivity : AppCompatActivity() {
    private val database = FirebaseDatabase.getInstance();
    private lateinit var myRef : DatabaseReference;
    private lateinit var categoryname : String;
    private val data = arrayListOf<Questions>();

    override fun onCreate(savedInstanceState: Bundle?) {

        categoryname = intent.getStringExtra("com.example.prjtranslator.info").toString().capitalize();
        myRef = database.getReference("1eLp2DK8iDagiTPtyGeDDLv_Qk4V7K5bL4anCPoxQmYY/" + categoryname);

        @Suppress("ConvertToStringTemplate")
//        println("catname " + categoryname);

        super.onCreate(savedInstanceState)
        setContentView(R.layout.oefen)

        val homeButton = findViewById<Button>(R.id.home)
        homeButton.setOnClickListener {
            val intent: Intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }

        val reloadBtn = findViewById<Button>(R.id.reloadPageBtn);
        reloadBtn.setOnClickListener {
            addData();
        }
        getData();
    }

    private fun getData() {
        database.goOnline()
        myRef.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                val values = dataSnapshot.children
                for (ds in values) {
                    val categoryItems = ds.getValue(Questions::class.java);
                    data.add(categoryItems!!);
                }
                addData();
            }

            override fun onCancelled(error: DatabaseError) {
                println("Failed to read value " + error.toException())
            }
        })
    }

    private fun addData() {
        val randomNumber = Random().nextInt(data.count())

        val textnaam = findViewById<TextView>(R.id.imgText);
        textnaam.setText(data.get(randomNumber).naam);

        val textvertaling = findViewById<TextView>(R.id.vertalingText);
        textvertaling.setText(data.get(randomNumber).vertaling);

        val iv: ImageView = findViewById(R.id.imageQuestion)
        Glide.with(this@OefenActivity).load(data.get(randomNumber).imgUrl.toString()).into(iv)

        val playBtn = findViewById<Button>(R.id.playBtn);
        playBtn.setOnClickListener {
            val mediaPlayer: MediaPlayer? = MediaPlayer().apply {
                setDataSource(data.get(randomNumber).mp3Url)
                prepare()
                start()
            }
        }
    }

    data class Questions(
        val naam:String = "",
        val vertaling:String = "",
        val imgUrl:String = "",
        val mp3Url:String = ""
    )
}
