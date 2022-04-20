package com.example.socialproject.assign

import android.animation.ValueAnimator
import android.os.Bundle
import android.os.Handler
import android.os.PersistableBundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.appcompat.widget.AppCompatButton
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.widget.ViewPager2
import com.example.socialproject.R
import com.example.socialproject.databinding.FragmentHomeBinding

class ClauseFragment(var assignActivity: AssignActivity) : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View = inflater.inflate(R.layout.clause_assign, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        var click = false
        var basicheight : Int = 0
        var next = view.findViewById<AppCompatButton>(R.id.clause_next_btn)
        var seemorebtn = view.findViewById<AppCompatButton>(R.id.seemoreBtn)
        var checklayout = view.findViewById<ConstraintLayout>(R.id.checklayout1)
        var clauseExplainText1 = view.findViewById<TextView>(R.id.clause_explain_text1)
        next.setOnClickListener {
            assignActivity.nextPage()
        }


        seemorebtn.setOnClickListener {
            if(click){
                val heightAnimator = ValueAnimator.ofInt(checklayout.height,
                    (basicheight))
                heightAnimator.duration = 500


                heightAnimator.addUpdateListener { animation: ValueAnimator ->
                    checklayout.layoutParams.height = animation.animatedValue as Int
                    checklayout.requestLayout()
                }

                heightAnimator.start()
                Handler().postDelayed({
                    clauseExplainText1.isVisible = false
                },500)
                click = false
            }
            else{
                basicheight = checklayout.height
                val heightAnimator = ValueAnimator.ofInt(checklayout.height,
                    (checklayout.height * 6)
                )
                heightAnimator.duration = 500


                heightAnimator.addUpdateListener { animation: ValueAnimator ->
                    checklayout.layoutParams.height = animation.animatedValue as Int
                    checklayout.requestLayout()
                }

                heightAnimator.start()
                Handler().postDelayed({
                    clauseExplainText1.isVisible = true
                },500)
                click = true
            }
        }
    }

    override fun onResume() {
        super.onResume()
//        val layoutHeight = view?.findViewById<ConstraintLayout>(R.id.clause_assign_layout)
//        assignActivity.resizeViewPager(layoutHeight!!.layoutParams.height)
        val layoutview = view?.findViewById<View>(R.id.clause_assign_layout)
        if (layoutview != null) {
            layoutview.requestLayout()
        }
    }

}