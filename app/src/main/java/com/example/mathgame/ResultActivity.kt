package com.example.mathgame

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView

class ResultActivity : AppCompatActivity() {
    lateinit var playAgain : Button
    lateinit var exit : Button
    lateinit var result : TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_result)

        playAgain = findViewById(R.id.buttonAgain)
        exit = findViewById(R.id.buttonExit)
        result = findViewById(R.id.textViewResult)

        val score = intent.getIntExtra("score",0)
        result.text = "Your Score : "+ score

        playAgain.setOnClickListener {
            var intent = Intent(this,MainActivity::class.java)
            startActivity(intent)
            finish()
        }

        exit.setOnClickListener {
            var intent = Intent(Intent.ACTION_MAIN)
            intent.addCategory(Intent.CATEGORY_HOME)
            intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK
            startActivity(intent)
        }
    }
}