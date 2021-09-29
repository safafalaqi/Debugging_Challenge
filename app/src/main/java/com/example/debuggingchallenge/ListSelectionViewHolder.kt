package com.example.debuggingchallenge

import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class ListSelectionViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    val listPosition: TextView = itemView.findViewById(R.id.tvCountry)
    val listTitle: TextView = itemView.findViewById(R.id.tvCabital)
}