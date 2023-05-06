package com.example.pawningsystem.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.pawningsystem.R
import com.example.pawningsystem.models.CashReturnModel

class MyAdaptor(private val returnList:ArrayList<CashReturnModel>):
    RecyclerView.Adapter<MyAdaptor.MyViewHolder>() {

    var onItemClick : ((CashReturnModel) -> Unit)? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.single_cash_return, parent,false)
        return MyViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val currentItem = returnList[position]

        holder.nic.text = currentItem.csNIC
        holder.cashAmount.text = currentItem.cashAmount

        holder.itemView.setOnClickListener{
            onItemClick?.invoke(currentItem)
        }
    }
    override fun getItemCount(): Int {
        return returnList.size
    }

    class MyViewHolder(itemView: View):RecyclerView.ViewHolder(itemView){
        val nic :TextView = itemView.findViewById(R.id.nic)
        val cashAmount: TextView = itemView.findViewById(R.id.cash)

    }

}