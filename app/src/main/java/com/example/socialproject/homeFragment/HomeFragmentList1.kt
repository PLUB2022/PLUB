package com.example.socialproject.homeFragment

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.viewpager2.widget.ViewPager2
import com.example.socialproject.R
import com.example.socialproject.databinding.FragmentHomeList1Binding
import com.example.socialproject.socialing.InfoSocialingActivity

class HomeFragmentList1 : Fragment() {

    private lateinit var binding : FragmentHomeList1Binding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }
    //테스트용 주석

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_home_list1, container, false)

        //TODO 파이어 베이스 순위 가져와야 함
        var titleList = arrayListOf<String>(
            "인기있는 소셜링1 (추천)",
            "인기있는 소셜링2 (비추)",
            "테스트용 소셜링3 (추천)",
            "진짜 테스트!"
        )

        //TODO 파이어 베이스 순위 가져와야 함
        var contentList = arrayListOf<String>(
            "별 자랑처럼 가득 그러나 이름자 이웃 거외다. 많은 내 릴케 거외",
            "인기있는 소셜링2 (비추)",
            "테스트용 소셜링3 (추천)",
            "진짜 테스트!"
        )


        //뷰페이저 레이아웃과 겹치는 부분 이벤트 가로채기
        binding.socialHostTop.setOnClickListener {
            //
            return@setOnClickListener
        }


        // 뷰페이저 어뎁터
        binding.titleViewPager.adapter = PagerRecyclerAdapter(titleList, contentList)
        // ViewPager의 Paging 방향은 Horizontal
        binding.titleViewPager.orientation = ViewPager2.ORIENTATION_HORIZONTAL

        binding.titleViewPager.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {

            // Paging 완료되면 호출
            override fun onPageSelected(position: Int) {
                super.onPageSelected(position)
                Log.d("ViewPagerFragment", "Page ${position+1}")
            }
        })

        binding.smallTalkTitle3.setOnClickListener {
            val intent = Intent(context, InfoSocialingActivity::class.java)
            startActivity(intent)
        }
        return binding.root
    }
}