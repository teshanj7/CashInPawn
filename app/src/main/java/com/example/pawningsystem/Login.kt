package com.example.pawningsystem

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import com.example.pawningsystem.activities.ActivityDashBoard

class Login : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        val loginBtn = findViewById<Button>(R.id.btn_login)
        val signUpLink = findViewById<TextView>(R.id.signup_login)

        loginBtn.setOnClickListener {
            val intent = Intent(this, ActivityDashBoard::class.java)
            Toast.makeText(this, "Successfully logged in!", Toast.LENGTH_SHORT).show()
            startActivity(intent)
        }

        signUpLink.setOnClickListener{
            val intent = Intent(this, Register::class.java)
            startActivity(intent)
        }
    }
}