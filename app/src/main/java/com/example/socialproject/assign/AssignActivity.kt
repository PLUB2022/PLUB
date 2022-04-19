package com.example.socialproject.assign

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.appcompat.widget.AppCompatButton
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.viewpager2.widget.ViewPager2
import com.example.socialproject.R

class AssignActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_assign)

        var viewPager : ViewPager2 = findViewById(R.id.assignViewPager)
        var nextBtn : AppCompatButton = findViewById(R.id.nextButton1)
        val test = findViewById<ConstraintLayout>(R.id.gender_age_layout)
        Log.d("TAG", test.layoutParams.height.toString())
        val pagerAdapter = AssignAdapter(this)
        viewPager.adapter = pagerAdapter

        viewPager.isUserInputEnabled = false

        viewPager.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
            override fun onPageScrollStateChanged(state: Int) {  }

            override fun onPageScrolled(position: Int, positionOffset: Float, positionOffsetPixels: Int) { }

            override fun onPageSelected(position: Int) {
                if(position == 0){

                }
            }
        })

        nextBtn.setOnClickListener {
            var temp = viewPager.currentItem
            viewPager.setCurrentItem(temp + 1, true)
        }

    }
}