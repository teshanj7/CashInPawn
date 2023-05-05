package com.example.pawningsystem.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.pawningsystem.databinding.ActivityViewProfileBinding
import com.example.pawningsystem.models.UserModel
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener

class ViewProfile : AppCompatActivity() {

    private lateinit var binding: ActivityViewProfileBinding
    private lateinit var firebaseAuth: FirebaseAuth
    private lateinit var dbRef: DatabaseReference
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityViewProfileBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Initialize Firebase Authentication and get the current user
        firebaseAuth = FirebaseAuth.getInstance()
        val currentUser = firebaseAuth.currentUser

        //handle click, opem edit profile
        binding.btnviewUpdate.setOnClickListener {
            val intent = Intent(this, UpdateProfile::class.java)
            startActivity(intent)
        }

        if (currentUser != null){
            val useremail = currentUser.email
            val dbRef = FirebaseDatabase.getInstance().getReference("Users")
            dbRef.orderByChild("email").equalTo(useremail).addListenerForSingleValueEvent(object : ValueEventListener{
                override fun onDataChange(snapshot: DataSnapshot) {
                    if (snapshot.exists()){
                        //get the user data
                        val user = snapshot.children.first().getValue(UserModel::class.java)
                        //set the user data to view activity
                        binding.userNameprofile.text = user?.name
                        binding.userEmailprofile.text = user?.email
                        binding.userNICprofile.text = user?.nic
                        binding.userADDprofile.text = user?.address
                        binding.userTELEprofile.text = user?.phone
                        binding.userUSERNAMEprofile.text = user?.username
                        binding.userPassprofile.text = user?.pass
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

        //logout button
        binding.btnlogout.setOnClickListener {
            firebaseAuth.signOut()
            val intent = Intent(this,Login::class.java)
            startActivity(intent)
        }

        binding.btndelete.setOnClickListener {
            val currentUser = FirebaseAuth.getInstance().currentUser

            //check user
            if (currentUser != null){
                val useremail = currentUser.email
                val dbRef = FirebaseDatabase.getInstance().getReference("Users")
                dbRef.orderByChild("email").equalTo(useremail).addListenerForSingleValueEvent(object : ValueEventListener{
                    override fun onDataChange(snapshot: DataSnapshot) {
                        if (snapshot.exists()){
                            val userID = snapshot.children.first().key
                            //delete the user from the database
                            dbRef.child(userID!!).removeValue()

                            FirebaseAuth.getInstance().signOut()

                            //delete the user from Firebase Authentication
                            currentUser.delete().addOnCompleteListener { task->
                                if (task.isSuccessful){
                                    val intent = Intent(this@ViewProfile,Login::class.java)
                                    startActivity(intent)
                                    finish()
                                }else{
                                    Toast.makeText(applicationContext,"Delete user account", Toast.LENGTH_SHORT).show()
                                }
                            }
                        }else{
                            Toast.makeText(applicationContext,"User not found",Toast.LENGTH_SHORT).show()
                        }
                    }

                    override fun onCancelled(error: DatabaseError) {
                        //handle the error
                        Toast.makeText(applicationContext, error.message, Toast.LENGTH_SHORT).show()
                    }
                })
            }
        }
    }
}