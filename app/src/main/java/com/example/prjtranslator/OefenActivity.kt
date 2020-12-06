package com.example.prjtranslator

import android.content.Intent
import android.media.MediaPlayer
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.annotation.GlideModule
import com.google.firebase.database.*
import java.util.*
import kotlin.collections.ArrayList


@GlideModule
class OefenActivity : AppCompatActivity() {
    private val database = FirebaseDatabase.getInstance();
    private lateinit var myRef : DatabaseReference;
    private lateinit var categoryname : String;


    override fun onCreate(savedInstanceState: Bundle?) {
        // intent coming over from caterogyactivity

        categoryname = intent.getStringExtra("com.example.prjtranslator.info").toString().capitalize();
        myRef = database.getReference("1eLp2DK8iDagiTPtyGeDDLv_Qk4V7K5bL4anCPoxQmYY/" + categoryname);

        @Suppress("ConvertToStringTemplate")
        println("catname " + categoryname);

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
        getData();
    }

    private fun getData() {
        database.goOnline()
        myRef.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
//                var data = HashMap<String, String>();
                var data = arrayListOf<Questions>();
                val values = dataSnapshot.children
//                var i = 0;
                for (ds in values) {

                    val dier = ds.getValue(Questions::class.java);
                    data.add(dier!!);

//                    var naam = ds.child("naam").value.toString()
//                    var vertaling = ds.child("vertaling").value.toString()
//                    var img = ds.child("imgUrl").value.toString()
//                    var mp3 = ds.child("mp3Url").value.toString()
//                    data["naam-" + i] = naam;
//                    data["vertaling-" + i] = vertaling;
//                    data["img-" + i] = img;
//                    data["mp3-" + i] = mp3;
//                    i++;
                }
                val randomNumber = Random().nextInt(data.count())
                println("LolData: " + data);
                println("LolData: " + randomNumber);


            }

            override fun onCancelled(error: DatabaseError) {
                // Failed to read value
                println("Failed to read value " + error.toException())
            }
        })
    }

    data class Questions(
        val naam:String = "",
        val vertaling:String = "",
        val imgUrl:String = "",
        val mp3Url:String = ""
    )
}
