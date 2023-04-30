package com.example.pawningsystem

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageButton
import android.widget.Toast

class ActivityDashBoard : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dash_board)

        val pawningSystemButton = findViewById<ImageButton>(R.id.imgbtnPawning)

        pawningSystemButton.setOnClickListener{
            val Intent = Intent(this, ActivityCreatePawnings::class.java)
            Toast.makeText(this, "Welcome to the pawning system!", Toast.LENGTH_SHORT).show()
            startActivity(Intent)
        }
    }
}