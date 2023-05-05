package com.example.pawningsystem.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.pawningsystem.R
import com.example.pawningsystem.models.InquiryModel


class InquiryAdapter(private val inqList: ArrayList<InquiryModel>) : RecyclerView.Adapter<InquiryAdapter.ViewHolder>(){

    private lateinit var mListener: OnItemClickListener

    interface OnItemClickListener{
        fun onItemClick(position: Int)
    }

    fun setOnItemClickListener(clickListener: OnItemClickListener){
        mListener = clickListener
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {

        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.inquiry_list_item, parent, false)
        return ViewHolder(itemView, mListener)
    }

    override fun onBindViewHolder(holder: InquiryAdapter.ViewHolder, position: Int) {

        val currentInquiry = inqList[position]
        holder.tvInquiry.text = currentInquiry.iqSubjectOfMatter

    }

    override fun getItemCount(): Int {
        return inqList.size
    }
    class ViewHolder(itemView: View, clickListener: OnItemClickListener) : RecyclerView.ViewHolder(itemView){

        val tvInquiry: TextView = itemView.findViewById(R.id.tvInquirySubject)

        init{
            itemView.setOnClickListener {
                clickListener.onItemClick(adapterPosition)
            }
        }
    }

}