package com.example.pawningsystem.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import com.example.pawningsystem.R
import com.example.pawningsystem.models.InquiryModel
import com.google.firebase.database.FirebaseDatabase

class InquiryDetails : AppCompatActivity() {

    private lateinit var tvInqID : TextView
    private lateinit var tvInqName : TextView
    private lateinit var tvInqNIC : TextView
    private lateinit var tvInqTeleNo : TextView
    private lateinit var tvInqSubjectOM : TextView
    private lateinit var tvInqMessage : TextView

    private lateinit var btnUpdate: Button
    private lateinit var btnDelete: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_inquiry_details)

        initView()
        setValuesToViews()

        btnUpdate.setOnClickListener {
            openUpdateDialog(
                intent.getStringExtra("inquiryId").toString(),
                intent.getStringExtra("iqFullName").toString(),
                intent.getStringExtra("iqSubjectOfMatter").toString()
            )
        }

        btnDelete.setOnClickListener {
            deleteInquiry(
                intent.getStringExtra("inquiryId").toString()
            )
        }
    }

    private fun deleteInquiry(id: String) {
        val dbRef = FirebaseDatabase.getInstance().getReference("Inquiries").child(id)
        val mTask = dbRef.removeValue()

        mTask.addOnSuccessListener {
            Toast.makeText(this, "Inquiry deleted!", Toast.LENGTH_LONG).show()

            val intent = Intent(this, ViewCustomerSupport::class.java)
            finish()
            startActivity(intent)
        }.addOnFailureListener { err->
            Toast.makeText(this, "Inquiry deletion failed! ${err.message}", Toast.LENGTH_LONG).show()
            val intent = Intent(this, ViewCustomerSupport::class.java)
            finish()
            startActivity(intent)
        }
    }

    private fun openUpdateDialog(
        inquiryId: String, inquiryName: String, inquirySub: String
    ) {

        val mDialog = AlertDialog.Builder(this)
        val inflater = layoutInflater

        val mDialogView = inflater.inflate(R.layout.inquiry_update_dialog, null)

        mDialog.setView(mDialogView)

        val etInquiryName = mDialogView.findViewById<EditText>(R.id.etInquiryName)
        val etInquiryNic = mDialogView.findViewById<EditText>(R.id.etInquiryNic)
        val etInquiryTeleNo = mDialogView.findViewById<EditText>(R.id.etInquiryTele)
        val etInquirySOM = mDialogView.findViewById<EditText>(R.id.etInquirySOM)
        val etInquiryMessage = mDialogView.findViewById<EditText>(R.id.etInquiryMsg)
        val btnUpdateData = mDialogView.findViewById<Button>(R.id.btnUpdateData)

        etInquiryName.setText(intent.getStringExtra("iqFullName").toString())
        etInquiryNic.setText(intent.getStringExtra("iqNic").toString())
        etInquiryTeleNo.setText(intent.getStringExtra("iqTelephone").toString())
        etInquirySOM.setText(intent.getStringExtra("iqSubjectOfMatter").toString())
        etInquiryMessage.setText(intent.getStringExtra("iqMessage").toString())

        mDialog.setTitle("Updating $inquiryName 's $inquirySub inquiry")
        val alertDialog = mDialog.create()
        alertDialog.show()

        btnUpdateData.setOnClickListener {
            updateInquiryData(
                inquiryId,
                etInquiryName.text.toString(),
                etInquiryNic.text.toString(),
                etInquiryTeleNo.text.toString(),
                etInquirySOM.text.toString(),
                etInquiryMessage.text.toString()
            )

            Toast.makeText(applicationContext, "Inquiry updated!", Toast.LENGTH_LONG).show()

            tvInqName.text = etInquiryName.text.toString()
            tvInqNIC.text = etInquiryNic.text.toString()
            tvInqTeleNo.text = etInquiryNic.text.toString()
            tvInqSubjectOM.text = etInquirySOM.text.toString()
            tvInqMessage.text = etInquiryMessage.text.toString()

            alertDialog.dismiss()
        }
    }

    private fun updateInquiryData(
        id: String,
        name: String,
        nic: String,
        teleNo: String,
        SOM: String,
        message: String
    ) {
        val dbRef = FirebaseDatabase.getInstance().getReference("Inquiries").child(id)
        val inqInfo = InquiryModel(id, name, nic, teleNo, SOM, message)

        dbRef.setValue(inqInfo)

    }

    private fun initView() {

        tvInqID = findViewById(R.id.tvInquiryId)
        tvInqName = findViewById(R.id.tvInquiryFullName)
        tvInqNIC = findViewById(R.id.tvInquiryNic)
        tvInqTeleNo = findViewById(R.id.tvInquiryTeleNo)
        tvInqSubjectOM = findViewById(R.id.tvInquirySubject)
        tvInqMessage = findViewById(R.id.tvInquiryMessage)

        btnUpdate = findViewById(R.id.btnUpdateINQ)
        btnDelete = findViewById(R.id.btnDeleteINQ)
    }
    private fun setValuesToViews() {

        tvInqID.text = intent.getStringExtra("inquiryId")
        tvInqName.text = intent.getStringExtra("iqFullName")
        tvInqNIC.text = intent.getStringExtra("iqNic")
        tvInqTeleNo.text = intent.getStringExtra("iqTelephone")
        tvInqSubjectOM.text = intent.getStringExtra("iqSubjectOfMatter")
        tvInqMessage.text = intent.getStringExtra("iqMessage")

    }


}