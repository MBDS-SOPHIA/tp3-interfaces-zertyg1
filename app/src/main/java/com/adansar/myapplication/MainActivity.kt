package com.adansar.myapplication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView

class MainActivity : AppCompatActivity() {
    private lateinit var dice1: ImageView
    private lateinit var rollButton: Button
    private lateinit var dice2: ImageView
    private lateinit var resultText: TextView

    private val diceImages = listOf(
        R.drawable.dice_1,
        R.drawable.dice_2,
        R.drawable.dice_3,
        R.drawable.dice_4,
        R.drawable.dice_5,
        R.drawable.dice_6
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        dice1 = findViewById(R.id.dice1)
        rollButton = findViewById(R.id.rollButton)

        dice2 = findViewById(R.id.dice2)
        resultText = findViewById(R.id.resultText)

        rollButton.setOnClickListener {
            rollDice()
        }
    }

    private fun rollDice() {
        val dice1Value = (1..6).random()
        val dice2Value = (1..6).random()

        dice1.setImageResource(diceImages[dice1Value - 1])
        dice2.setImageResource(diceImages[dice2Value - 1])

        if (dice1Value == dice2Value) {
            resultText.text = "Félicitations ! Vous avez gagné !"
        } else {
            resultText.text = "Essayez encore !"
        }
    }
}