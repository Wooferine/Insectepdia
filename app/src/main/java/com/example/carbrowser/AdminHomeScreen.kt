package com.example.carbrowser

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.carbrowser.adapter.AdminAdapter
import com.example.carbrowser.enty.Profile
import com.example.carbrowser.functionalInterface.DeleteProfile
import com.example.carbrowser.functionalInterface.RecyclerInterface
import com.example.carbrowser.viewModel.ProfileViewModel
import com.google.android.material.floatingactionbutton.FloatingActionButton

class AdminHomeScreen : AppCompatActivity(), RecyclerInterface, DeleteProfile {

    private lateinit var profileViewModel: ProfileViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_admin_home)

        // Setupt recycler view and corresponding adapter
        var recyclerView: RecyclerView
        var adminAdapter: RecyclerView.Adapter<AdminAdapter.ProfileViewHolder>

        // Initializing and declaring recyclerview and adapter
        adminAdapter = AdminAdapter(this, this, this)
        recyclerView = findViewById(R.id.rv_tertiary_category)
        recyclerView.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        recyclerView.setHasFixedSize(true)
        recyclerView.adapter = adminAdapter

        // Initializing view model
        profileViewModel = ViewModelProvider(
            this,
            ViewModelProvider.AndroidViewModelFactory.getInstance(application)
        ).get(ProfileViewModel::class.java)

        // Update adminAdapter's allProfile with view model
        profileViewModel.allProfile.observe(this) { list ->
            list?.let {
                // Update the adapter profile list with database profile
                adminAdapter.updateProfile(it)
                adminAdapter.notifyDataSetChanged()
            }
        }

        val floatingActionButton = findViewById<FloatingActionButton>(R.id.addBtn)
        floatingActionButton.bringToFront()
        floatingActionButton.setOnClickListener {
                    var intent = Intent(this, AdminAddProfileScreen::class.java)
                    startActivity(intent)
                    finish()
        }
    }

    override fun itemClick(any: Any) {
        var temp = any as Profile
        val intent = Intent(this, AdminReadProfileScreen::class.java)

        // Parsing value to subsequent activiyty
        intent.putExtra("Common", temp.common)
        intent.putExtra("Scientific", temp.scientific)
        intent.putExtra("Category", temp.category)
        intent.putExtra("LifeSpan", temp.lifeSpan)
        intent.putExtra("Description", temp.description)
        intent.putExtra("Type", "Edit")
        startActivity(intent)
    }

    override fun onDeleteProfile(profile: Profile) {
        // Delete profile
        profileViewModel.deleteProfile(profile)
        Toast.makeText(this, "Profile is removed from database.", Toast.LENGTH_SHORT).show()
    }
}