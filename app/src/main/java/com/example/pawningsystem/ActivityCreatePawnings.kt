package com.example.pawningsystem

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.Toast

class ActivityCreatePawnings : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_createpawnings)

        val submitAct = findViewById<Button>(R.id.btnSubmit)

        submitAct.setOnClickListener{
            Toast.makeText(this, "Pawning Request Submitted", Toast.LENGTH_SHORT).show()
        }

        val createViewAllActButton = findViewById<Button>(R.id.btnViewAll)

        createViewAllActButton.setOnClickListener{
            val Intent = Intent(this, ActivityViewPawnings::class.java)
            startActivity(Intent)
        }

    }

}