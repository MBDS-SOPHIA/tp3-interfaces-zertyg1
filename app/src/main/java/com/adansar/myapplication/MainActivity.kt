package com.adansar.myapplication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView

class MainActivity : AppCompatActivity() {
    private lateinit var dice1: ImageView
    private lateinit var rollButton: Button
    private lateinit var dice2: ImageView
    private lateinit var resultText: TextView
    private lateinit var targetNumberInput: EditText

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

        rollButton.isEnabled = false

        rollButton.setOnClickListener {
            rollDice()
        }

        targetNumberInput.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                val number = s.toString().toIntOrNull()
                rollButton.isEnabled = number != null && number in 2..12
            }

            override fun afterTextChanged(s: Editable?) {}
        })
    }

    private fun rollDice() {
        val dice1Value = (1..6).random()
        val dice2Value = (1..6).random()

        dice1.setImageResource(diceImages[dice1Value - 1])
        dice2.setImageResource(diceImages[dice2Value - 1])

        val sum = dice1Value + dice2Value

        if (dice1Value == dice2Value) {
            resultText.text = "Félicitations ! Vous avez gagné !"
        } else {
            resultText.text = "Dommage ! Essayez encore (Somme: $sum)"
        }
    }
}