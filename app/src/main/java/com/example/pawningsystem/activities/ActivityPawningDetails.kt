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
import com.example.pawningsystem.models.PawningModel
import com.google.firebase.database.FirebaseDatabase

class ActivityPawningDetails : AppCompatActivity() {

    private lateinit var tvPawnID : TextView
    private lateinit var tvPawnName: TextView
    private lateinit var tvPawnNIC: TextView
    private lateinit var tvPawnTeleNo: TextView
    private lateinit var tvPawnBankNo: TextView
    private lateinit var tvPawnPickAdd: TextView
    private lateinit var tvPawnItem: TextView
    private lateinit var tvPawnEstValue: TextView

    private lateinit var btnUpdate: Button
    private lateinit var btnDelete: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_pawning_details)

        initView()
        setValuesToViews()

        btnUpdate.setOnClickListener {
            openUpdateDialog(
                intent.getStringExtra("psID").toString(),
                intent.getStringExtra("psName").toString(),
                intent.getStringExtra("psPawnItem").toString()
            )
        }

        btnDelete.setOnClickListener {
            deletePawning(
                intent.getStringExtra("psID").toString()
            )
        }

    }

    private fun deletePawning(
        id: String ){
        val dbRef = FirebaseDatabase.getInstance().getReference("Pawnings").child(id)
        val mTask = dbRef.removeValue()

        mTask.addOnSuccessListener {
            Toast.makeText(this, "Pawning request deleted!", Toast.LENGTH_LONG).show()

            val intent = Intent(this, ActivityViewPawnings::class.java)
            finish()
            startActivity(intent)
        }.addOnFailureListener{ error->
            Toast.makeText(this, "Pawning request deletion failed! ${error.message}", Toast.LENGTH_LONG).show()
            val intent = Intent(this, ActivityViewPawnings::class.java)
            finish()
            startActivity(intent)

        }

    }

    //initializing the views
    private fun initView() {
        tvPawnID = findViewById(R.id.tvPawnId)
        tvPawnName = findViewById(R.id.tvPawnFullName)
        tvPawnNIC = findViewById(R.id.tvPawnNic)
        tvPawnTeleNo = findViewById(R.id.tvPawnTeleNo)
        tvPawnBankNo = findViewById(R.id.tvPawnBank)
        tvPawnPickAdd = findViewById(R.id.tvPawnAddress)
        tvPawnItem = findViewById(R.id.tvPawnItemData)
        tvPawnEstValue = findViewById(R.id.tvPawnValue)

        //buttons
        btnUpdate = findViewById(R.id.btnUpdatePAWN)
        btnDelete = findViewById(R.id.btnDeletePAWN)
    }

    //setting the data into the variables
    private fun setValuesToViews(){

        tvPawnID.text =  intent.getStringExtra("psID")
        tvPawnName.text = intent.getStringExtra("psName")
        tvPawnNIC.text = intent.getStringExtra("psNIC")
        tvPawnTeleNo.text = intent.getStringExtra("psTeleNo")
        tvPawnBankNo.text = intent.getStringExtra("psBankNo")
        tvPawnPickAdd.text = intent.getStringExtra("psAddress")
        tvPawnItem.text = intent.getStringExtra("psPawnItem")
        tvPawnEstValue.text = intent.getStringExtra("psEstValue")
    }

    //update dialog
    private fun openUpdateDialog(
        pawnId: String,
        pawnName: String,
        pawnItem: String
    ){
        val mDialog = AlertDialog.Builder(this)
        val inflater = layoutInflater
        //setting the dialog into a variable
        val mDialogView = inflater.inflate(R.layout.pawningsystem_updatedialog, null)

        mDialog.setView(mDialogView)

        //intializing views of the dialog
        val etPawnName = mDialogView.findViewById<EditText>(R.id.etPawnName)
        val etPawnNic = mDialogView.findViewById<EditText>(R.id.etPawnNic)
        val etPawnTeleNo = mDialogView.findViewById<EditText>(R.id.etPawnTeleNo)
        val etPawnBank = mDialogView.findViewById<EditText>(R.id.etPawnBank)
        val etPawnAddress = mDialogView.findViewById<EditText>(R.id.etPawnAddress)
        val etPawnItem = mDialogView.findViewById<EditText>(R.id.etPawnItem)
        val etPawnEstValue = mDialogView.findViewById<EditText>(R.id.etPawnEstValue)

        val btnUpdateData = mDialogView.findViewById<Button>(R.id.btnUpdateData)

        //populating data into the variable

        etPawnName.setText(intent.getStringExtra("psName").toString())
        etPawnNic.setText(intent.getStringExtra("psNIC").toString())
        etPawnTeleNo.setText(intent.getStringExtra("psTeleNo").toString())
        etPawnBank.setText(intent.getStringExtra("psBankNo").toString())
        etPawnAddress.setText(intent.getStringExtra("psAddress").toString())
        etPawnItem.setText(intent.getStringExtra("psPawnItem").toString())
        etPawnEstValue.setText(intent.getStringExtra("psEstValue").toString())

        mDialog.setTitle("Updating $pawnName 's $pawnItem request")

        val alertDialog = mDialog.create()
        alertDialog.show()

        btnUpdateData.setOnClickListener {
            updatePawningData(
                pawnId,
                etPawnName.text.toString(),
                etPawnNic.text.toString(),
                etPawnTeleNo.text.toString(),
                etPawnBank.text.toString(),
                etPawnAddress.text.toString(),
                etPawnItem.text.toString(),
                etPawnEstValue.text.toString()
            )

            Toast.makeText(applicationContext, "Pawning Record updated!", Toast.LENGTH_LONG).show()

            //setting updated data to textviews
            tvPawnName.text = etPawnName.text.toString()
            tvPawnNIC.text = etPawnNic.text.toString()
            tvPawnTeleNo.text = etPawnTeleNo.text.toString()
            tvPawnBankNo.text = etPawnBank.text.toString()
            tvPawnPickAdd.text = etPawnAddress.text.toString()
            tvPawnEstValue.text = etPawnEstValue.text.toString()

            alertDialog.dismiss()
        }

    }

    private fun updatePawningData(
        id:String,
        name:String,
        nic:String,
        teleNo: String,
        bankDeets: String,
        address: String,
        item: String,
        value: String
    ){
        val dbRef = FirebaseDatabase.getInstance().getReference("Pawnings").child(id)
        val pawnInfo = PawningModel(id, name, nic, teleNo, bankDeets, address, item,value)

        dbRef.setValue(pawnInfo)


    }
}