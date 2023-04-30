package com.example.pawningsystem

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class ActivityUpdatePawnings : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_updatepawnings)

        val updateActButton = findViewById<Button>(R.id.btnUpdate)

        updateActButton.setOnClickListener{
            val Intent = Intent(this, ActivityViewPawnings::class.java)

            startActivity(Intent)
        }
    }
}