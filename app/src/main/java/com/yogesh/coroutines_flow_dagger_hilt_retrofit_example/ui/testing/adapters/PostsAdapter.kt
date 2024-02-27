package com.yogesh.coroutines_flow_dagger_hilt_retrofit_example.ui.testing.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.yogesh.coroutines_flow_dagger_hilt_retrofit_example.R
import com.yogesh.coroutines_flow_dagger_hilt_retrofit_example.ui.testing.models.PostResp
import com.yogesh.coroutines_flow_dagger_hilt_retrofit_example.ui.tmdb.movie.models.MovieResp

class PostsAdapter(val context: Context, private val postsList: List<PostResp>) :
    RecyclerView.Adapter<PostsAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.rv_movie, parent, false)

        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        val singleUnit = postsList[position]
        holder.card.visibility = View.GONE

        holder.textView.text = singleUnit.title

    }

    override fun getItemCount(): Int {
        return postsList.size
    }

    class ViewHolder(ItemView: View) : RecyclerView.ViewHolder(ItemView) {
        val imageView: ImageView = itemView.findViewById(R.id.imageview)
        val textView: TextView = itemView.findViewById(R.id.textView)
        val card: CardView = itemView.findViewById(R.id.card)
    }
}