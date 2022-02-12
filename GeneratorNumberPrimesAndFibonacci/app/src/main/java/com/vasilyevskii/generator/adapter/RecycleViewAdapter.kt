package com.vasilyevskii.generator.adapter

import android.graphics.Color.*
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.vasilyevskii.generator.R

class RecycleViewAdapter : RecyclerView.Adapter<RecycleViewAdapter.RecycleViewHolder>() {

    var listPrime: List<String> = emptyList()
    set(newValue){
        field = newValue
        notifyDataSetChanged()
    }

    var numberLine = 0

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecycleViewHolder {
        val contextView = parent.context
        val layoutIdForListItem = R.layout.number_list_item

        val inflater = LayoutInflater.from(contextView)
        val view = inflater.inflate(layoutIdForListItem, parent, false)

        return RecycleViewHolder(view)
    }

    override fun onBindViewHolder(holder: RecycleViewHolder, position: Int) {
        numberLine = position / 2

        if (position % 2 == 0) {
           if(numberLine % 2 == 0) {
              holder.cardViewItem.setBackgroundColor(GRAY)
           }else {
              holder.cardViewItem.setBackgroundColor(WHITE)
           }
        } else {
           if(numberLine % 2 == 0) {
              holder.cardViewItem.setBackgroundColor(WHITE)
           }else {
              holder.cardViewItem.setBackgroundColor(GRAY)
           }
        }

        holder.bind(listPrime[position])
    }

    override fun getItemCount(): Int = listPrime.size

    class RecycleViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        val cardViewItem: CardView = itemView.findViewById(R.id.card_view_item)
        private val numberView: TextView = itemView.findViewById(R.id.number)

        fun bind(number: String) {
            numberView.text = number
        }

    }


}