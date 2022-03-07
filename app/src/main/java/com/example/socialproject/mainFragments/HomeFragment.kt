package com.example.socialproject.mainFragments

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.*
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.appcompat.widget.Toolbar
import androidx.databinding.DataBindingUtil
import androidx.navigation.Navigation.findNavController
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.setupWithNavController
import androidx.viewpager2.widget.ViewPager2
import com.example.socialproject.R
import com.example.socialproject.databinding.FragmentHomeBinding
import com.example.socialproject.homeFragment.PostFragmentStateAdapter
import com.google.android.material.appbar.AppBarLayout
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator

class HomeFragment : Fragment() {

    private lateinit var binding : FragmentHomeBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
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

        val navController = findNavController()

        val appbar = view.findViewById<AppBarLayout>(R.id.appbar)
        val topbar = view.findViewById<Toolbar>(R.id.topbar)
        val viewPager2 = view.findViewById<ViewPager2>(R.id.viewPager2)
        val tabLayout = view.findViewById<TabLayout>(R.id.tabLayout)

        val activity : AppCompatActivity = getActivity() as AppCompatActivity
        activity.setSupportActionBar(topbar)

        viewPager2.adapter =
            PostFragmentStateAdapter(childFragmentManager, viewLifecycleOwner.lifecycle)

        //스와이프 막기
        viewPager2.isUserInputEnabled = false

        TabLayoutMediator(tabLayout, viewPager2) { tab, position ->
            tab.text = "Tab $position"
        }.attach()

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

}