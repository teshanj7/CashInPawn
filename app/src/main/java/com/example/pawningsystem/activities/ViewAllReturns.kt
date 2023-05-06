package com.example.pawningsystem.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.pawningsystem.R
import com.example.pawningsystem.adapters.MyAdaptor
import com.example.pawningsystem.models.CashReturnModel
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.*

class viewAllReturns : AppCompatActivity() {

    private lateinit var firebaseAuth: FirebaseAuth//authentication

    private lateinit var dhref: DatabaseReference
    private lateinit var returnRecyclerView: RecyclerView
    private lateinit var returnList: ArrayList<CashReturnModel>

    private lateinit var returnAdapter: MyAdaptor

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_view_all_returns)

        //auth using email
        firebaseAuth = FirebaseAuth.getInstance()
        val currentUser = firebaseAuth.currentUser
        val useremail = currentUser?.email


        returnRecyclerView = findViewById(R.id.viewAll)//recycler view List
        returnRecyclerView.layoutManager = LinearLayoutManager(this)
        returnRecyclerView.setHasFixedSize(true)

        returnList = arrayListOf<CashReturnModel>()
        getReturnData(useremail)

    }

    private fun getReturnData(
        useremail: String?
    ) {

        dhref = FirebaseDatabase.getInstance().getReference("CashReturn")
        dhref.orderByChild("email").equalTo(useremail).addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                if (snapshot.exists()) {

                    for (userSnapshot in snapshot.children) {

                        val ret = userSnapshot.getValue(CashReturnModel::class.java)
                        returnList.add(ret!!)
                    }

                    returnAdapter = MyAdaptor(returnList)
                    returnRecyclerView.adapter = MyAdaptor(returnList)
                    returnRecyclerView.adapter = returnAdapter

                    returnAdapter.onItemClick = {
                        val intent = Intent(this@viewAllReturns, ActivityViewReturn::class.java )

                        intent.putExtra("fullName",it)
                        intent.putExtra("email",it)
                        intent.putExtra("phone",it)
                        intent.putExtra("csNIC",it)
                        intent.putExtra("cashAmount",it)
                        intent.putExtra("dateToCollect",it)
                        intent.putExtra("csID",it)
                        startActivity(intent)
                    }
                }
            }

            override fun onCancelled(error: DatabaseError) {
                TODO("Not yet implemented")
            }
        })
    }

}