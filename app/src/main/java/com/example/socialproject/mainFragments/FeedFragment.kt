package com.example.socialproject.mainFragments

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.AppCompatButton
import androidx.databinding.DataBindingUtil
import androidx.navigation.findNavController
import com.example.socialproject.R
import com.example.socialproject.databinding.FragmentFeedBinding

class FeedFragment : Fragment() {

    private lateinit var binding : FragmentFeedBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_feed, container, false)

        binding.homeClubTap.setOnClickListener {
            it.findNavController().navigate(R.id.action_feedFragment_to_homeFragment)
        }

        binding.socialTap.setOnClickListener {
            it.findNavController().navigate(R.id.action_feedFragment_to_socialFragment)
        }

        binding.clubAddTap.setOnClickListener {
            it.findNavController().navigate(R.id.action_feedFragment_to_clubFragment)
        }

        binding.profileTap.setOnClickListener {
            it.findNavController().navigate(R.id.action_feedFragment_to_profileFragment)
        }


        return binding.root
    }

}