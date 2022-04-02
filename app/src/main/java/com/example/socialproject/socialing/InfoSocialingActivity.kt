package com.example.socialproject.socialing

import android.animation.ObjectAnimator
import android.animation.ValueAnimator
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.view.View
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import android.widget.ImageView
import android.widget.ScrollView
import androidx.constraintlayout.widget.ConstraintLayout
import com.example.socialproject.R




class InfoSocialingActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_info_socialing)

        val scrollView = findViewById<ScrollView>(R.id.infoSocialScrollView)
        val view = findViewById<ConstraintLayout>(R.id.hostgrid)
        val socialImageView = findViewById<ImageView>(R.id.SocialImageView)
        val contentView = findViewById<ConstraintLayout>(R.id.infoSocialContentView)
        var fadeinani : Animation = AnimationUtils.loadAnimation(this, R.anim.fade_in)
        var fadeoutani : Animation = AnimationUtils.loadAnimation(this, R.anim.fade_out)

        //=====================버튼 클릭시 애니메이션 작동 ========================//
//        contentView.setOnClickListener {
//            scrollView.post {
//                ObjectAnimator.ofInt(scrollView, "scrollY", socialImageView.bottom).setDuration(1000).start()
//            }
//            val aa = view.height.toInt()
//            val heightAnimator = ValueAnimator.ofInt(view.height, view.height * 3)
//            heightAnimator.duration = 2000
//
//
//            heightAnimator.addUpdateListener { animation: ValueAnimator ->
//                view.layoutParams.height = animation.animatedValue as Int
//                view.requestLayout()
//            }
//
//            heightAnimator.start()
//        }
//================================================================================


        //================================ 자동 스크롤 (2초) ==============================//
        Handler().postDelayed({
            scrollView.post {
                ObjectAnimator.ofInt(scrollView, "scrollY", socialImageView.bottom).setDuration(1000).start()
            }
            //TODO 50은 임시, px단위로 계산되기 때문에 비율로 설정해야함
            val heightAnimator = ValueAnimator.ofInt(view.height, view.height * 3 + 50)
            heightAnimator.duration = 1000


                heightAnimator.addUpdateListener { animation: ValueAnimator ->
                    view.layoutParams.height = animation.animatedValue as Int
                    view.requestLayout()
                }

                heightAnimator.start()
        },2000)
        //===============================================================================//
    }


}