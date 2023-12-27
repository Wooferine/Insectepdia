package com.example.carbrowser
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.SearchView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.carbrowser.adapter.MainAdapter
import com.example.carbrowser.enty.RecyclerViewItems
import com.example.carbrowser.enty.SampleData
import com.example.carbrowser.functionalInterface.RecyclerInterface

class HomeScreen : AppCompatActivity(), RecyclerInterface {

    private var recyclerViewItems: ArrayList<RecyclerViewItems> = arrayListOf()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)
        Log.d("HomeScreen", "Search Bar")

//         Create search bar
        var searchView = findViewById<SearchView>(R.id.search_view)
        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                // Handle the search query
                val intent = Intent(this@HomeScreen, SearchScreen::class.java)
                intent.putExtra("QUERY", query)
                startActivity(intent)
                return true
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                // Handle query change
                return true
            }
        })

        // Create data type variable in object type SampleData
        SampleData.createInsectsModel(this)

//        Initialized adapter and recyclerview
        var mainAdapter: RecyclerView.Adapter<MainAdapter.insectsViewHolder>
        var recyclerView: RecyclerView

        createRecyclerViewItems()

        // Initializing and declaring adapter for both main and secondary recyclerview
        for(item in recyclerViewItems){
            mainAdapter = MainAdapter(item.mainLayout, item.dataset, this)
            recyclerView = findViewById(item.secondLayout)
            recyclerView.layoutManager = LinearLayoutManager(this, item.layoutManagerOrientation, false)
            recyclerView.adapter = mainAdapter
        }
    }

    fun createRecyclerViewItems(){
        recyclerViewItems.add(RecyclerViewItems(R.layout.item_rv_main_category, R.id.rv_main_category, SampleData.insectsClassification, LinearLayoutManager.HORIZONTAL))
        recyclerViewItems.add(RecyclerViewItems(R.layout.item_rv_secondary_category, R.id.rv_secondary_category, SampleData.popularSearch, LinearLayoutManager.VERTICAL))
    }

    override fun itemClick(any: Any) {
        var intent = Intent(this, CategoryScreen::class.java)
        intent.putExtra("NAME", recyclerViewItems[0].dataset[any as Int].insectsCommon)
        startActivity(intent)
    }
}

//594x420
//
//282,132