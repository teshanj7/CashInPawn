package com.example.pawningsystem.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import com.example.pawningsystem.models.InquiryModel
import com.example.pawningsystem.R
import com.google.android.material.textfield.TextInputEditText
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class CreateCustomerSupport : AppCompatActivity() {

    private lateinit var csFullName : TextInputEditText
    private lateinit var csNic: TextInputEditText
    private lateinit var csTeleNo: TextInputEditText
    private lateinit var csSubject: TextInputEditText
    private lateinit var csMessage: TextInputEditText

    private lateinit var btnSubmitData: Button
    private lateinit var btnViewAllInquiries: Button

    private lateinit var dbRef: DatabaseReference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_create_customer_support)

        csFullName = findViewById(R.id.edtFullName)
        csNic = findViewById(R.id.edtNic)
        csTeleNo = findViewById(R.id.edtTeleNo)
        csSubject = findViewById(R.id.edtSubject)
        csMessage = findViewById(R.id.edtMessage)

        btnSubmitData = findViewById(R.id.SubmitButton1)
        btnViewAllInquiries = findViewById(R.id.CancelButton1)

        dbRef = FirebaseDatabase.getInstance().getReference("Inquiries")

        btnSubmitData.setOnClickListener {
            saveInquiryData()
        }

        btnViewAllInquiries.setOnClickListener {
            val intent = Intent(this, ViewCustomerSupport::class.java)
            startActivity(intent)
        }
    }

    private fun saveInquiryData() {

        val iqName = csFullName.text.toString()
        val iqNic = csNic.text.toString()
        val iqTeleNo = csTeleNo.text.toString()
        val iqSubject = csSubject.text.toString()
        val iqMessage = csMessage.text.toString()

        if(iqName.isEmpty()){
            csFullName.error = "Please enter full name!"
        }
        if(iqNic.isEmpty()){
            csNic.error = "Please enter your NIC number!"
        }
        if(iqTeleNo.isEmpty()){
            csTeleNo.error = "Please enter your telephone number!"
        }
        if(iqSubject.isEmpty()){
            csSubject.error = "Please enter the subject!"
        }
        if(iqMessage.isEmpty()){
            csMessage.error = "Please enter the message"
        }

        val inquiryId = dbRef.push().key!!

        val inquiry = InquiryModel(inquiryId, iqName, iqNic ,iqTeleNo, iqSubject, iqMessage)

        dbRef.child(inquiryId).setValue(inquiry).addOnCompleteListener {
            Toast.makeText(this, "Inquiry submitted!", Toast.LENGTH_LONG).show()

            csFullName.text?.clear()
            csNic.text?.clear()
            csTeleNo.text?.clear()
            csSubject.text?.clear()
            csMessage.text?.clear()

        }.addOnFailureListener { error->
            Toast.makeText(this, "Inquiry submission failed! ${error.message}", Toast.LENGTH_LONG).show()
        }
    }
}