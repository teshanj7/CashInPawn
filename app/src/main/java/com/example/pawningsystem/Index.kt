package com.example.pawningsystem

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import com.example.pawningsystem.activities.Login
import com.example.pawningsystem.activities.Register

class Index : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_index)

        val loginBtn = findViewById<Button>(R.id.login_index)
        val createAccBtn = findViewById<Button>(R.id.create_btn_index)

        loginBtn.setOnClickListener {
            val intent = Intent(this, Login::class.java)
            startActivity(intent)
        }

        createAccBtn.setOnClickListener {
            val intent = Intent(this, Register::class.java)
            startActivity(intent)
        }
    }
}