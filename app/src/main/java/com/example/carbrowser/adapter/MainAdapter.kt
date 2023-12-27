package com.example.carbrowser.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.carbrowser.enty.InsectsModel
import com.example.carbrowser.R
import com.example.carbrowser.functionalInterface.RecyclerInterface

class MainAdapter(var layout:Int, var insectsModel: ArrayList<InsectsModel>, var recyclerInterface: RecyclerInterface):
    RecyclerView.Adapter<MainAdapter.insectsViewHolder>(), RecyclerInterface {

    class insectsViewHolder(var view: View, var id: Int, recyclerInterface: RecyclerInterface):RecyclerView.ViewHolder(view){
        var imageView: ImageView = view.findViewById(R.id.imageView)
        var textView: TextView = view.findViewById(R.id.textView)
        var textView2: TextView? = null

        init{
            if(id.equals(R.layout.item_rv_secondary_category)){
                textView2 = view.findViewById(R.id.textView2)
            }else{
                // Item onclick for primary Recycler view only
                itemView.setOnClickListener(object: View.OnClickListener{
                    override fun onClick(v: View?) {
                        // Recycler View item onclick
                        recyclerInterface.itemClick(adapterPosition)
                    }
                })
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): insectsViewHolder {
        val layoutInflater: LayoutInflater = LayoutInflater.from(parent.context)
        val view: View = layoutInflater.inflate(layout, parent, false)
        return insectsViewHolder(view, layout, recyclerInterface)
    }

    override fun getItemCount(): Int {
        // return item list size
        return insectsModel.size
    }

    override fun onBindViewHolder(holder: insectsViewHolder, @SuppressLint("RecyclerView") position: Int) {
        // Binding textView2 if the layout is secondary Recycler view
        if(holder.textView2 != null) {
            holder.textView2!!.setText((insectsModel[position].insectsScientific))
        }
        holder.imageView.setImageResource(insectsModel[position].insectsImages)
        holder.textView.setText(insectsModel[position].insectsCommon)
    }

    override fun itemClick(any: Any) {
        // Empty, for implementation purpose
    }
}
