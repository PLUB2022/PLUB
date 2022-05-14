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

class AccessAuthorityFragment(var assignActivity: AssignActivity) : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View = inflater.inflate(R.layout.access_authority_assign, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        var next = view.findViewById<AppCompatButton>(R.id.access_authority_next_btn)
        var fadeinani : Animation = AnimationUtils.loadAnimation(context, R.anim.fade_in)
        var fadeoutani : Animation = AnimationUtils.loadAnimation(context, R.anim.fade_out)
        val layoutview = view?.findViewById<View>(R.id.access_authority_layout)
        if (layoutview != null) {
            layoutview.startAnimation(fadeinani)
        }
        next.setOnClickListener {
            if (layoutview != null) {
                layoutview.startAnimation(fadeoutani)
            }
            Handler().postDelayed({
                assignActivity.nextPage()
            },200)
        }
    }

    override fun onResume() {
        super.onResume()
//        val layoutHeight = view?.findViewById<ConstraintLayout>(R.id.gender_age_layout)
//        assignActivity.resizeViewPager(layoutHeight!!.layoutParams.height)
        val layoutview = view?.findViewById<View>(R.id.access_authority_layout)
        if (layoutview != null) {
            layoutview.requestLayout()
        }
    }
}