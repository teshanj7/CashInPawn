package com.example.pawningsystem.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.pawningsystem.R
import com.example.pawningsystem.adapters.PawnAdapter
import com.example.pawningsystem.models.PawningModel
import com.google.firebase.database.*

class ActivityViewPawnings : AppCompatActivity(){

    private lateinit var pawnRecyclerView: RecyclerView
    private lateinit var pawnLoadingData: TextView
    private lateinit var pawnLoadingData2: TextView
    private lateinit var pawnList: ArrayList<PawningModel>

    //db reference
    private lateinit var dbRef: DatabaseReference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_viewpawnings)

        pawnRecyclerView = findViewById(R.id.rvPawnings)
        pawnRecyclerView.layoutManager =  LinearLayoutManager(this)
        pawnRecyclerView.setHasFixedSize(true)

        //loading screen initializing
        pawnLoadingData = findViewById(R.id.tvloadingPawnings)
        pawnLoadingData2 = findViewById(R.id.tvloadingPawnings2)

        pawnList = arrayListOf<PawningModel>()

        getPawningData()

    }

    private fun getPawningData() {

        pawnRecyclerView.visibility = View.GONE
        pawnLoadingData.visibility = View.VISIBLE
        pawnLoadingData2.visibility = View.VISIBLE

        dbRef = FirebaseDatabase.getInstance().getReference("Pawnings")

        //getting data
        dbRef.addValueEventListener(object : ValueEventListener{
            override fun onDataChange(snapshot: DataSnapshot) {
                pawnList.clear()
                if(snapshot.exists()){
                    for(pawnSnap in snapshot.children){
                        val pawnData = pawnSnap.getValue(PawningModel::class.java)
                        pawnList.add(pawnData!!)
                    }
                    val mAdapter = PawnAdapter(pawnList)
                    pawnRecyclerView.adapter = mAdapter

                    mAdapter.setOnItemClickListener(object : PawnAdapter.OnItemClickListener{
                        override fun onItemClick(position: Int) {
                            val intent = Intent(this@ActivityViewPawnings, ActivityPawningDetails::class.java)

                            //put extras
                            intent.putExtra("psID", pawnList[position].psId)
                            intent.putExtra("psName", pawnList[position].psName)
                            intent.putExtra("psNIC", pawnList[position].psNIC)
                            intent.putExtra("psTeleNo", pawnList[position].psTeleNo)
                            intent.putExtra("psBankNo", pawnList[position].psBankNo)
                            intent.putExtra("psAddress", pawnList[position].psAddress)
                            intent.putExtra("psPawnItem", pawnList[position].psPawnItem)
                            intent.putExtra("psEstValue", pawnList[position].psEstValue)
                            startActivity(intent)
                        }

                    })

                    pawnRecyclerView.visibility = View.VISIBLE
                    pawnLoadingData.visibility = View.GONE
                    pawnLoadingData2.visibility = View.GONE
                }
            }

            override fun onCancelled(error: DatabaseError) {
                TODO("Not yet implemented")
            }

        })
    }
}