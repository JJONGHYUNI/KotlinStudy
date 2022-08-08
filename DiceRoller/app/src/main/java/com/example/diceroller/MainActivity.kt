package com.example.diceroller

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val rollButton = findViewById<Button>(R.id.roll_button)
        rollButton.setOnClickListener { rollDice() }
        val countButton = findViewById<Button>(R.id.count_button)
        countButton.setOnClickListener { countUp() }

    }
    private fun rollDice(){
        Toast.makeText(this, "button clicked", Toast.LENGTH_SHORT).show()
        val resultText = findViewById<TextView>(R.id.result_text)
        val randomInt=(1..6).random()
        resultText.text=randomInt.toString()
    }
    private fun countUp(){
        val resultText=findViewById<TextView>(R.id.result_text)
        if (resultText.text=="Hello World!"){
            resultText.text="1"
        }else{
            var resultInt=resultText.text.toString().toInt()
            if (resultInt<6){
                resultInt++
                resultText.text=resultInt.toString()
            }
        }
    }

}