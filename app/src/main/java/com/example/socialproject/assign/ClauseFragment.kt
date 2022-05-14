package com.example.socialproject.assign

import android.animation.ValueAnimator
import android.os.Bundle
import android.os.Handler
import android.os.PersistableBundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.Animation
import android.view.animation.AnimationUtils
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
        var basicheight: Int = 0
        var next = view.findViewById<AppCompatButton>(R.id.clause_next_btn)
        var seemorebtn = view.findViewById<AppCompatButton>(R.id.seemoreBtn)
        var checklayout = view.findViewById<ConstraintLayout>(R.id.checklayout1)
        var allcheckBtn = view.findViewById<AppCompatButton>(R.id.allCheckBtn)
        var allBtnFlag = false
        var checkBtn1 = view.findViewById<AppCompatButton>(R.id.clauseCheckBtn1)
        var checkBtn2 = view.findViewById<AppCompatButton>(R.id.clauseCheckBtn2)
        var checkBtn3 = view.findViewById<AppCompatButton>(R.id.clauseCheckBtn3)
        var checkBtn4 = view.findViewById<AppCompatButton>(R.id.clauseCheckBtn4)
        var checkBtn5 = view.findViewById<AppCompatButton>(R.id.clauseCheckBtn5)
        var checkBtnList =
            arrayListOf<AppCompatButton>(checkBtn1, checkBtn2, checkBtn3, checkBtn4, checkBtn5)
        var flagList = arrayListOf<Boolean>(false, false, false, false, false)
        var clauseExplainText1 = view.findViewById<TextView>(R.id.clause_explain_text1)

        var fadeinani: Animation = AnimationUtils.loadAnimation(context, R.anim.fade_in)
        var fadeoutani: Animation = AnimationUtils.loadAnimation(context, R.anim.fade_out)
        val layoutview = view?.findViewById<View>(R.id.clause_assign_layout)
        if (layoutview != null) {
            layoutview.startAnimation(fadeinani)
        }

        for (i in 0..4) {
            checkBtnList[i].setOnClickListener {
                if (flagList[i]) {
                    checkBtnList[i].setBackgroundResource(R.drawable.clause_assign_check_rect)
                    allcheckBtn.setBackgroundResource(R.drawable.clause_assign_check_rect)
                    allBtnFlag = false
                    flagList[i] = false
                } else {
                    checkBtnList[i].setBackgroundResource(R.drawable.clause_assign_checked_rect)
                    flagList[i] = true
                }
                if(flagList[0] && flagList[1] && flagList[2] && flagList[3] && flagList[4]){
                    allcheckBtn.setBackgroundResource(R.drawable.clause_assign_checked_rect)
                    allBtnFlag = true
                }
            }
        }

        allcheckBtn.setOnClickListener {
            if (allBtnFlag) {
                for (i in 0..4) {
                    checkBtnList[i].setBackgroundResource(R.drawable.clause_assign_check_rect)
                    flagList[i] = false
                }
                allBtnFlag = false
                allcheckBtn.setBackgroundResource(R.drawable.clause_assign_check_rect)
            }
            else{
                for (i in 0..4) {
                    checkBtnList[i].setBackgroundResource(R.drawable.clause_assign_checked_rect)
                    flagList[i] = true
                }
                allBtnFlag = true
                allcheckBtn.setBackgroundResource(R.drawable.clause_assign_checked_rect)
            }
        }

        next.setOnClickListener {
            if (layoutview != null) {
                layoutview.startAnimation(fadeoutani)
            }
            Handler().postDelayed({
                assignActivity.nextPage()
            }, 200)
        }


        seemorebtn.setOnClickListener {
            if (click) {
                val heightAnimator = ValueAnimator.ofInt(
                    checklayout.height,
                    (basicheight)
                )
                heightAnimator.duration = 500


                heightAnimator.addUpdateListener { animation: ValueAnimator ->
                    checklayout.layoutParams.height = animation.animatedValue as Int
                    checklayout.requestLayout()
                }

                heightAnimator.start()
                Handler().postDelayed({
                    clauseExplainText1.isVisible = false
                }, 500)
                click = false
            } else {
                basicheight = checklayout.height
                val heightAnimator = ValueAnimator.ofInt(
                    checklayout.height,
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
                }, 500)
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