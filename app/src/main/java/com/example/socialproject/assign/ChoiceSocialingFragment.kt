package com.example.socialproject.assign

import android.os.Bundle
import android.os.Handler
import android.os.PersistableBundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import androidx.appcompat.widget.AppCompatButton
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.widget.ViewPager2
import com.example.socialproject.R

class ChoiceSocialingFragment(var assignActivity: AssignActivity) : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View = inflater.inflate(R.layout.choice_socialing_assign, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        var next = view.findViewById<AppCompatButton>(R.id.choice_socialing_next_btn)
        var socialing_assign_category1 = view.findViewById<ConstraintLayout>(R.id.category_layout1)
        var socialing_assign_category2 = view.findViewById<ConstraintLayout>(R.id.category_layout2)
        var socialing_assign_category3 = view.findViewById<ConstraintLayout>(R.id.category_layout3)
        var socialing_assign_category4 = view.findViewById<ConstraintLayout>(R.id.category_layout4)
        var socialing_assign_category5 = view.findViewById<ConstraintLayout>(R.id.category_layout5)
        var socialing_assign_category6 = view.findViewById<ConstraintLayout>(R.id.category_layout6)
        var category_lists = arrayListOf<ConstraintLayout>(socialing_assign_category1, socialing_assign_category2, socialing_assign_category3, socialing_assign_category4, socialing_assign_category5, socialing_assign_category6)
        for(i in 0..5){
            category_lists[i].setOnClickListener {
                assignActivity.clickPage()
            }
        }
        var fadeinani : Animation = AnimationUtils.loadAnimation(context, R.anim.fade_in)
        var fadeoutani : Animation = AnimationUtils.loadAnimation(context, R.anim.fade_out)
        val layoutview = view?.findViewById<View>(R.id.choice_socialing_layout)
        if (layoutview != null) {
            layoutview.startAnimation(fadeinani)
        }
        next.setOnClickListener {
            if (layoutview != null) {
                layoutview.startAnimation(fadeoutani)
            }
            Handler().postDelayed({
                assignActivity.goNextPage()
            },200)
        }
    }

    override fun onResume() {
        super.onResume()
//        val layoutHeight = view?.findViewById<ConstraintLayout>(R.id.gender_age_layout)
//        assignActivity.resizeViewPager(layoutHeight!!.layoutParams.height)
        val layoutview = view?.findViewById<View>(R.id.choice_socialing_layout)
        if (layoutview != null) {
            layoutview.requestLayout()
        }
    }
}