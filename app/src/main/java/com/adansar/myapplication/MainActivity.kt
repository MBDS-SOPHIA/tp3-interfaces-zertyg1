package com.adansar.myapplication

import android.animation.ObjectAnimator
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.animation.AccelerateDecelerateInterpolator
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import nl.dionsegijn.konfetti.core.Party
import nl.dionsegijn.konfetti.core.Position
import nl.dionsegijn.konfetti.core.emitter.Emitter
import nl.dionsegijn.konfetti.xml.KonfettiView
import java.util.concurrent.TimeUnit

class MainActivity : AppCompatActivity() {
    private lateinit var dice1: ImageView
    private lateinit var dice2: ImageView
    private lateinit var resultText: TextView
    private lateinit var targetNumberInput: EditText
    private lateinit var konfettiView: KonfettiView


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

        dice2 = findViewById(R.id.dice2)
        resultText = findViewById(R.id.resultText)

        targetNumberInput = findViewById(R.id.targetNumber)

        konfettiView = findViewById(R.id.konfettiView)


        targetNumberInput.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                val number = s.toString().toIntOrNull()
                if (number != null && number in 2..12) {
                    rollDice()
                } else {
                    resultText.text = ""
                }
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
        val targetNumber = targetNumberInput.text.toString().toIntOrNull() ?: return

        if (sum == targetNumber) {
            resultText.text = "Félicitations ! Vous avez gagné !"
            animateWin()
            showKonfetti()
        } else {
            resultText.text = "Dommage ! Essayez encore (Somme: $sum)"
        }
    }

    private fun animateWin() {
        ObjectAnimator.ofFloat(dice1, "translationY", 0f, -50f, 0f).apply {
            duration = 1000
            interpolator = AccelerateDecelerateInterpolator()
            start()
        }

        ObjectAnimator.ofFloat(dice2, "translationY", 0f, -50f, 0f).apply {
            duration = 1000
            interpolator = AccelerateDecelerateInterpolator()
            start()
        }
    }

    private fun showKonfetti() {
        val party = Party(
            speed = 0f,
            maxSpeed = 30f,
            damping = 0.9f,
            spread = 360,
            colors = listOf(0xfce18a, 0xff726d, 0xf4306d, 0xb48def),
            emitter = Emitter(duration = 100, TimeUnit.MILLISECONDS).max(100),
            position = Position.Relative(0.5, 0.3)
        )
        konfettiView.start(party)
    }
}