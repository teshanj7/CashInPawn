package com.example.pawningsystem.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import android.widget.Toast
import com.example.pawningsystem.R
import com.example.pawningsystem.models.CashReturnModel
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.*

private lateinit var returnTotal : TextView
private lateinit var firebaseAuth: FirebaseAuth

private lateinit var dbRef: DatabaseReference

class Activity_Cash_Return_calc : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cash_return_calc)

// Initialize Firebase Authentication and get the current user
        firebaseAuth = FirebaseAuth.getInstance()
        val currentUser = firebaseAuth.currentUser

//initialize a variable to capture logged in user's email
        val useremail = currentUser?.email

        returnTotal = findViewById(R.id.totalReturns)

        getPawningCalcData(useremail)
    }

    private fun getPawningCalcData(useremail: String?) {

        dbRef = FirebaseDatabase.getInstance().getReference("CashReturn")

//getting data
        dbRef.orderByChild("email").equalTo(useremail).addValueEventListener(object :
            ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {

                var noOfPawningRequests = 0

                for (pawnSnap in snapshot.children) {

                    snapshot.getValue(CashReturnModel::class.java)
                    noOfPawningRequests++
                }

                returnTotal.text = noOfPawningRequests.toString()
            }

            override fun onCancelled(error: DatabaseError) {
                TODO("Not yet implemented")
            }

        })
    }
}
