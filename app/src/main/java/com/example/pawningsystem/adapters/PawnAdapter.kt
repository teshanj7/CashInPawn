package com.example.pawningsystem.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.pawningsystem.R
import com.example.pawningsystem.models.PawningModel

class PawnAdapter( private val pawnList: ArrayList<PawningModel>) : RecyclerView.Adapter<PawnAdapter.ViewHolder>(){

    private lateinit var mListener: OnItemClickListener

    interface OnItemClickListener{
        fun onItemClick(position: Int)
    }

    fun setOnItemClickListener(clickListener: OnItemClickListener){
        mListener = clickListener
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {

        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.pawning_list_item, parent, false)
        return ViewHolder(itemView, mListener)

    }

    override fun onBindViewHolder(holder: PawnAdapter.ViewHolder, position: Int) {

        val currentPawning = pawnList[position]
        holder.tvPawningItem.text = currentPawning.psPawnItem
    }

    override fun getItemCount(): Int {
        return pawnList.size
    }

    class ViewHolder(itemView: View, clickListener: OnItemClickListener) : RecyclerView.ViewHolder(itemView) {

        val tvPawningItem: TextView = itemView.findViewById(R.id.tvPawningItem)

        init {
            itemView.setOnClickListener{
                clickListener.onItemClick(adapterPosition)
            }
        }
    }


}