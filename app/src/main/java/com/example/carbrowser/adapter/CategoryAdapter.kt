package com.example.carbrowser.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.widget.SwitchCompat
import androidx.recyclerview.widget.RecyclerView
import com.example.carbrowser.R
import com.example.carbrowser.enty.Profile
import com.example.carbrowser.enty.SampleData
import com.example.carbrowser.functionalInterface.RecyclerInterface


class CategoryAdapter(var switch: SwitchCompat, var recyclerInterface: RecyclerInterface, var category: String):
    RecyclerView.Adapter<CategoryAdapter.ProfileViewHolder>(), RecyclerInterface{

    private var allProfile: MutableList<Profile> = mutableListOf()
    private var filteredProfiles: List<Profile> = listOf()

    class ProfileViewHolder(view: View): RecyclerView.ViewHolder(view){
        var textView = view.findViewById<TextView>(R.id.textView)
        var textView2 = view.findViewById<TextView>(R.id.textView2)
        var imageView: ImageView = view.findViewById(R.id.imageView)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProfileViewHolder{
        val layoutInflater = LayoutInflater.from(parent.context)
        val view: View = layoutInflater.inflate(R.layout.item_rv_secondary_category, parent, false)
        return ProfileViewHolder(view)
    }

    override fun getItemCount(): Int {
        return filteredProfiles.size
    }

    override fun onBindViewHolder(holder: ProfileViewHolder, @SuppressLint("RecyclerView") position: Int) {
        // Filter out profile corresponding to current page category
        filteredProfiles = allProfile.filter { profile ->
            profile.category == category
        }
        // Set switch to sort the profile
        switch.setOnCheckedChangeListener { _, isChecked ->
            if (isChecked) {
                allProfile.sortBy {
                    it.common
                }
            } else {
                allProfile.sortByDescending {
                    it.common
                }
            }
            notifyDataSetChanged()
        }

        // Binding value to views
        holder.textView.setText(filteredProfiles[position].common)
        holder.textView2.setText(filteredProfiles[position].scientific)
        holder.imageView.setImageResource(filteredProfiles[position].images)
        holder.itemView.setOnClickListener(object: View.OnClickListener{
            override fun onClick(v: View?) {

                // Recycler View item onclick
                recyclerInterface.itemClick(filteredProfiles[position])
            }
        })
    }

    override fun itemClick(any: Any) {
        // Empty, for implementation purpose
    }

    fun updateProfile(profile: List<Profile>){

        // Clearing list to prevent duplicate data
        allProfile.clear()
        allProfile.addAll(profile)

        // Merging Profile from another data class
        for(item in SampleData.insectsStats){
            allProfile.add(
                Profile(
                    item.insectsCommon,
                    item.insectsScientific,
                    item.insectsCategory,
                    Integer.parseInt(item.insectsLifeSpan),
                    item.insectsDescription,
                    item.insectsImages)
            )
        }

        // Filter logic to retrieve profile with same category
        filteredProfiles = allProfile.filter { profile ->
            profile.category == category
        }
    }
}