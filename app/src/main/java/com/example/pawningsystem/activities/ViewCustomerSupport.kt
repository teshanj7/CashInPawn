package com.example.pawningsystem.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.pawningsystem.models.InquiryModel
import com.example.pawningsystem.R
import com.example.pawningsystem.adapters.InquiryAdapter
import com.google.firebase.database.*

class ViewCustomerSupport : AppCompatActivity() {

    private lateinit var inqRecyclerView: RecyclerView
    private lateinit var inqLoadingData: TextView
    private lateinit var inqLoadingData2: TextView
    private lateinit var inqList: ArrayList<InquiryModel>

    private lateinit var dbRef: DatabaseReference
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_view_customer_support)

        inqRecyclerView = findViewById(R.id.rvInquiries)
        inqRecyclerView.layoutManager = LinearLayoutManager(this)
        inqRecyclerView.setHasFixedSize(true)

        inqLoadingData = findViewById(R.id.tvloadingInquiries)
        inqLoadingData2 = findViewById(R.id.tvloadingInquiries2)

        inqList = arrayListOf<InquiryModel>()

        getInquiryData()
    }

    private fun getInquiryData() {

        inqRecyclerView.visibility = View.GONE
        inqLoadingData.visibility = View.VISIBLE
        inqLoadingData2.visibility = View.VISIBLE

        dbRef = FirebaseDatabase.getInstance().getReference("Inquiries")

        dbRef.addValueEventListener(object : ValueEventListener{
            override fun onDataChange(snapshot: DataSnapshot) {
               inqList.clear()
                if(snapshot.exists()){
                    for(inqSnap in snapshot.children){
                        val inqData = inqSnap.getValue(InquiryModel::class.java)
                        inqList.add(inqData!!)
                    }

                    val mAdapter = InquiryAdapter(inqList)
                    inqRecyclerView.adapter = mAdapter

                    mAdapter.setOnItemClickListener(object : InquiryAdapter.OnItemClickListener{
                        override fun onItemClick(position: Int) {
                            val intent = Intent(this@ViewCustomerSupport, InquiryDetails::class.java)

                            intent.putExtra("inquiryId", inqList[position].inquiryId)
                            intent.putExtra("iqFullName", inqList[position].iqFullName)
                            intent.putExtra("iqNic", inqList[position].iqNic)
                            intent.putExtra("iqTelephone", inqList[position].iqTelephone)
                            intent.putExtra("iqSubjectOfMatter", inqList[position].iqSubjectOfMatter)
                            intent.putExtra("iqMessage", inqList[position].iqMessage)
                            startActivity(intent)
                        }

                    })

                    inqRecyclerView.visibility = View.VISIBLE
                    inqLoadingData.visibility = View.GONE
                    inqLoadingData2.visibility = View.GONE
                }
            }

            override fun onCancelled(error: DatabaseError) {
                TODO("Not yet implemented")
            }
        })
    }
}