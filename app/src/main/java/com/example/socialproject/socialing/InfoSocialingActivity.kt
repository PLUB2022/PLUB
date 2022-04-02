package com.example.socialproject.socialing

import android.animation.ObjectAnimator
import android.animation.ValueAnimator
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.view.View
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
        Handler().postDelayed({
            //scrollView.smoothScrollTo(0, socialImageView.bottom)
            //scrollView.smoothScrollToView(socialImageView)
            scrollView.post {
                ObjectAnimator.ofInt(scrollView, "scrollY", socialImageView.bottom).setDuration(1000).start()
            }
            val aa = view.height.toInt()
            val heightAnimator = ValueAnimator.ofInt(view.height, view.height * 3)
            heightAnimator.duration = 2000


                heightAnimator.addUpdateListener { animation: ValueAnimator ->
                    view.layoutParams.height = animation.animatedValue as Int
                    view.requestLayout()
                }

                heightAnimator.start()
        },2000)
    }


}