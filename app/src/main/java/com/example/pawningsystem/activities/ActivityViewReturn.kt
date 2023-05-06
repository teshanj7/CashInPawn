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
import com.example.pawningsystem.databinding.ActivityViewReturnBinding
import com.example.pawningsystem.databinding.SingleCashReturnBinding
import com.example.pawningsystem.models.CashReturnModel
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import org.w3c.dom.Text

class ActivityViewReturn : AppCompatActivity() {


    private lateinit var  csNic: TextView
    private lateinit var  csName :TextView
    private lateinit var csPhone : TextView
    private lateinit var  csCash :TextView
    private lateinit var csEmail : TextView
    private lateinit var  csDate :TextView
    private lateinit var binding: ActivityViewReturnBinding
    private lateinit var database :DatabaseReference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_view_return)


        binding = ActivityViewReturnBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val cReturn = intent.getParcelableExtra<CashReturnModel>("email")

        if(cReturn!=null){
            csNic = findViewById(R.id.csNIC)
            csName= findViewById(R.id.fullName)
            csPhone= findViewById(R.id.phone)
            csEmail = findViewById(R.id.email)
            csCash = findViewById(R.id.cashAmount)
            csDate = findViewById(R.id.dateToCollect)


            csNic.text = cReturn.csNIC
            csName.text = cReturn.fullName
            csEmail.text = cReturn.email
            csPhone.text = cReturn.phone
            csCash.text = cReturn.cashAmount
            csDate.text = cReturn.dateToCollect

        }

        binding.Update2.setOnClickListener {
            openUpdateDialog(
                cReturn
            )
        }

        binding.Delete1.setOnClickListener {
            deleteReturn(
                cReturn?.csNIC.toString()
            )
        }

    }
    private fun openUpdateDialog(cReturn: CashReturnModel?) {
        val mDialog = AlertDialog.Builder(this)
        val inflater = layoutInflater
        val mDialogView = inflater.inflate(R.layout.update_dialog_cash,null)

        mDialog.setView(mDialogView)
        Toast.makeText(this,"fullName,${cReturn?.email}",Toast.LENGTH_SHORT).show()

        val cRfullName = mDialogView.findViewById<EditText>(R.id.fullName)
        val cREmail = mDialogView.findViewById<EditText>(R.id.email)
        val cRphone = mDialogView.findViewById<EditText>(R.id.phone)
        val cRNIC = mDialogView.findViewById<EditText>(R.id.csNIC)
        val cRCashAmount = mDialogView.findViewById<EditText>(R.id.cashAmount)
        val cRDateCollect = mDialogView.findViewById<EditText>(R.id.dateToCollect)


        val btnUpdateView = mDialogView.findViewById<Button>(R.id.updateBtn1)


        cRNIC.setText(cReturn?.csNIC)
        cRfullName.setText(cReturn?.fullName)
        cREmail.setText(cReturn?.email)
        cRphone.setText(cReturn?.phone)
        cRCashAmount.setText(cReturn?.cashAmount)
        cRDateCollect.setText(cReturn?.dateToCollect)


        mDialog.setTitle("Updating  ${cReturn?.fullName}  Record")

        val alertDialog = mDialog.create()
        alertDialog.show()

        btnUpdateView.setOnClickListener {

            updateReturnData(

                cRNIC.text.toString(),
                cRfullName.text.toString(),
                cREmail.text.toString(),
                cRphone.text.toString(),
                cRCashAmount.text.toString(),
                cRDateCollect.text.toString(),
            )
            Toast.makeText(applicationContext,"Return Data Updated!",Toast.LENGTH_LONG).show()

            csNic.text  = cRNIC.text.toString()
            csName.text = cRfullName.text.toString()
            csEmail.text = cREmail.text.toString()
            csPhone.text = cRphone.text.toString()
            csCash.text = cRCashAmount.text.toString()
            csDate.text = cRDateCollect.text.toString()

            alertDialog.dismiss()
        }
    }

    private fun deleteReturn(
        csID:String
    )
    {
        Toast.makeText(this,"delete $csID",Toast.LENGTH_SHORT).show()
        val dbRef = FirebaseDatabase.getInstance().getReference("CashReturn").child(csID)
        val mTask = dbRef.removeValue()
        mTask.addOnSuccessListener {
            Toast.makeText(this,"Return data Deleted",Toast.LENGTH_LONG).show()
            val intent = Intent(this, ActivityViewReturn::class.java)
            finish()
            startActivity(intent)
        }.addOnFailureListener { error->
            Toast.makeText(this,"Deleting Error ${error.message}",Toast.LENGTH_LONG).show()
        }
    }
    private fun updateReturnData(

        csNIC:String,
        csName:String,
        csEmail:String,
        csPhone:String,
        csCash:String,
        csDate:String
    ){

        val dbRef = FirebaseDatabase.getInstance().getReference("CashReturn").child(csNIC)
        val returnInfo = CashReturnModel(csNIC,csName,csEmail,csPhone,csCash,csDate)
        dbRef.setValue(returnInfo)
        val i = Intent(this@ActivityViewReturn, viewAllReturns::class.java)
        startActivity(i)

    }

}