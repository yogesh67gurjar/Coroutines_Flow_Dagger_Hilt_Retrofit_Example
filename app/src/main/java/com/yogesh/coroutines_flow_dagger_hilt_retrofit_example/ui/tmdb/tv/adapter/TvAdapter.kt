package com.yogesh.coroutines_flow_dagger_hilt_retrofit_example.ui.tmdb.tv.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.yogesh.coroutines_flow_dagger_hilt_retrofit_example.R
import com.yogesh.coroutines_flow_dagger_hilt_retrofit_example.ui.tmdb.movie.models.MovieResp
import com.yogesh.coroutines_flow_dagger_hilt_retrofit_example.ui.tmdb.tv.models.TvResp

class TvAdapter(val context: Context, private val movieList: List<TvResp.Results>) :
    RecyclerView.Adapter<TvAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.rv_movie, parent, false)

        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        val singleUnit = movieList[position]

        Glide.with(context).load("https://image.tmdb.org/t/p/w500/" + singleUnit.posterPath)
            .placeholder(R.drawable.loading)
            .into(holder.imageView)
        holder.textView.text = singleUnit.originalName
    }

    override fun getItemCount(): Int {
        return movieList.size
    }

    class ViewHolder(ItemView: View) : RecyclerView.ViewHolder(ItemView) {
        val imageView: ImageView = itemView.findViewById(R.id.imageview)
        val textView: TextView = itemView.findViewById(R.id.textView)
    }
}