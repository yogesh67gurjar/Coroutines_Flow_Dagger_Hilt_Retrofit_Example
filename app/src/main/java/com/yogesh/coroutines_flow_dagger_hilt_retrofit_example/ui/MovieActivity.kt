package com.yogesh.coroutines_flow_dagger_hilt_retrofit_example.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import com.yogesh.coroutines_flow_dagger_hilt_retrofit_example.R
import com.yogesh.coroutines_flow_dagger_hilt_retrofit_example.databinding.ActivityMovieBinding
import com.yogesh.coroutines_flow_dagger_hilt_retrofit_example.viewModel.MovieViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MovieActivity : AppCompatActivity() {
    lateinit var binding: ActivityMovieBinding
    val movieViewModel: MovieViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMovieBinding.inflate(layoutInflater)
        setContentView(binding.root)

        clickEvents()
        attachObservers()

        callInitApi()
    }

    private fun callInitApi() {

    }

    private fun attachObservers() {

    }

    private fun clickEvents() {

    }
}