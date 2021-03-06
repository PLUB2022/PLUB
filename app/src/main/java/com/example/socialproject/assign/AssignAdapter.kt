package com.example.socialproject.assign

import android.app.Activity
import android.text.Layout
import android.util.Log
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Lifecycle
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.socialproject.homeFragment.HomeFragmentList1
import java.util.ArrayList

class AssignAdapter(fragmentManager: FragmentActivity, var assignActivity: AssignActivity) : FragmentStateAdapter(fragmentManager) {

    override fun getItemCount(): Int = 6

    override fun createFragment(position: Int): Fragment {
        if(position == 0){
            Log.d("TAG", position.toString() + " " + "성별 나이 프래그맨트 생성")
            return GenderAgeFragment(assignActivity)
        }
        else if(position == 1){
            Log.d("TAG", position.toString() + " " + "이용 약관 프래그맨트 생성")
            return ClauseFragment(assignActivity)
        }
        else if(position == 2){
            Log.d("TAG", position.toString() + " " + "접근 권한 프래그맨트 생성")
            return AccessAuthorityFragment(assignActivity)
        }
        else if(position == 3){
            Log.d("TAG", position.toString() + " " +  "소셜링 선택 프래그맨트 생성")
            return ChoiceSocialingFragment(assignActivity)
        }
        else if(position == 4){
            Log.d("TAG", position.toString() + " " +  "소셜링 선택 프래그맨트 생성")
            return ClickSocialingFragment(assignActivity)
        }
        else{
            Log.d("TAG", position.toString() + " " + "프래그맨트 생성 다른거")
            return GenderAgeFragment(assignActivity)
        }
    }

}