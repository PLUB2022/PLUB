package com.example.socialproject.assign

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.appcompat.widget.AppCompatButton
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.view.get
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.widget.ViewPager2
import com.example.socialproject.R
import com.tbuonomo.viewpagerdotsindicator.DotsIndicator

class AssignActivity : AppCompatActivity() {
    lateinit var viewPager : ViewPager2
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_assign)

        viewPager = findViewById(R.id.assignViewPager)
        val assignIndicator = findViewById<DotsIndicator>(R.id.assignIndicator)
        val pagerAdapter = AssignAdapter(this, this)
        viewPager.adapter = pagerAdapter
        assignIndicator.setViewPager2(viewPager)

        viewPager.isUserInputEnabled = false

        viewPager.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
            override fun onPageScrollStateChanged(state: Int) {  }

            override fun onPageScrolled(position: Int, positionOffset: Float, positionOffsetPixels: Int) { }

            override fun onPageSelected(position: Int) {
                super.onPageSelected(position)
                Log.d("TAg", position.toString() + " " + viewPager.height)

            }
        })


    }

    fun nextPage(){
        var temp = viewPager.currentItem
        viewPager.setCurrentItem(temp + 1, false)
    }

    fun goNextPage(){
        var temp = viewPager.currentItem
        viewPager.setCurrentItem(temp + 2, false)
    }

    fun clickPage(){
        var temp = viewPager.currentItem
        viewPager.setCurrentItem(temp + 1, true)
    }

    fun prevPage(){
        var temp = viewPager.currentItem
        viewPager.setCurrentItem(temp - 1, true)
    }
}