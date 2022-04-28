package com.example.socialproject.assign
import android.graphics.Color
import android.os.Bundle
import android.os.PersistableBundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.AppCompatButton
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.widget.ViewPager2
import com.example.socialproject.R

class ClickSocialingFragment(var assignActivity: AssignActivity) : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View = inflater.inflate(R.layout.click_socialing_assign, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        var yes = view.findViewById<AppCompatButton>(R.id.click_choice_socialing_yes_btn)
        val button1 = view.findViewById<AppCompatButton>(R.id.socialing_choice_btn1)
        val button2 = view.findViewById<AppCompatButton>(R.id.socialing_choice_btn2)
        val button3 = view.findViewById<AppCompatButton>(R.id.socialing_choice_btn3)
        val button4 = view.findViewById<AppCompatButton>(R.id.socialing_choice_btn4)
        val button5 = view.findViewById<AppCompatButton>(R.id.socialing_choice_btn5)
        val button6 = view.findViewById<AppCompatButton>(R.id.socialing_choice_btn6)
        val button7 = view.findViewById<AppCompatButton>(R.id.socialing_choice_btn7)
        val button8 = view.findViewById<AppCompatButton>(R.id.socialing_choice_btn8)
        val button9 = view.findViewById<AppCompatButton>(R.id.socialing_choice_btn9)
        val button10 = view.findViewById<AppCompatButton>(R.id.socialing_choice_btn10)
        val button11 = view.findViewById<AppCompatButton>(R.id.socialing_choice_btn11)
        val button12 = view.findViewById<AppCompatButton>(R.id.socialing_choice_btn12)
        val button13 = view.findViewById<AppCompatButton>(R.id.socialing_choice_btn13)
        val button14 = view.findViewById<AppCompatButton>(R.id.socialing_choice_btn14)
        val button15 = view.findViewById<AppCompatButton>(R.id.socialing_choice_btn15)
        val button16 = view.findViewById<AppCompatButton>(R.id.socialing_choice_btn16)
        val button17 = view.findViewById<AppCompatButton>(R.id.socialing_choice_btn17)
        val button18 = view.findViewById<AppCompatButton>(R.id.socialing_choice_btn18)
        val button19 = view.findViewById<AppCompatButton>(R.id.socialing_choice_btn19)
        val button20 = view.findViewById<AppCompatButton>(R.id.socialing_choice_btn20)

        var button_list = arrayListOf<AppCompatButton>(button1, button2, button3, button4, button5, button6, button7, button8, button9, button10
            , button11, button12, button13, button14, button15, button16, button17, button18, button19, button20)

        var flag_list = arrayListOf<Boolean>(false, false, false, false, false, false, false, false, false, false
            , false, false, false, false, false, false, false, false, false, false)

        for(i in 0.. 19){
            button_list[i].setOnClickListener {
                if(flag_list[i]){
                    button_list[i].setBackgroundResource(R.drawable.unselected_choice_assign_btn)
                    button_list[i].setTextColor(Color.parseColor("#737373"))
                    flag_list[i] = false
                }
                else{
                    button_list[i].setBackgroundResource(R.drawable.selected_choice_assign_btn)
                    button_list[i].setTextColor(Color.WHITE)
                    flag_list[i] = true
                }

            }
        }

        yes.setOnClickListener {
            assignActivity.prevPage()
        }
    }

    override fun onResume() {
        super.onResume()
        val layoutview = view?.findViewById<View>(R.id.click_choice_socialing_layout)
        if (layoutview != null) {
            layoutview.requestLayout()
        }
    }
}