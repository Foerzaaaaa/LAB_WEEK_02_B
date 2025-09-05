package com.example.lab_week_02_b

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.textfield.TextInputEditText
import androidx.activity.result.contract.ActivityResultContracts

class MainActivity : AppCompatActivity() {
    companion object {
        private const val COLOR_KEY = "COLOR_KEY"
        private const val ERROR_KEY = "ERROR_KEY"
    }

    private val startForResult =
        registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { activityResult ->
            val data = activityResult.data
            val error = data?.getBooleanExtra(ERROR_KEY, false) // fixed typo
            if (error == true) {
                Toast.makeText(
                    this,
                    getString(R.string.color_code_input_invalid),
                    Toast.LENGTH_LONG
                ).show()
            }
        }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val submitButton = findViewById<Button>(R.id.submit_button)
        val colorInput = findViewById<TextInputEditText>(R.id.color_code_input_field)

        submitButton.setOnClickListener {
            val colorCode = colorInput.text.toString().trim()

            if (colorCode.isEmpty()) {
                Toast.makeText(
                    this,
                    getString(R.string.color_code_input_empty),
                    Toast.LENGTH_LONG
                ).show()
            } else if (colorCode.length < 6) {
                Toast.makeText(
                    this,
                    getString(R.string.color_code_input_wrong_length),
                    Toast.LENGTH_LONG
                ).show()
            } else {
                val resultIntent = Intent(this, ResultActivity::class.java)
                resultIntent.putExtra(COLOR_KEY, colorCode)
                startForResult.launch(resultIntent)
            }
        }
    }
}
