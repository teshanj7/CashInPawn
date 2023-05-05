package com.example.pawningsystem.activities

import android.Manifest
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.Spinner
import android.widget.Toast
import androidx.core.app.ActivityCompat
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import com.example.pawningsystem.models.PawningModel
import com.example.pawningsystem.R
import com.google.android.material.textfield.TextInputEditText
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import java.text.SimpleDateFormat
import java.util.*

class ActivityCreatePawnings : AppCompatActivity() {

    //implementing the variables
    private lateinit var psFullName : TextInputEditText
    private lateinit var psNic : TextInputEditText
    private lateinit var psTeleNo: TextInputEditText
    private lateinit var psBankNo: TextInputEditText
    private lateinit var psAddress: TextInputEditText
    private lateinit var psPawningItem: Spinner
    private lateinit var psEstValue: TextInputEditText
    private lateinit var btnSubmitData: Button
    private lateinit var btnViewAllPawnings: Button

    //implementing the unique noti obj
    private companion object{
        private const val CHANNEL_ID = "channelPawn"
    }

    //implementing variable for the database
    private lateinit var dbRef: DatabaseReference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_createpawnings)

        psFullName = findViewById(R.id.psName)
        psNic = findViewById(R.id.psNicNo)
        psTeleNo = findViewById(R.id.psTeleNumber)
        psBankNo = findViewById(R.id.psBankAccNo)
        psAddress = findViewById(R.id.psPickAddress)
        psPawningItem = findViewById(R.id.spinner)
        psEstValue = findViewById(R.id.psEstimateVal)
        btnSubmitData = findViewById(R.id.btnSubmit)
        btnViewAllPawnings = findViewById(R.id.btnViewAll)

        //Database reference
        dbRef = FirebaseDatabase.getInstance().getReference("Pawnings")

        btnSubmitData.setOnClickListener {
            savePawningData()
            showNotification()
        }

        btnViewAllPawnings.setOnClickListener {
            val intent = Intent(this, ActivityViewPawnings::class.java)
            startActivity(intent)
        }

    }

    //notification function
    private fun showNotification() {

        createNotificationChannel()

        //unique id for the noti each time we click notifications
        val date = Date()
        val pawnNotificationId = SimpleDateFormat("ddHHmmss", Locale.US).format(date).toInt()

        //clicking notification and redirecting to another activity
        val mainIntent = Intent(this, ActivityViewPawnings::class.java)
        mainIntent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
        val mainPendingIntent = PendingIntent.getActivity(this, 1,mainIntent, PendingIntent.FLAG_IMMUTABLE)

        //notification builder
        val notificationBuilder = NotificationCompat.Builder(this, "$CHANNEL_ID")

        //notification icon
        notificationBuilder.setSmallIcon(R.drawable.ic_notification)
        //notification title
        notificationBuilder.setContentTitle("CashInPawn")
        //notification description
        notificationBuilder.setContentText("A new pawning request was submitted successfully!, view your pawning requests now.")
        //setting priority for notification
        notificationBuilder.priority = NotificationCompat.PRIORITY_DEFAULT

        //cancelling notification on click
        notificationBuilder.setAutoCancel(true)
        notificationBuilder.setContentIntent(mainPendingIntent)

        //notification manager
        val pawnNotificationManagerCompat = NotificationManagerCompat.from(this)

        if (ActivityCompat.checkSelfPermission(
                this,
                Manifest.permission.POST_NOTIFICATIONS
            ) != PackageManager.PERMISSION_GRANTED
        ) {
            return
        }
        pawnNotificationManagerCompat.notify(pawnNotificationId, notificationBuilder.build())




    }

    private fun createNotificationChannel(){
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.O){
            val name: CharSequence = "PawnNotification"
            val description = "Pawn notification channel description"
            //importance of the notification
            val importance = NotificationManager.IMPORTANCE_DEFAULT
            val notificationChannel = NotificationChannel(CHANNEL_ID, name, importance)

            notificationChannel.description = description

            val notificationManager = getSystemService(NOTIFICATION_SERVICE) as NotificationManager
            notificationManager.createNotificationChannel(notificationChannel)
        }
    }

    private fun savePawningData() {
        //setting values
        val pawnName = psFullName.text.toString()
        val pawnNic = psNic.text.toString()
        val pawnTeleNo = psTeleNo.text.toString()
        val pawnBank = psBankNo.text.toString()
        val pawnAddress = psAddress.text.toString()
        val pawnItem = psPawningItem.selectedItem.toString()
        val pawnValue = psEstValue.text.toString()

        //form validations
        if(pawnName.isEmpty()){
            psFullName.error = "Please enter full name!"
        }
        if(pawnNic.isEmpty()){
            psNic.error = "Please enter your NIC number!"
        }
        if(pawnTeleNo.isEmpty()){
            psTeleNo.error = "Please enter your telephone number!"
        }
        if(pawnBank.isEmpty()){
            psBankNo.error = "Please enter your Bank details!"
        }
        if(pawnAddress.isEmpty()){
            psBankNo.error = "Please enter the pickup address"
        }
        if(pawnValue.isEmpty()){
            psEstValue.error = "Please enter your estimated value"
        }

        //generating primary key (pawningID)
        val pawnId = dbRef.push().key!!

        //creating model
        val pawning = PawningModel(pawnId, pawnName, pawnNic, pawnTeleNo, pawnBank, pawnAddress, pawnItem, pawnValue)

        //putting data into DB
        dbRef.child(pawnId).setValue(pawning).addOnCompleteListener{
            Toast.makeText(this, "Pawning request submitted!", Toast.LENGTH_LONG).show()

            //clear fields after submit
            psFullName.text?.clear()
            psNic.text?.clear()
            psTeleNo.text?.clear()
            psBankNo.text?.clear()
            psAddress.text?.clear()
            psEstValue.text?.clear()

        }.addOnFailureListener { error->
            Toast.makeText(this, "Pawning request failed! ${error.message}", Toast.LENGTH_LONG).show()
        }
    }

}