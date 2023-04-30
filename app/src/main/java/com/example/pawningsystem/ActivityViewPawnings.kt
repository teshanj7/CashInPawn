package com.example.pawningsystem

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class ActivityViewPawnings : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_viewpawnings)

        val submitUpdateAct1 = findViewById<Button>(R.id.btnUpdateSubmit1)

        submitUpdateAct1.setOnClickListener{
            val Intent = Intent(this, ActivityUpdatePawnings::class.java)

            startActivity(Intent)
        }

        val submitDeleteAct1 = findViewById<Button>(R.id.btnDeleteSubmit1)

        submitDeleteAct1.setOnClickListener{
            val Intent = Intent(this, ActivityDeletePawnings::class.java)

            startActivity(Intent)
        }

        val submitUpdateAct2 = findViewById<Button>(R.id.btnUpdateSubmit2)

        submitUpdateAct2.setOnClickListener{
            val Intent = Intent(this, ActivityUpdatePawnings::class.java)

            startActivity(Intent)
        }

        val submitDeleteAct2 = findViewById<Button>(R.id.btnDeleteSubmit2)

        submitDeleteAct2.setOnClickListener{
            val Intent = Intent(this, ActivityDeletePawnings::class.java)

            startActivity(Intent)
        }

    }
}