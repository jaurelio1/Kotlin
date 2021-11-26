package com.example.diceroller

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView

/**
 * This activity allows the user to roll a dice and view the result
 * on the screen.
 */
class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val rollButton: Button = findViewById(R.id.button)
        //vai ficar esperando os clicks no botão
        rollButton.setOnClickListener{ rollDice() }

    }

    fun rollDice(){
        val dice = Dice(6)
        val resultTextView: TextView = findViewById(R.id.textView2)
        resultTextView.text = dice.roll().toString()

    }
}