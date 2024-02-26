package com.yogesh.coroutines_flow_dagger_hilt_retrofit_example.ui.main

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.yogesh.coroutines_flow_dagger_hilt_retrofit_example.R
import com.yogesh.coroutines_flow_dagger_hilt_retrofit_example.databinding.ActivityMainBinding
import com.yogesh.coroutines_flow_dagger_hilt_retrofit_example.ui.movie.MovieActivity
import com.yogesh.coroutines_flow_dagger_hilt_retrofit_example.ui.tv.TvActivity

class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        clickEvents()
    }

    private fun clickEvents() {
        binding.tvBtn.setOnClickListener {
            startActivity(Intent(this, TvActivity::class.java))
        }
        binding.movieBtn.setOnClickListener {
            startActivity(Intent(this, MovieActivity::class.java))
        }
    }
}