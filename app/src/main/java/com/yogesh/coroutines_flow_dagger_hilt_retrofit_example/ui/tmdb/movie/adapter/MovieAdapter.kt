package com.yogesh.coroutines_flow_dagger_hilt_retrofit_example.ui.tmdb.movie.adapter

import android.content.Context
import android.graphics.drawable.Drawable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.target.Target
import com.yogesh.coroutines_flow_dagger_hilt_retrofit_example.R
import com.yogesh.coroutines_flow_dagger_hilt_retrofit_example.ui.tmdb.movie.models.MovieResp
import javax.sql.DataSource

class MovieAdapter(val context: Context, private val movieList: List<MovieResp.Results>) :
    RecyclerView.Adapter<MovieAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.rv_movie, parent, false)

        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        val singleUnit = movieList[position]
//        holder.progress.visibility = View.GONE
//        holder.imageView.visibility = View.VISIBLE

        Glide.with(context).load("https://image.tmdb.org/t/p/w500/" + singleUnit.posterPath)
//            .listener(object : RequestListener<Drawable> {
//                override fun onLoadFailed(
//                    e: GlideException?,
//                    model: Any?,
//                    target: Target<Drawable>?,
//                    isFirstResource: Boolean
//                ): Boolean {
//                    holder.progress.visibility = View.GONE
//                    holder.imageView.visibility = View.VISIBLE
//                    return false
//                }
//
//                override fun onResourceReady(
//                    resource: Drawable?,
//                    model: Any?,
//                    target: Target<Drawable>?,
//                    dataSource: com.bumptech.glide.load.DataSource?,
//                    isFirstResource: Boolean
//                ): Boolean {
//                    holder.progress.visibility = View.GONE
//                    holder.imageView.visibility = View.VISIBLE
//                    return false
//                }
//            })
            .placeholder(R.drawable.loading)
            .into(holder.imageView)
        holder.textView.text = singleUnit.title
    }

    override fun getItemCount(): Int {
        return movieList.size
    }

    class ViewHolder(ItemView: View) : RecyclerView.ViewHolder(ItemView) {
        val imageView: ImageView = itemView.findViewById(R.id.imageview)
        val textView: TextView = itemView.findViewById(R.id.textView)
//        val progress: ProgressBar = itemView.findViewById(R.id.progress)
    }
}