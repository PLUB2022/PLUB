package com.example.socialproject.fragments

import android.graphics.Color.red
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.navigation.findNavController
import androidx.viewpager2.widget.ViewPager2
import com.example.socialproject.R
import com.example.socialproject.databinding.FragmentHomeBinding

class HomeFragment : Fragment() {

    private lateinit var binding : FragmentHomeBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_home, container, false)

        var titleList = arrayListOf<String>(
            "인기있는 소셜링1 (추천)",
            "인기있는 소셜링2 (비추)",
            "테스트용 소셜링3 (추천)",
            "진짜 테스트!"
        )

        var contentList = arrayListOf<String>(
            "별 자랑처럼 가득 그러나 이름자 이웃 거외다. 많은 내 릴케 거외",
            "인기있는 소셜링2 (비추)",
            "테스트용 소셜링3 (추천)",
            "진짜 테스트!"
        )

        // RecyclerView.Adapter<ViewHolder>()
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


        binding.socialTap.setOnClickListener {
            it.findNavController().navigate(R.id.action_homeFragment_to_socialFragment)
        }

        binding.clubAddTap.setOnClickListener {
            it.findNavController().navigate(R.id.action_homeFragment_to_clubFragment)
        }

        binding.feedTap.setOnClickListener {
            it.findNavController().navigate(R.id.action_homeFragment_to_feedFragment)
        }

        binding.profileTap.setOnClickListener {
            it.findNavController().navigate(R.id.action_homeFragment_to_profileFragment)
        }

        return binding.root
    }

}