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
        val cashReturnsButton = findViewById<ImageButton>(R.id.imgbtnCash)
        val customerSupportButton = findViewById<ImageButton>(R.id.imgbtnCustomer)

        pawningSystemButton.setOnClickListener{
            val intent = Intent(this, ActivityCreatePawnings::class.java)
            Toast.makeText(this, "Welcome to the pawning system!", Toast.LENGTH_SHORT).show()
            startActivity(intent)
        }

        cashReturnsButton.setOnClickListener {
            val intent = Intent(this, CreateCashReturn::class.java)
            Toast.makeText(this, "Welcome to the cash returns system!", Toast.LENGTH_SHORT).show()
            startActivity(intent)
        }

        customerSupportButton.setOnClickListener {
            val intent = Intent(this, CreateCustomerSupport::class.java)
            Toast.makeText(this, "Welcome to the customer support corner!", Toast.LENGTH_SHORT).show()
            startActivity(intent)
        }

    }
}