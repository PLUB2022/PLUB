package com.example.socialproject.assign

import android.os.Bundle
import android.os.PersistableBundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.AppCompatButton
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.widget.ViewPager2
import com.example.socialproject.R

class GenderAgeFragment(var assignActivity: AssignActivity) : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View = inflater.inflate(R.layout.gender_age_assign, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        var next = view.findViewById<AppCompatButton>(R.id.gender_age_next_btn)
        next.setOnClickListener {
            assignActivity.nextPage()
        }
    }

    override fun onResume() {
        super.onResume()
//        val layoutHeight = view?.findViewById<ConstraintLayout>(R.id.gender_age_layout)
//        assignActivity.resizeViewPager(layoutHeight!!.layoutParams.height)
        val layoutview = view?.findViewById<View>(R.id.clause_assign_layout)
        if (layoutview != null) {
            layoutview.requestLayout()
        }
    }
}