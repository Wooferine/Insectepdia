package com.example.carbrowser
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ListView
import android.widget.TextView
import androidx.lifecycle.ViewModelProvider
import com.example.carbrowser.adapter.SearchAdapter
import com.example.carbrowser.enty.Profile
import com.example.carbrowser.enty.SampleData
import com.example.carbrowser.functionalInterface.RecyclerInterface
import com.example.carbrowser.viewModel.ProfileViewModel

class SearchScreen : AppCompatActivity(), RecyclerInterface {

    private lateinit var profileViewModel: ProfileViewModel
    private var searchList: ArrayList<Profile> = arrayListOf()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_search)

        // Parse QUERY search from HomeScreen
        val searchQuery = intent.getStringExtra("QUERY")
        findViewById<TextView>(R.id.textTitle).setText("Search: ${searchQuery}")
        val listView = findViewById<ListView>(R.id.searchList)

        // Initializing view model
        profileViewModel = ViewModelProvider(
            this,
            ViewModelProvider.AndroidViewModelFactory.getInstance(application)
        ).get(ProfileViewModel::class.java)

        // Transfer all profle from database to searchList to filter
        profileViewModel.allProfile.observe(this) { list ->
            list?.let {

                // Clear and merge data from database and sample data
                searchList.clear()
                searchList.addAll(it)
                SampleData.insectsStats.map {
                    searchList.add(
                        Profile(
                            it.insectsCommon,
                            it.insectsScientific,
                            it.insectsCategory,
                            Integer.parseInt(it.insectsLifeSpan),
                            it.insectsDescription,
                            it.insectsImages)
                    )
                }

                // Filter the profile with common name contains query character
                // Set case insensitive
                val filteredProfiles = searchList.filter {
                    val contains = it.common.contains(searchQuery!!, ignoreCase = true)
                    contains
                }

                // Setting up adapter
                val adapter = SearchAdapter(this, filteredProfiles, this)
                listView.adapter = adapter
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