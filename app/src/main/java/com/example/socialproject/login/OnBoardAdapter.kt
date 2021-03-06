package com.example.socialproject.login

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.socialproject.R

class OnBoardAdapter(private val Title: ArrayList<String>, private val Content : ArrayList<String>) : RecyclerView.Adapter<OnBoardAdapter.PagerViewHolder>() {

    inner class PagerViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        private val onBoardImage : ImageView = itemView.findViewById(R.id.OnBoardImage)
        private val onBoardTitle: TextView = itemView.findViewById(R.id.OnBoardTitle)
        private val onBoardText: TextView = itemView.findViewById(R.id.OnBoardText)

        fun bind(title: String, content: String, position: Int) {
            onBoardTitle.text = title
            onBoardText.text = content
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PagerViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(
            R.layout.activity_on_board_adapter,
            parent,
            false
        )
        return PagerViewHolder(view)
    }

    override fun onBindViewHolder(holder: PagerViewHolder, position: Int) {
        holder.bind(Title[position], Content[position], position)
    }

    override fun getItemCount(): Int = Title.size
}