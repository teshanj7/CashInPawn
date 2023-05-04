package com.example.pawningsystem

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import com.example.madcustomersupport.InquiryModel
import com.google.android.material.textfield.TextInputEditText
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class CreateCustomerSupport : AppCompatActivity() {

    private lateinit var csFullName : TextInputEditText
    private lateinit var csEmail: TextInputEditText
    private lateinit var csTeleNo: TextInputEditText
    private lateinit var csSubject: TextInputEditText
    private lateinit var csMessage: TextInputEditText

    private lateinit var btnSubmitData: Button
    private lateinit var btnViewAllPawnings: Button

    private lateinit var dbRef: DatabaseReference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_create_customer_support)

        csFullName = findViewById(R.id.edtFullName)
        csEmail = findViewById(R.id.edtEmail)
        csTeleNo = findViewById(R.id.edtTeleNo)
        csSubject = findViewById(R.id.edtSubject)
        csMessage = findViewById(R.id.edtMessage)

        btnSubmitData = findViewById(R.id.SubmitButton1)
        btnViewAllPawnings = findViewById(R.id.CancelButton1)

        dbRef = FirebaseDatabase.getInstance().getReference("Inquiries")

        btnSubmitData.setOnClickListener {
            saveInquiryData()
        }
    }

    private fun saveInquiryData() {

        val iqName = csFullName.text.toString()
        val iqEmail = csEmail.text.toString()
        val iqTeleNo = csTeleNo.text.toString()
        val iqSubject = csSubject.text.toString()
        val iqMessage = csMessage.text.toString()

        if(iqName.isEmpty()){
            csFullName.error = "Please enter full name!"
        }
        if(iqEmail.isEmpty()){
            csEmail.error = "Please enter your NIC number!"
        }
        if(iqTeleNo.isEmpty()){
            csTeleNo.error = "Please enter your telephone number!"
        }
        if(iqSubject.isEmpty()){
            csSubject.error = "Please enter your Bank details!"
        }
        if(iqMessage.isEmpty()){
            csMessage.error = "Please enter the pickup address"
        }

        val inquiryId = dbRef.push().key!!

        val inquiry = InquiryModel(inquiryId, iqName, iqTeleNo, iqSubject, iqMessage)

        dbRef.child(inquiryId).setValue(inquiry).addOnCompleteListener {
            Toast.makeText(this, "Inquiry submitted!", Toast.LENGTH_LONG).show()

            csFullName.text?.clear()
            csEmail.text?.clear()
            csTeleNo.text?.clear()
            csSubject.text?.clear()
            csMessage.text?.clear()

        }.addOnFailureListener { error->
            Toast.makeText(this, "Inquiry submission failed! ${error.message}", Toast.LENGTH_LONG).show()
        }
    }
}