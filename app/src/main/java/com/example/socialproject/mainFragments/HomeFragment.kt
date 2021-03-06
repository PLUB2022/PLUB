package com.example.socialproject.mainFragments

import android.annotation.SuppressLint
import android.graphics.Color
import android.os.Bundle
import android.os.Handler
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.view.marginTop
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
import java.lang.Math.abs
import kotlin.properties.Delegates

class HomeFragment : Fragment() {

    private var nowpos : Int = 0
    private var flag by Delegates.notNull<Boolean>()
    private lateinit var binding : FragmentHomeBinding
    private val tabTitles = arrayListOf("   Talking   ", "   Enjoying   ", "   Learning   ", "   Exercising   ", "  Relaxing  ", "  Creating  ", "  Challenging  ")

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


        // ?????? Fragment ?????? ??? ??????
        binding.tabLayout.getTabAt(1)?.view?.setBackgroundResource(R.drawable.tab_unselected2)

        return binding.root
    }

    // ?????? ????????? ??????
    @SuppressLint("ResourceAsColor")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val appbar = view.findViewById<AppBarLayout>(R.id.appbar)
        val viewPager2 = view.findViewById<ViewPager2>(R.id.viewPager2)
        val tabLayout = view.findViewById<TabLayout>(R.id.tabLayout)

        flag = true

        viewPager2.adapter =
            PostFragmentStateAdapter(childFragmentManager, viewLifecycleOwner.lifecycle)

        //???????????? ??????
        viewPager2.isUserInputEnabled = false


        // Tablayout??? ?????? ??????
        TabLayoutMediator(tabLayout, viewPager2) { tab, position ->
            tab.text = tabTitles[position]
        }.attach()
//
////        for (i in 0..7) {
////            val textView = LayoutInflater.from(requireContext()).inflate(R.layout.tab_title, null) as TextView
////            binding.tabLayout.getTabAt(i)?.customView = textView
////        }

        var fadeinani : Animation = AnimationUtils.loadAnimation(context, R.anim.fade_in)
        var fadeoutani : Animation = AnimationUtils.loadAnimation(context, R.anim.fade_out)


        // ?????? ??????, ????????? ?????? ??????
        tabLayout.setTabTextColors(Color.WHITE, Color.BLACK)
        ChangeAni(0, tabLayout)

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
                    6 -> {
                        ChangeAni(6, tabLayout)
                    }
                }
            }
        })


//        var previous = false
//        var collapsed = false
//
//        appbar.addOnOffsetChangedListener(AppBarLayout.OnOffsetChangedListener { appBarLayout, verticalOffset ->
//            if (previous != collapsed) {
////                updateToolbarIconsOnScrollChange(toolbar, collapsed)
//                previous = collapsed
//            }
//        })
////        updateToolbarIconsOnScrollChange(toolbar, collapsed)


        appbar.addOnOffsetChangedListener(AppBarLayout.OnOffsetChangedListener { appBarLayout, verticalOffset ->

            when {
                //  State Expanded
                verticalOffset == 0 -> {
                    Log.d("tob", "?????????")
                    if(!flag){
                        flag = true
                        tabLayout.startAnimation(fadeoutani)
                        Handler().postDelayed({
                            tabLayout.startAnimation(fadeinani)
                            tabLayout.setBackgroundColor(Color.LTGRAY)
                            tabLayout.setPadding(0,0,0,0)
                            appbar.elevation = 0.0F
                            ChangeAni(nowpos, tabLayout)
                        }, 200)

                    }
                }
                //  State Collapsed
                abs(verticalOffset) >= appBarLayout.totalScrollRange -> {
                    Log.d("tob", "??????")
                    if(flag){
                        flag = false
                        tabLayout.startAnimation(fadeoutani)
                        Handler().postDelayed({
                            tabLayout.setBackgroundColor(Color.WHITE)
                            tabLayout.setPadding(0,0,0,14)
                            appbar.elevation = 10.0F
                            ChangeAni(nowpos, tabLayout)
                            tabLayout.startAnimation(fadeinani)
                        }, 200)

                    }
                }//  Do anything for Collapse State
            }
        })

    }

    @SuppressLint("ResourceAsColor")
    public fun ChangeAni(pos : Int, tab : TabLayout){
        nowpos = pos
        if(flag){
            tab.setTabTextColors(Color.WHITE, Color.BLACK)
            for(i in 0.. 7){
                tab.getTabAt(i)?.view?.setBackgroundResource(R.drawable.tab_basic)
            }
            tab.getTabAt(pos)?.view?.setBackgroundResource(R.drawable.tab_selected_background)
            if(pos == 0){
                tab.getTabAt(1)?.view?.setBackgroundResource(R.drawable.tab_unselected2)
            }
            else if(pos == 6){
                tab.getTabAt(5)?.view?.setBackgroundResource(R.drawable.tab_unselected)
            }
            else{
                tab.getTabAt(pos - 1)?.view?.setBackgroundResource(R.drawable.tab_unselected)
                tab.getTabAt(pos + 1)?.view?.setBackgroundResource(R.drawable.tab_unselected2)
            }
        }
        else{
            tab.setTabTextColors(Color.LTGRAY, Color.WHITE)
            for(i in 0.. 7){
                tab.getTabAt(i)?.view?.setBackgroundResource(R.drawable.tab_noncollas)
            }
            tab.getTabAt(pos)?.view?.setBackgroundResource(R.drawable.tab_noncollas_selected_background)
        }

    }

}