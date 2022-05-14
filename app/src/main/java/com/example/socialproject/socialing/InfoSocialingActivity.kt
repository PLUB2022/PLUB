package com.example.socialproject.socialing

import android.animation.ObjectAnimator
import android.animation.ValueAnimator
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.util.Log
import android.view.View
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import android.widget.ImageView
import android.widget.ScrollView
import androidx.constraintlayout.widget.ConstraintLayout
import com.example.socialproject.R
import android.widget.Toast

import com.example.socialproject.MainActivity

import android.view.MotionEvent
import android.view.View.OnTouchListener


class InfoSocialingActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_info_socialing)

        var flag : Boolean = true
        var click = false

        val scrollView = findViewById<ScrollView>(R.id.infoSocialScrollView)
        val view = findViewById<ConstraintLayout>(R.id.hostgrid)
        val socialImageView = findViewById<ImageView>(R.id.SocialImageView)
        val contentView = findViewById<ConstraintLayout>(R.id.infoSocialContentView)
        val infomoim = findViewById<ConstraintLayout>(R.id.rectangle_3)

        infomoim.setOnClickListener {
            val intent = Intent(this, InfoSocialingFeedActivity::class.java)
            startActivity(intent)
            finish()
        }

        //=====================버튼 클릭시 애니메이션 작동 ========================//
        contentView.setOnClickListener {
            if(click){

            }
            else{
                scrollView.post {
                    ObjectAnimator.ofInt(scrollView, "scrollY", socialImageView.bottom).setDuration(1000).start()
                }
                val aa = view.height.toInt()
                val heightAnimator = ValueAnimator.ofInt(view.height, view.height * 3)
                heightAnimator.duration = 1000


                heightAnimator.addUpdateListener { animation: ValueAnimator ->
                    view.layoutParams.height = animation.animatedValue as Int
                    view.requestLayout()
                }
                flag = false

                heightAnimator.start()
                click = true
            }

        }
//================================================================================


        //================================ 자동 스크롤 (2초) ==============================//
//        Handler().postDelayed({
//            scrollView.post {
//                ObjectAnimator.ofInt(scrollView, "scrollY", socialImageView.bottom).setDuration(1000).start()
//            }
//            //TODO 50은 임시, px단위로 계산되기 때문에 비율로 설정해야함
//            val heightAnimator = ValueAnimator.ofInt(view.height, view.height * 3 + 50)
//            heightAnimator.duration = 1000
//
//
//                heightAnimator.addUpdateListener { animation: ValueAnimator ->
//                    view.layoutParams.height = animation.animatedValue as Int
//                    view.requestLayout()
//                }
//
//                heightAnimator.start()
//            flag = false
//        },2000)
        //===============================================================================//

        scrollView.setOnTouchListener { v, event ->
            if(flag){
                Log.d("TAG", "하이")
                true
            }
            else{
                false
            }
        }
    }
}