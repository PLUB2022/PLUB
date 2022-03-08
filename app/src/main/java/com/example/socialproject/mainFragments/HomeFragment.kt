package com.example.socialproject.mainFragments

import android.annotation.SuppressLint
import android.graphics.Color
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.databinding.DataBindingUtil
import androidx.navigation.findNavController
import androidx.viewpager2.widget.ViewPager2
import com.example.socialproject.R
import com.example.socialproject.databinding.FragmentHomeBinding
import com.example.socialproject.homeFragment.PostFragmentStateAdapter
import com.google.android.material.appbar.AppBarLayout
import com.google.android.material.tabs.TabItem
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator

class HomeFragment : Fragment() {

    private lateinit var binding : FragmentHomeBinding
    private val tabTitles = arrayListOf("talking", "enjoying", "learning", "exercising", "relaxing", "creating", "challenging")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_home, container, false)

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

    // 새로 그리는 부분
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val appbar = view.findViewById<AppBarLayout>(R.id.appbar)
        val viewPager2 = view.findViewById<ViewPager2>(R.id.viewPager2)
        val tabLayout = view.findViewById<TabLayout>(R.id.tabLayout)
        val tab1 = view.findViewById<TabItem>(R.id.tabtalking1)

        viewPager2.adapter =
            PostFragmentStateAdapter(childFragmentManager, viewLifecycleOwner.lifecycle)

        //스와이프 막기
        viewPager2.isUserInputEnabled = false

//        TabLayoutMediator(tabLayout, viewPager2) { tab, position ->
//            tab.text = tabTitles[position]
//        }.attach()
//
////        for (i in 0..7) {
////            val textView = LayoutInflater.from(requireContext()).inflate(R.layout.tab_title, null) as TextView
////            binding.tabLayout.getTabAt(i)?.customView = textView
////        }

        tabLayout.setTabTextColors(Color.WHITE, Color.BLACK)

        tabLayout.addOnTabSelectedListener(object: TabLayout.OnTabSelectedListener {
            override fun onTabReselected(tab: TabLayout.Tab?) { }
            override fun onTabUnselected(tab: TabLayout.Tab?) { }
            @SuppressLint("ResourceAsColor")
            override fun onTabSelected(tab: TabLayout.Tab?) {
                when (tab!!.position) {
                    0 -> ChangeAni(0, tabLayout)
                    1 -> {
                        ChangeAni(1, tabLayout)
                    }
                    2 -> {
                        ChangeAni(2, tabLayout)
                    }
                    3 -> {
                        ChangeAni(3, tabLayout)
                    }
                    4 -> {
                        ChangeAni(4, tabLayout)
                    }
                    5 -> {
                        ChangeAni(5, tabLayout)
                    }
                }
            }
        })


        var previous = false
        var collapsed = false

        appbar.addOnOffsetChangedListener(AppBarLayout.OnOffsetChangedListener { appBarLayout, verticalOffset ->


            if (previous != collapsed) {
//                updateToolbarIconsOnScrollChange(toolbar, collapsed)
                previous = collapsed

            }
        })

//        updateToolbarIconsOnScrollChange(toolbar, collapsed)

    }

    @SuppressLint("ResourceAsColor")
    public fun ChangeAni(pos : Int, tab : TabLayout){
        for(i in 0.. 5){
            tab.getTabAt(i)?.view?.setBackgroundResource(R.drawable.test_tab_basic)
        }
        tab.getTabAt(pos)?.view?.setBackgroundResource(R.drawable.tab_selected_background)
        if(pos == 0){
            tab.getTabAt(1)?.view?.setBackgroundResource(R.drawable.test_unselect1)
        }
        else if(pos == 5){
            tab.getTabAt(4)?.view?.setBackgroundResource(R.drawable.test_unselect)
        }
        else{
            tab.getTabAt(pos - 1)?.view?.setBackgroundResource(R.drawable.test_unselect)
            tab.getTabAt(pos + 1)?.view?.setBackgroundResource(R.drawable.test_unselect1)
        }

    }

}