package com.example.socialproject.mapTest

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import com.example.socialproject.R

class MapTestActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_map_test)

        val test_map_btn = findViewById<TextView>(R.id.search_map_btn)

        test_map_btn.setOnClickListener {
            val intent = Intent(this, NaverMapTestActivity::class.java)
            startActivity(intent)
        }
    }
}