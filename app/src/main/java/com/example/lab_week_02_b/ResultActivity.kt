package com.example.lab_week_02_b

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.graphics.toColorInt

class ResultActivity : AppCompatActivity() {
    companion object {
        const val COLOR_KEY = "COLOR_KEY"
        const val ERROR_KEY = "ERROR_KEY"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_result)

        val backgroundScreen = findViewById<ConstraintLayout>(R.id.background_screen)
        val resultMessage = findViewById<TextView>(R.id.color_code_result_message)
        val backButton = findViewById<Button>(R.id.back_button)

        val colorCode = intent?.getStringExtra(COLOR_KEY)

        if (colorCode.isNullOrEmpty()) {
            sendErrorResult()
            return
        }

        try {
            backgroundScreen.setBackgroundColor("#$colorCode".toColorInt())
            resultMessage.text = getString(
                R.string.color_code_result_message,
                colorCode.uppercase()
            )
        } catch (_: IllegalArgumentException) {
            sendErrorResult()
        }

        backButton.setOnClickListener {
            finish()
        }
    }

    private fun sendErrorResult() {
        val errorIntent = Intent().apply {
            putExtra(ERROR_KEY, true)
        }
        setResult(RESULT_OK, errorIntent)
        finish()
    }
}
