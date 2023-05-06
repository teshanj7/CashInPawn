package com.example.pawningsystem.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.TextView
import com.example.pawningsystem.R
import com.example.pawningsystem.adapters.PawnAdapter
import com.example.pawningsystem.models.PawningModel
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.*

class PawnCalcActivity : AppCompatActivity() {

    private lateinit var firebaseAuth: FirebaseAuth

    private lateinit var totalRecords : TextView

    //implementing variable for the database
    private lateinit var dbRef: DatabaseReference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_pawn_calc)

        // Initialize Firebase Authentication and get the current user
        firebaseAuth = FirebaseAuth.getInstance()
        val currentUser = firebaseAuth.currentUser

        //initialize a variable to capture logged in user's email
        val useremail = currentUser?.email

        totalRecords = findViewById(R.id.textCalcInput1)

        getPawningCalcData(useremail)
    }

    private fun getPawningCalcData(useremail: String?) {

        dbRef = FirebaseDatabase.getInstance().getReference("Pawnings")

        //getting data
        dbRef.orderByChild("email").equalTo(useremail).addValueEventListener(object :
            ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {

                var noOfPawningRequests = 0

                for (pawnSnap in snapshot.children){

                    snapshot.getValue(PawningModel::class.java)
                    noOfPawningRequests++
                }

                totalRecords.text = noOfPawningRequests.toString()
            }



            override fun onCancelled(error: DatabaseError) {
                TODO("Not yet implemented")
            }

        })
    }
}