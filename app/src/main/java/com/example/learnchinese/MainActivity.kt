package com.example.learnchinese

import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.learnchinese.R
import kotlin.random.Random

class MainActivity : AppCompatActivity(), View.OnClickListener {

    private lateinit var imageView: ImageView
    private lateinit var button1: Button
    private lateinit var button2: Button
    private lateinit var restartButton: Button
    private lateinit var scoreCount: TextView

    private var currentImageIndex = 0
    private var score = 0
    private var images = intArrayOf(R.drawable.ni, R.drawable.wo, R.drawable.ta, R.drawable.ai,
         R.drawable.nan, R.drawable.nu, R.drawable.yao, R.drawable.ren)
    private var correctAnswers = arrayOf("Nǐ / You", "Wǒ / Me", "Tā / Him", "Ài / Love", "Nán / Boy", "Nǚ / Girl", "Yào / Want", "Rén / Person")
    private var buttonTexts = arrayOf("Nǐ / You", "Wǒ / Me", "Tā / Him", "Ài / Love", "Nán / Boy", "Nǚ / Girl", "Yào / Want", "Rén / Person")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main_activity)

        imageView = findViewById(R.id.imageView)
        button1 = findViewById(R.id.button1)
        button2 = findViewById(R.id.button2)
        restartButton = findViewById(R.id.restart)
        scoreCount = findViewById(R.id.scorecount)

        button1.setOnClickListener(this)
        button2.setOnClickListener(this)
        restartButton.setOnClickListener { restartGame() }

        restartGame()
    }

    override fun onClick(view: View?) {
        val clickedButton = view as Button
        val answer = clickedButton.text.toString()

        if (answer == correctAnswers[currentImageIndex]) {
            score++
        }
        scoreCount.text = "$score/8"
        currentImageIndex++
        if (currentImageIndex < images.size) {
            loadNextImage()
        } else {
            showRestartButton()
        }
    }

    private fun loadNextImage() {
        imageView.setImageResource(images[currentImageIndex])
        val buttonSelect = Random.nextBoolean()
        var wrongAnswerIndex = Random.nextInt(0, 7)
        if(wrongAnswerIndex==currentImageIndex){
            wrongAnswerIndex=(wrongAnswerIndex+1)%7
        }
        if(buttonSelect)
        {
            button1.text = buttonTexts[currentImageIndex]
            button2.text = buttonTexts[wrongAnswerIndex]
        }
        else{
            button1.text = buttonTexts[wrongAnswerIndex]
            button2.text = buttonTexts[currentImageIndex]
        }

    }

    private fun showRestartButton() {
        button1.visibility = View.GONE
        button2.visibility = View.GONE
        restartButton.visibility = View.VISIBLE
    }

    private fun restartGame() {
        currentImageIndex = 0
        score = 0
        scoreCount.text = "$score/8"
        button1.visibility = View.VISIBLE
        button2.visibility = View.VISIBLE
        restartButton.visibility = View.GONE
        loadNextImage()
    }
}
