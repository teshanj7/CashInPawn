package com.example.pawningsystem.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.pawningsystem.R
import com.example.pawningsystem.databinding.ActivityUpdateProfileBinding
import com.example.pawningsystem.models.UserModel
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.database.*

class UpdateProfile : AppCompatActivity() {

    private lateinit var binding: ActivityUpdateProfileBinding
    private lateinit var firebaseAuth: FirebaseAuth
    private lateinit var dbRef: DatabaseReference
    private lateinit var currentUser: FirebaseUser
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityUpdateProfileBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //Firebase Authenticaton and get current user
        firebaseAuth = FirebaseAuth.getInstance()
        currentUser = firebaseAuth.currentUser!!

        //check user currently logged in
        if (currentUser != null){
            val useremail = currentUser.email
            dbRef = FirebaseDatabase.getInstance().getReference("Users")

            dbRef.orderByChild("email").equalTo(useremail).addListenerForSingleValueEvent(object : ValueEventListener{
                override fun onDataChange(snapshot: DataSnapshot) {
                    if (snapshot.exists()){
                        //get user data from the database
                        val user = snapshot.children.first().getValue(UserModel::class.java)

                        //set user data
                        binding.userNameupdate.setText(user?.name)
                        binding.userEmailupdate.setText(user?.email)
                        binding.userNICupdate.setText(user?.nic)
                        binding.userADDupdate.setText(user?.address)
                        binding.userTELEupdate.setText(user?.phone)
                        binding.userUSERNAMEupdate.setText(user?.username)
                        binding.userPASSupdate.setText(user?.pass)
                    }else{
                        Toast.makeText(applicationContext,"User not found", Toast.LENGTH_SHORT).show()
                    }
                }

                override fun onCancelled(error: DatabaseError) {
                    //handle the error
                    Toast.makeText(applicationContext, error.message, Toast.LENGTH_SHORT).show()
                }
            })
        }else{
            //user is not logged in, so redirect to the login activity
            val intent = Intent(this, Login::class.java)
            startActivity(intent)
            finish()
        }

        binding.btnUpdate.setOnClickListener {
            saveUpdate()
        }
    }

    private fun saveUpdate(){
        val name = binding.userNameupdate.text.toString().trim()
        val email = binding.userEmailupdate.text.toString().trim()
        val nic = binding.userNICupdate.text.toString().trim()
        val address = binding.userADDupdate.text.toString().trim()
        val phone = binding.userTELEupdate.text.toString().trim()
        val username = binding.userUSERNAMEupdate.text.toString().trim()
        val pass = binding.userPASSupdate.text.toString().trim()

        if (name.isEmpty() || email.isEmpty() || nic.isEmpty() || address.isEmpty() || phone.isEmpty() || username.isEmpty() || pass.isEmpty()){
            Toast.makeText(this,"Please fill in all fields", Toast.LENGTH_SHORT).show()
            return
        }

        //update the user details in the database
        dbRef.orderByChild("email").equalTo(currentUser.email).addListenerForSingleValueEvent(object : ValueEventListener{
            override fun onDataChange(snapshot: DataSnapshot) {
                if (snapshot.exists()) {
                    snapshot.children.forEach { dataSnapshot ->
                        val user = dataSnapshot.getValue(UserModel::class.java)
                        user?.let {
                            //update user details based on the email
                            it.name = name
                            it.email = email
                            it.nic = nic
                            it.address = address
                            it.phone = phone
                            it.username = username
                            it.pass = pass
                            dbRef.child(dataSnapshot.key!!).setValue(user)
                                .addOnSuccessListener {
                                    Toast.makeText(
                                        this@UpdateProfile,
                                        "Updated Successfully",
                                        Toast.LENGTH_SHORT
                                    ).show()
                                    val intent = Intent(this@UpdateProfile, ViewProfile::class.java)
                                    startActivity(intent)
                                }
                                .addOnFailureListener {
                                    Toast.makeText(
                                        this@UpdateProfile,
                                        "Fail to save changes",
                                        Toast.LENGTH_SHORT
                                    ).show()
                                }
                        }
                    }
                }else{
                    //user not found
                    Toast.makeText(applicationContext,"User not found", Toast.LENGTH_SHORT).show()
                }
            }

            override fun onCancelled(error: DatabaseError) {
                //handle the error
                Toast.makeText(applicationContext, error.message, Toast.LENGTH_SHORT).show()
            }
        })
    }
}