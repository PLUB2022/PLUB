package com.example.socialproject.assign

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.viewpager2.widget.ViewPager2
import com.example.socialproject.R

class AssignActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_assign)

        var viewPager : ViewPager2 = findViewById(R.id.assignViewPager)
        val pagerAdapter = AssignAdapter(this)
        viewPager.adapter = pagerAdapter

        viewPager.isUserInputEnabled = false

    }
}