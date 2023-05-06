package com.example.pawningsystem.activities

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.pawningsystem.databinding.ActivityCreateCashReturnBinding
import com.example.pawningsystem.models.CashReturnModel
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class CreateCashReturnActivity:AppCompatActivity() {

    private lateinit var binding: ActivityCreateCashReturnBinding
    private lateinit var database: DatabaseReference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityCreateCashReturnBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.cancelBtn2.setOnClickListener {

            val i = Intent(this@CreateCashReturnActivity, viewAllReturns::class.java)
            startActivity(i)
        }
        binding.btnCalcTot.setOnClickListener {
            val i = Intent(this@CreateCashReturnActivity, Activity_Cash_Return_calc::class.java)
            startActivity(i) }


        binding.payBtn1.setOnClickListener {


            val csNIC = binding.csNIC.text.toString()
            val fName = binding.fullName.text.toString()
            val email = binding.email.text.toString()
            val phone = binding.phone.text.toString()
            val cash = binding.cashAmount.text.toString()
            val dateToCollect = binding.dateToCollect.text.toString()



            database = FirebaseDatabase.getInstance().getReference("CashReturn")

            //unique ID generate

            val cReturn = CashReturnModel(csNIC,fName, email, phone, cash,dateToCollect)
            database.child(csNIC).setValue(cReturn).addOnSuccessListener {
                binding.csNIC.text?.clear()
                binding.fullName.text?.clear()
                binding.email.text?.clear()
                binding.phone.text?.clear()
                binding.cashAmount.text?.clear()
                binding.dateToCollect.text?.clear()

                Toast.makeText(this, "Successfuly Saved", Toast.LENGTH_LONG).show()

                val i = Intent(this@CreateCashReturnActivity, viewAllReturns::class.java)
                startActivity(i)
            }.addOnFailureListener {
                Toast.makeText(applicationContext, "Failed", Toast.LENGTH_LONG).show()
            }
        }
    }
}
