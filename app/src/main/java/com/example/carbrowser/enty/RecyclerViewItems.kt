package com.example.carbrowser.enty

data class RecyclerViewItems(
    var mainLayout: Int,
    var secondLayout: Int,
    var dataset: ArrayList<InsectsModel>,
    var layoutManagerOrientation: Int
)
