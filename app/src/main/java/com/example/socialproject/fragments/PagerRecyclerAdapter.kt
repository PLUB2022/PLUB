package com.example.socialproject.fragments

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.annotation.ColorRes
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.example.socialproject.R

class PagerRecyclerAdapter(private val Title: ArrayList<String>, private val Content : ArrayList<String>) : RecyclerView.Adapter<PagerRecyclerAdapter.PagerViewHolder>() {

    inner class PagerViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        private val titleName: TextView = itemView.findViewById(R.id.title_id)
        private val contentC : TextView = itemView.findViewById(R.id.content_id)

        fun bind(title : String, content : String, position: Int) {
            titleName.text = title
            contentC.text = content
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PagerViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(
            R.layout.titleslider1,
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
