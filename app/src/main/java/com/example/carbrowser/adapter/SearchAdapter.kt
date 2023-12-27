package com.example.carbrowser.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.TextView
import com.example.carbrowser.R
import com.example.carbrowser.enty.Profile
import com.example.carbrowser.functionalInterface.RecyclerInterface

class SearchAdapter(context: Context, allProfile: List<Profile>, var recyclerInterface: RecyclerInterface):
    ArrayAdapter<Profile>(context, 0, allProfile), RecyclerInterface {

        override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
            val profile = getItem(position)!!

            var itemView = convertView
            if (itemView == null) {
                itemView = LayoutInflater.from(context).inflate(R.layout.item_search_list, parent, false)
            }

            // Binding name to textView on search item
            val textView = itemView!!.findViewById<TextView>(R.id.searchText)
            textView.text = profile.common

            // Click on the result to enter the read profile page
            itemView.setOnClickListener{
                recyclerInterface.itemClick(profile)
            }
            return itemView
        }

    override fun itemClick(any: Any) {
        // Empty, for implementation purpose
    }
}