package com.example.helloworld

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
//Android Activities don't use constructors
class MainActivity : AppCompatActivity() {
    //best practice to use lateinit with fields that hold views in just this way.
    lateinit var diceImage : ImageView
    lateinit var diceImage1 : ImageView

    @SuppressLint("WrongViewCast")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val rollButton: Button = findViewById(R.id.roll_button)
        rollButton.setOnClickListener{ rollDice() }

        diceImage = findViewById(R.id.dice_image)
        diceImage1 = findViewById(R.id.dice_image_1)
    }


    private fun throwDice(): Int {
        val randomInt = (1..6).random()

        val drawableResource = when (randomInt){
            1 -> R.drawable.dice_1
            2 -> R.drawable.dice_2
            3 -> R.drawable.dice_3
            4 -> R.drawable.dice_4
            5 -> R.drawable.dice_5
            else -> R.drawable.dice_6
        }

        return drawableResource

    }

    private fun rollDice(){
//        Toast.makeText(this, "button clicked", Toast.LENGTH_SHORT).show()

//        val resultText: TextView = findViewById(R.id.result_text)
//        resultText.text = randomInt.toString()

        diceImage.setImageResource(this.throwDice())
        diceImage1.setImageResource(this.throwDice())
    }


}