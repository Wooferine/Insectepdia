package com.example.carbrowser.adapter

import android.annotation.SuppressLint
import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.example.carbrowser.R
import com.example.carbrowser.enty.Profile
import com.example.carbrowser.functionalInterface.DeleteProfile
import com.example.carbrowser.functionalInterface.RecyclerInterface
import kotlin.random.Random


class AdminAdapter(var context: Context, var recyclerInterface: RecyclerInterface, var deleteProfile: DeleteProfile):
    RecyclerView.Adapter<AdminAdapter.ProfileViewHolder>(), RecyclerInterface, DeleteProfile {

    private var allProfile: ArrayList<Profile> = arrayListOf()
    private var colorList: ArrayList<Int> = arrayListOf(
        R.color.honey_crisp, R.color.honey_hive, R.color.honey_dense,
        R.color.honey_den, R.color.ColorPrimary, R.color.ColorPrimaryDark,
        R.color.ColorAccent
    )

    class ProfileViewHolder(view: View): RecyclerView.ViewHolder(view){
        var textView = view.findViewById<TextView>(R.id.textView)
        var textView2 = view.findViewById<TextView>(R.id.textView2)
        var deleteButton = view.findViewById<ImageView>(R.id.deleteBtn)
        val background: View = view.findViewById(R.id.adminCardView)
        val context: Context = view.context
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProfileViewHolder{
        Log.d("AdminAdapter", "Oncreate view holder")

        // Intialized layout for recyclerview item
        val layoutInflater = LayoutInflater.from(parent.context)
        val view: View = layoutInflater.inflate(R.layout.item_rv_tertiary_category, parent, false)
        return ProfileViewHolder(view)
    }

    override fun getItemCount(): Int {
        return allProfile.size
    }

    override fun onBindViewHolder(holder: ProfileViewHolder, @SuppressLint("RecyclerView") position: Int) {
        // Binding value to views
        holder.textView.setText(allProfile[position].common)
        holder.textView2.setText(allProfile[position].description)

        // Programmatically set view background color
        val root = holder.background.rootView
        root.setBackgroundColor(ContextCompat.getColor(holder.context, colorList[Random.nextInt(0, 6)]))

        // Recycler view item onclick
        holder.itemView.setOnClickListener(object: View.OnClickListener{
            override fun onClick(v: View?) {
                recyclerInterface.itemClick(allProfile[position])
            }
        })

        // Delete profile onclick
        holder.deleteButton.setOnClickListener{
            deleteProfile.onDeleteProfile(allProfile[position])
        }
    }

    override fun itemClick(any: Any) {
        // Empty, for implementation purpose
    }

    fun updateProfile(profile: List<Profile>){
        // Clear the profile to avoid duplicate data
        allProfile.clear()
        allProfile.addAll(profile)
    }

    override fun onDeleteProfile(profile: Profile) {
        // Empty, for implementation purpose
    }
}