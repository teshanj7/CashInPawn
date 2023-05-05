package com.example.pawningsystem.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageButton
import android.widget.Toast
import com.example.pawningsystem.CreateCashReturn
import com.example.pawningsystem.R
import com.example.pawningsystem.databinding.ActivityDashBoardBinding
import com.google.firebase.auth.FirebaseAuth

class ActivityDashBoard : AppCompatActivity() {
    private lateinit var binding: ActivityDashBoardBinding
    private lateinit var firebaseAuth: FirebaseAuth
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDashBoardBinding.inflate(layoutInflater)
        setContentView(binding.root)

        firebaseAuth = FirebaseAuth.getInstance()

//        val viewprofilebutton = findViewById<ImageButton>(R.id.viewProfilebtn)
        val pawningSystemButton = findViewById<ImageButton>(R.id.imgbtnPawning)
        val cashReturnsButton = findViewById<ImageButton>(R.id.imgbtnCash)
        val customerSupportButton = findViewById<ImageButton>(R.id.imgbtnCustomer)

        binding.viewProfilebtn.setOnClickListener {
            val intent = Intent(this, ViewProfile::class.java)
            Toast.makeText(this, "Welcome to the profile page!", Toast.LENGTH_SHORT).show()
            startActivity(intent)
        }

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