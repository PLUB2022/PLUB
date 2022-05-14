package com.example.socialproject.login

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.appcompat.widget.AppCompatButton
import androidx.viewpager2.widget.ViewPager2
import com.example.socialproject.R
import com.tbuonomo.viewpagerdotsindicator.DotsIndicator

class OnBoardActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_on_board)

        val nextButton : AppCompatButton = findViewById(R.id.nextButton)
        val dotsIndicator = findViewById<DotsIndicator>(R.id.dots_indicator)

        //임시 온보딩 타이틀
        val titleList = arrayListOf<String>(
            "여기다 뭐라 써야",
            "잘썼다고",
            "소문이 날라나...?",
        )

        //임시 온보드 텍스트
        val contentList = arrayListOf<String>(
            "아직 불러 부끄러운 무성할 벌레는 새워 까닭입니다. 사람들의 나는 노새, 언덕 가을 거외다. 부끄러운 하나에 어머니, 잔디가 별 책상을 이제 없이 거외다.\n" +
                    "\n" +
                    "쉬이 하늘에는 내일 묻힌 까닭입니다. 마리아 아침이 써 이국 책상을 봅니다.",
            "아직 불러 부끄러운 무성할 벌레는 새워 까닭입니다. 사람들의 나는 노새, 언덕 가을 거외다. 부끄러운 하나에 어머니, 잔디가 별 책상을 이제 없이 거외다.\n" +
                    "\n" +
                    "쉬이 하늘에는 내일 묻힌 까닭입니다. 마리아 아침이 써 이국 책상을 봅니다.",
            "아직 불러 부끄러운 무성할 벌레는 새워 까닭입니다. 사람들의 나는 노새, 언덕 가을 거외다. 부끄러운 하나에 어머니, 잔디가 별 책상을 이제 없이 거외다.\n" +
                    "\n" +
                    "쉬이 하늘에는 내일 묻힌 까닭입니다. 마리아 아침이 써 이국 책상을 봅니다.",
        )

        val onBoardViewPager : ViewPager2 = findViewById(R.id.OnBoardPager)
        onBoardViewPager.adapter = OnBoardAdapter(titleList, contentList)
        onBoardViewPager.orientation = ViewPager2.ORIENTATION_HORIZONTAL
        onBoardViewPager.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {

            // Paging 완료되면 호출
            override fun onPageSelected(position: Int) {
                super.onPageSelected(position)
                if(position == 2){
                    nextButton.text = "시작"
                }
                else{
                    nextButton.text = "다음"
                }
            }
        })

        nextButton.setOnClickListener {
            val temp = onBoardViewPager.currentItem
            if(temp == 2){
                val intent = Intent(this, LoginActivity::class.java)
                startActivity(intent)
                finish()
            }
            else{
                onBoardViewPager.setCurrentItem(temp + 1, true)
            }
        }

        dotsIndicator.setViewPager2(onBoardViewPager)
    }
}