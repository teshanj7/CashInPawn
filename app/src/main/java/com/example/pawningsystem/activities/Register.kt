package com.example.pawningsystem.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.pawningsystem.databinding.ActivityRegisterBinding
import com.example.pawningsystem.models.UserModel
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class Register : AppCompatActivity() {
    private lateinit var binding: ActivityRegisterBinding
    private lateinit var firebaseAuth: FirebaseAuth
    private lateinit var dbRefUser: DatabaseReference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityRegisterBinding.inflate(layoutInflater)
        setContentView(binding.root)

        firebaseAuth = FirebaseAuth.getInstance()

        //database created
        dbRefUser = FirebaseDatabase.getInstance().getReference("Users")

        //login link
        binding.signuptoLogin.setOnClickListener {
            val intent = Intent(this, Login::class.java)
            startActivity(intent)
        }

        binding.btnSubmitC.setOnClickListener {
            val name = binding.userName.text.toString()
            val email = binding.userEmail.text.toString()
            val nic = binding.userNIC.text.toString()
            val address = binding.userADD.text.toString()
            val phone = binding.userTELE.text.toString()
            val username = binding.userUSERNAME.text.toString()
            val pass = binding.userPASS.text.toString()
            val repass = binding.userRETYPASS.text.toString()

            if (name.isNotEmpty() && email.isNotEmpty() && nic.isNotEmpty() && address.isNotEmpty() && phone.isNotEmpty()
                && username.isNotEmpty() && pass.isNotEmpty() && repass.isNotEmpty()){
                if (pass == repass){

                    val user = UserModel(name,email,nic,address,phone,username,pass,repass)
                    dbRefUser.child(nic).setValue(user)

                    firebaseAuth.createUserWithEmailAndPassword(email,pass).addOnCompleteListener {
                        if (it.isSuccessful){
                            Toast.makeText(this,"Account created Successfully", Toast.LENGTH_SHORT).show()
                            val intent = Intent(this, Login::class.java)
                            startActivity(intent)
                        }else{
                            Toast.makeText(this,it.exception.toString(), Toast.LENGTH_SHORT).show()
                        }
                    }
                }else{
                    Toast.makeText(this,"Password is not matching", Toast.LENGTH_SHORT).show()
                }
            }else{
                Toast.makeText(this,"Empty fields are not allowed !!", Toast.LENGTH_SHORT).show()
            }
        }
    }

}
