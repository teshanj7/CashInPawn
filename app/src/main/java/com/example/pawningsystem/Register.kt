package com.example.pawningsystem

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.TextView
import android.widget.Toast

class Register : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

        val submitBtn = findViewById<Button>(R.id.btn_submit_C)
        val loginLink = findViewById<TextView>(R.id.login_C)

        submitBtn.setOnClickListener {
            val intent = Intent(this, Login::class.java)
            Toast.makeText(this, "Account created successfully!", Toast.LENGTH_SHORT).show()
            startActivity(intent)
        }

        loginLink.setOnClickListener{
            val intent = Intent(this, Login::class.java)
            startActivity(intent)
        }
    }
}