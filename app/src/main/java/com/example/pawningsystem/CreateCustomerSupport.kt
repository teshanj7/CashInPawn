package com.example.pawningsystem

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.Toast

class CreateCustomerSupport : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_create_customer_support)

        val submitBtn = findViewById<Button>(R.id.SubmitButton1)
        val viewAllBtn = findViewById<Button>(R.id.CancelButton1)

        submitBtn.setOnClickListener {
            val intent = Intent(this, ViewCustomerSupport::class.java)
            Toast.makeText(this, "Inquiry submitted successfully!", Toast.LENGTH_SHORT).show()
            startActivity(intent)
        }

        viewAllBtn.setOnClickListener {
            val intent = Intent(this, ViewCustomerSupport::class.java)
            startActivity(intent)
        }
    }
}