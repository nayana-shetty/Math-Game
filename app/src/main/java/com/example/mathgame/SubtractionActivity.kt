package com.example.mathgame

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.CountDownTimer
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import java.util.*
import kotlin.random.Random

class SubtractionActivity : AppCompatActivity() {
    lateinit var textScore: TextView
    lateinit var textLife: TextView
    lateinit var textTimer: TextView

    lateinit var textQuestion: TextView
    lateinit var editTextAnswer: EditText

    lateinit var buttonOk: Button
    lateinit var buttonNext: Button

    lateinit var timer: CountDownTimer
    private val startTimerInMillis: Long = 60000   //1-min
    var timeLeftInMillis: Long = startTimerInMillis

    var correctAnswer = 0
    var userScore = 0
    var userLife = 3
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_subtraction)
        supportActionBar!!.title = "subtraction"

        textScore = findViewById(R.id.textViewScore)
        textLife = findViewById(R.id.textViewLife)
        textTimer = findViewById(R.id.textViewTimer)
        textQuestion = findViewById(R.id.textViewQuestion)
        editTextAnswer = findViewById(R.id.editTextAnswer)
        buttonOk = findViewById(R.id.buttonOk)
        buttonNext = findViewById(R.id.buttonNext)

        //call gameContinue inside onCreate
        gameContinue()

        buttonOk.setOnClickListener {

            val input = editTextAnswer.text.toString()

            if (input == "") {
                Toast.makeText(
                    applicationContext, "Please Enter your answer and click Next button",
                    Toast.LENGTH_LONG
                ).show()
            } else{
                pauseTimer()
                val userAnswer = input.toInt()

                if (userAnswer == correctAnswer) {
                    userScore = userScore + 10
                    textQuestion.text = "Congratulation,your answer is Correct"
                    textScore.text = userScore.toString()
                } else {
                    userLife--
                    textQuestion.text = "Sorry,your answer is Wrong"
                    textLife.text = userLife.toString()
                }
            }


        }

        buttonNext.setOnClickListener {
            pauseTimer()
            resetTimer()

            editTextAnswer.setText("")

            if(userLife == 0){
                Toast.makeText(applicationContext, "Game Over!", Toast.LENGTH_LONG).show()
                var intent = Intent(this,ResultActivity::class.java)
                intent.putExtra("score",userScore)
                startActivity(intent)
                finish()
            }else{
                gameContinue()
            }

        }

    }

    fun gameContinue() {
        val number1: Int = Random.nextInt(0, 100)
        val number2: Int = Random.nextInt(0, 100)

        textQuestion.text = "$number1 - $number2"

        correctAnswer = number1 - number2

        startTimer()
    }

    fun startTimer() {
        timer = object : CountDownTimer(timeLeftInMillis, 1000){ // define object :
            override fun onTick(millisUntilFinished: Long) {
                timeLeftInMillis = millisUntilFinished
                updateText()
            }

            override fun onFinish() {
                pauseTimer()
                resetTimer()
                updateText()

                userLife--
                textLife.text = userLife.toString()
                textQuestion.text = "Sorry,Your time is Up!"
            }

        }.start()
    }

    fun  updateText(){
        val remainingTime : Int = (timeLeftInMillis/1000).toInt()
        textTimer.text = String.format(Locale.getDefault(),"%02d",remainingTime)
    }

    fun  pauseTimer(){
        timer.cancel()
    }

    fun resetTimer(){
        timeLeftInMillis = startTimerInMillis
        updateText()
    }


}
