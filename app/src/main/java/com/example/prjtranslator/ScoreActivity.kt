package com.example.prjtranslator
import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import com.google.gson.Gson
import com.google.gson.annotations.SerializedName
import java.io.BufferedReader
import java.io.File

class ScoreActivity : AppCompatActivity() {
    private lateinit var cato:String
    private lateinit var categoryName: String
    private var StateScoresFile = "nothing yet"
    private lateinit var fileName: String
    private lateinit var file:File
    private var gson = Gson()
    private lateinit var scoreCount: Number
    private lateinit var highscore:Number

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.score)
        val homeButton = findViewById<Button>(R.id.home)

        homeButton.setOnClickListener {
            val intent : Intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }

        cato = intent.getStringExtra("categoryName").toString()
        categoryName = cato
        var thisgay = intent.getIntExtra("rights", 0)
        scoreCount = thisgay
        // set file name and path based on the category
        when(cato){
            "Dieren"-> cato = this.filesDir.path+"/DierenScores.json"
            "Eten" -> cato = this.filesDir.path+"/EtenScores.json"
            "Fruit" -> cato =  this.filesDir.path+"/FruitScores.json"
            "Groente"-> cato =  this.filesDir.path+"/GroenteScores.json"
            "Insecten" -> cato =  this.filesDir.path+"/InsectenScores.json"
            "Kleding" -> cato =   this.filesDir.path+"/KledingScores.json"
            "Weer"-> cato = this.filesDir.path+"/WeerScores.json"
            else -> cato = "error"
        }
        fileName = cato
        // create file based upon the path and name
        file = File(fileName)
        //check if the file already is stored on the local device
        checkFileExistence()
        //sets the highscore if the currenscore is higher
        checkHighScore()
        //get the scores from the file and set the data
        if (getScoresJson(file) != null){
            val h: Int = getScoresJson(File(fileName)).HighScore
            writeData(file, h , scoreCount.toInt() )
            val textView: TextView = findViewById(R.id.ScoreText)
            textView.text = "${categoryName}\n Your score ${getScoresJson(file).CurrentScore} out of 10 \n The Highscore ${getScoresJson(file).HighScore} out of 10"
        }
    }


    private fun checkHighScore(){
        if( getScoresJson(file).HighScore  < scoreCount.toInt() ){
            writeData(file, scoreCount.toInt() , scoreCount.toInt() )
        }
    }

    private fun checkFileExistence() {
        if (file == null) {
            File(fileName).createNewFile()
            file = File(fileName)
            writeData(file, 0, 0)
        }
        //if the file already exists
        if (!file.createNewFile()) {
            if (getScoresJson(file) == null) {
                writeData(file, 0, 0)
                StateScoresFile = "json is null"
            }else{
                StateScoresFile = "already exists"
            }
        }
        println("oi blyat ${StateScoresFile}")
    }

    //get scores from file function
    private fun getScoresJson(f: File):Score{
        val bufferReader: BufferedReader = f.bufferedReader()
        val input:String = bufferReader.use { it.readText() }
        var end = Score(0,0)

        if (gson.fromJson(input, Score::class.java) == null){
            writeData(file , 0 , 0)
        }else{
           end = gson.fromJson(input, Score::class.java)
        }
        return end
    }
    // writes data into the json file
    private fun writeData(file: File, h: Int , n: Int){
        val score = Score(h , n)
        val scoreJson:String  = gson.toJson(score)
        file.writeText(scoreJson)
    }
}

data class Score (
    @SerializedName("HighScore") var HighScore: Int = 0,
    @SerializedName("CurrentScore") var CurrentScore: Int = 0
)