package com.example.carbrowser

import android.annotation.SuppressLint
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity


class AdminReadProfileScreen : AppCompatActivity() {
    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_admin_read_profile)

        // Parse value from previous activity
        findViewById<TextView>(R.id.scientific).text = "Scientific: ${intent.getStringExtra("Scientific")}"
        findViewById<TextView>(R.id.common).text = "Common: ${intent.getStringExtra("Common")}"
        findViewById<TextView>(R.id.category).text = "Category:  ${intent.getStringExtra("Category")}"
        findViewById<TextView>(R.id.lifeSpan).text = "LifeSpan:  ${(intent.getIntExtra("LifeSpan", 0).toString())} months"
        findViewById<TextView>(R.id.detail).text = "${intent.getStringExtra("Description")}"

        findViewById<ImageView>(R.id.backBtn).setOnClickListener{
            finish()
        }

    }
}