package com.example.debuggingchallenge

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.list_selection_view_holder.view.*

class ListSelectionRecyclerViewAdapter(private val stateAndCapitals: ArrayList<ArrayList<String>>) :
    RecyclerView.Adapter<ListSelectionViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListSelectionViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.list_selection_view_holder, parent, false)

        return ListSelectionViewHolder(view)
    }

    override fun onBindViewHolder(holder: ListSelectionViewHolder, position: Int) {
        val country=stateAndCapitals[position][0]
        val capital=stateAndCapitals[position][1]

        holder.itemView.apply{
           tvCountry.text=country
           tvCabital.text=capital
        }

    }

    override fun getItemCount(): Int {
        return stateAndCapitals.size
    }

}