package com.example.carbrowser

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SwitchCompat
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.carbrowser.adapter.CategoryAdapter
import com.example.carbrowser.functionalInterface.RecyclerInterface
import com.example.carbrowser.enty.Profile
import com.example.carbrowser.viewModel.ProfileViewModel

class CategoryScreen : AppCompatActivity(), RecyclerInterface {

    private lateinit var profileViewModel: ProfileViewModel

    @SuppressLint("UseSwitchCompatOrMaterialCode")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_category)

        // Change title
        var currentPageCategory: String? = intent.getStringExtra("NAME")
        findViewById<TextView>(R.id.textTitle).setText(currentPageCategory)


        var switch = findViewById<SwitchCompat>(R.id.switch1)

        // Declare categoryAdapter as an instance of CategoryAdapter
        var categoryAdapter = CategoryAdapter(switch,this, currentPageCategory!!)

        // Declare and set up recyclerView
        var recyclerView = findViewById<RecyclerView>(R.id.rv_quaternary_category)

        recyclerView.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        recyclerView.adapter = categoryAdapter

//        Initializing view model
        profileViewModel = ViewModelProvider(
            this,
            ViewModelProvider.AndroidViewModelFactory.getInstance(application)
        ).get(ProfileViewModel::class.java)

//         Update adminAdapter's allProfile with view model
        profileViewModel.allProfile.observe(this) { list ->
            list?.let {
                // on below line, we are updating our list.
                categoryAdapter.updateProfile(it)
                categoryAdapter.notifyDataSetChanged()
            }
        }
    }

    override fun itemClick(any: Any) {
        var temp = any as Profile

        // Parsing variables
        val intent = Intent(this, AdminReadProfileScreen::class.java)
        intent.putExtra("Common", temp.common)
        intent.putExtra("Scientific", temp.scientific)
        intent.putExtra("Category", temp.category)
        intent.putExtra("LifeSpan", temp.lifeSpan)
        intent.putExtra("Description", temp.description)
        intent.putExtra("Type", "Edit")
        startActivity(intent)
    }
}