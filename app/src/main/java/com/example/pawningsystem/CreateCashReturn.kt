package com.example.pawningsystem

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.Toast

class CreateCashReturn : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_create_cash_return)

        val submitBtn = findViewById<Button>(R.id.createBtn)
        val viewAllBtn = findViewById<Button>(R.id.cancelBtn)

        submitBtn.setOnClickListener {
            val intent = Intent(this, ViewCashReturn::class.java)
            Toast.makeText(this, "Cash return done successfully!", Toast.LENGTH_SHORT).show()
            startActivity(intent)
        }

        viewAllBtn.setOnClickListener {
            val intent = Intent(this, ViewCashReturn::class.java)
            startActivity(intent)
        }
    }
}