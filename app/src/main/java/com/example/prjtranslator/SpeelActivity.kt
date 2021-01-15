package com.example.prjtranslator

import android.content.Intent
import android.graphics.Color
import android.media.MediaPlayer
import android.os.Bundle
import android.os.Handler
import android.view.View
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.bumptech.glide.annotation.GlideModule
import com.google.firebase.database.*
import java.util.*


@GlideModule
class SpeelActivity : AppCompatActivity() {
    private val database = FirebaseDatabase.getInstance();
    private lateinit var myRef: DatabaseReference;
    private lateinit var answer: String;
    private lateinit var categoryname: String;
    private var questioncount: Int = 1;
    private var incorrectcount: Int = 0;
    private val data = arrayListOf<SpeelActivity.Questions>();
    private lateinit var categoryName: String
    private var correctCount = 0;

    private fun setCato(){
        categoryName = intent.getStringExtra("com.example.prjtranslator.info").toString().capitalize();
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.speel)

        setCato()
        myRef =
            database.getReference("1eLp2DK8iDagiTPtyGeDDLv_Qk4V7K5bL4anCPoxQmYY/" + categoryName);

        val homeButton = findViewById<Button>(R.id.home)
        homeButton.setOnClickListener {
            val intent: Intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }

        val reloadBtn = findViewById<Button>(R.id.reloadPageBtn);
        reloadBtn.setOnClickListener {
            addData();
        }

        val answer1 = findViewById<Button>(R.id.answer1);
        answer1.setOnClickListener {
            val value = answer1.text
            checkAnswer(answer1, value.toString())
        }
        val answer2 = findViewById<Button>(R.id.answer2);
        answer2.setOnClickListener {
            val value = answer2.text
            checkAnswer(answer2, value.toString())
        }
        val answer3 = findViewById<Button>(R.id.answer3);
        answer3.setOnClickListener {
            val value = answer3.text
            checkAnswer(answer3, value.toString())
        }
        val answer4 = findViewById<Button>(R.id.answer4);
        answer4.setOnClickListener {
            val value = answer4.text
            checkAnswer(answer4, value.toString())
        }
        val answer5 = findViewById<Button>(R.id.answer5);
        answer5.setOnClickListener {
            val value = answer5.text
            checkAnswer(answer5, value.toString())
        }
        val answer6 = findViewById<Button>(R.id.answer6);
        answer6.setOnClickListener {
            val value = answer6.text
            checkAnswer(answer6, value.toString())
        }

        getData();
    }

    private fun checkAnswer(button: Button, value: String) {
        if (value == answer) {
            correctCount++
            addData();
            incorrectcount = 0;

        } else {
            if(incorrectcount > 2) {
                addData();
            }
            button.setTextColor(Color.RED);
            incorrectcount++
            Handler().postDelayed({
                button.setTextColor(Color.BLACK)
            }, 1000)
        }
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
        val randomNumber = Random().nextInt(data.count()) // random item from db
        val randomType = Random().nextInt(2) // text, or image
        answer = data.get(randomNumber).vertaling;
        if (questioncount == 9) {
            val intent: Intent = Intent(this, ScoreActivity::class.java)
            if(this::categoryName.isInitialized ) {
                intent.putExtra("categoryName", categoryName)
                intent.putExtra("rights", correctCount)
            }else {
                intent.putExtra("categoryName", "it failed")
            }
            startActivity(intent)
        }
        // all buttons
        val answer1: Button = findViewById(R.id.answer1)
        val answer2: Button = findViewById(R.id.answer2)
        val answer3: Button = findViewById(R.id.answer3)
        val answer4: Button = findViewById(R.id.answer4)
        val answer5: Button = findViewById(R.id.answer5)
        val answer6: Button = findViewById(R.id.answer6)

        val AllAnswers: ArrayList<Button> =
            arrayListOf<Button>(answer1, answer2, answer3, answer4, answer5, answer6)

        var button_id = 0;

        for (question in data) {
            if (question.vertaling != answer && button_id < 6) {
                AllAnswers[button_id].setText(question.vertaling);
            }
            button_id++
        }
        val randomButtonNumb = Random().nextInt(6)
        AllAnswers[randomButtonNumb].setText(answer);


        if (randomType == 0) {
            val item: Button = findViewById(R.id.playBtn)
            item.isEnabled = false
            val iv: ImageView = findViewById(R.id.imageQuestion)
            val textnaam = findViewById<TextView>(R.id.imgText);
            textnaam.setText("Wat zie je?");
            Glide.with(this@SpeelActivity).load(data.get(randomNumber).imgUrl.toString()).into(iv)
            questioncount++;
        }
        if (randomType == 1) {
            val item: Button = findViewById(R.id.playBtn)
            item.isEnabled = true
            val iv: ImageView = findViewById(R.id.imageQuestion)
            iv.setImageResource(0)
            val textnaam = findViewById<TextView>(R.id.imgText);
            textnaam.setText(data.get(randomNumber).naam);
            questioncount++;
        }

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
        val naam: String = "",
        val vertaling: String = "",
        val imgUrl: String = "",
        val mp3Url: String = ""
    )
}