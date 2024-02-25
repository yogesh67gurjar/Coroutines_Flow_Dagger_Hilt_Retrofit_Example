package com.yogesh.coroutines_flow_dagger_hilt_retrofit_example.ui.movie

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.yogesh.coroutines_flow_dagger_hilt_retrofit_example.R
import com.yogesh.coroutines_flow_dagger_hilt_retrofit_example.connect.Resource
import com.yogesh.coroutines_flow_dagger_hilt_retrofit_example.databinding.ActivityMovieBinding
import com.yogesh.coroutines_flow_dagger_hilt_retrofit_example.models.MovieResp
import com.yogesh.coroutines_flow_dagger_hilt_retrofit_example.ui.movie.adapter.MovieAdapter
import com.yogesh.coroutines_flow_dagger_hilt_retrofit_example.viewModel.MovieViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

@AndroidEntryPoint
class MovieActivity : AppCompatActivity() {
    lateinit var binding: ActivityMovieBinding
    val movieViewModel: MovieViewModel by viewModels()
    var page: Int = 1
    var isNextPage = false
    var movieList: MutableList<MovieResp.Results> = mutableListOf()
    var linearLayoutManager: LinearLayoutManager = LinearLayoutManager(this)
    lateinit var adapter: MovieAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMovieBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initSetup()
        clickEvents()
        attachObservers()
        initPagination()
        callInitApi()
    }

    private fun initSetup() {
        adapter = MovieAdapter(this, movieList)
        binding.movieRecyclerView.adapter = adapter
        binding.movieRecyclerView.layoutManager = linearLayoutManager
    }

    private fun callInitApi() {

        movieViewModel.getMovie(page)
    }

    fun initPagination() {
        binding.movieRecyclerView.addOnScrollListener(object : RecyclerView.OnScrollListener() {

            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                if (linearLayoutManager.findLastVisibleItemPosition() == movieList.size - 1) {
                    if (isNextPage) {
                        ++page
                        callInitApi()
                        isNextPage = false
                    }
                }
            }
        })
    }

    private fun attachObservers() {
        lifecycleScope.launch {
            movieViewModel.movieResp.collect {
                when (it) {
                    is Resource.Loading -> {
                        Log.d("Yogesh_Android", "loading")
                    }

                    is Resource.Success -> {

                        handleSuccess(it.data)
                    }

                    is Resource.Failed -> {
                        Log.d("Yogesh_Android", "failed")
                    }

                    else -> {}
                }
            }
        }
    }

    private fun handleSuccess(data: MovieResp) {
        if (data.totalPages > page) {
            isNextPage = true
        }

        movieList.addAll(data.results)

        adapter.notifyDataSetChanged()
//        Log.d("Yogesh_Android", "hao")
    }

    private fun clickEvents() {

    }
}