package com.example.pawningsystem

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class ActivityDeletePawnings : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_deletepawnings)

        val deleteActButton = findViewById<Button>(R.id.btnDelete)

        deleteActButton.setOnClickListener{
            val Intent = Intent(this, ActivityCreatePawnings::class.java)

            startActivity(Intent)
        }
    }
}