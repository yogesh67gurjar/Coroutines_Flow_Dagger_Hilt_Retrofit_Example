package com.yogesh.coroutines_flow_dagger_hilt_retrofit_example.ui.tv

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.activity.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.yogesh.coroutines_flow_dagger_hilt_retrofit_example.R
import com.yogesh.coroutines_flow_dagger_hilt_retrofit_example.connect.Resource
import com.yogesh.coroutines_flow_dagger_hilt_retrofit_example.databinding.ActivityTvBinding
import com.yogesh.coroutines_flow_dagger_hilt_retrofit_example.ui.movie.adapter.MovieAdapter
import com.yogesh.coroutines_flow_dagger_hilt_retrofit_example.ui.tv.adapter.TvAdapter
import com.yogesh.coroutines_flow_dagger_hilt_retrofit_example.ui.tv.models.TvResp
import com.yogesh.coroutines_flow_dagger_hilt_retrofit_example.ui.tv.viewModel.TvViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class TvActivity : AppCompatActivity() {
    lateinit var binding: ActivityTvBinding

    val tvViewModel: TvViewModel by viewModels()
    var linearLayoutManager: LinearLayoutManager = LinearLayoutManager(this)
    lateinit var adapter: TvAdapter
    var page: Int = 1
    var isNextPage = false

    var tvList: MutableList<TvResp.Results> = mutableListOf()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityTvBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initSetup()
        attachObservers()
        initPagination()
        callInitApi()
    }

    private fun attachObservers() {
        lifecycleScope.launch {
            tvViewModel.tvResp.collect {
                when (it) {
                    is Resource.Loading -> {
                        binding.progressBar.visibility = View.VISIBLE
                    }

                    is Resource.Success -> {
                        handleSuccess(it.data)
                        binding.progressBar.visibility = View.GONE
                    }

                    is Resource.Failed -> {
                        handleError(it.message)
                        binding.progressBar.visibility = View.GONE
                    }

                    else -> {
                        binding.progressBar.visibility = View.VISIBLE
                    }
                }
            }
        }
    }

    private fun handleError(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }

    private fun handleSuccess(data: TvResp) {
        if (data.totalPages > page) {
            isNextPage = true
        }
        tvList.addAll(data.results)
        adapter.notifyDataSetChanged()

    }

    fun initPagination() {
        binding.tvRecyclerView.addOnScrollListener(object : RecyclerView.OnScrollListener() {

            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                if (linearLayoutManager.findLastVisibleItemPosition() == tvList.size - 1) {
                    if (isNextPage) {
                        ++page
                        callInitApi()
                        isNextPage = false
                    }
                }
            }
        })
    }

    private fun callInitApi() {
        tvViewModel.getTv(page)
        binding.progressBar.visibility = View.VISIBLE
    }


    private fun initSetup() {
        binding.tvRecyclerView.layoutManager = linearLayoutManager
        adapter = TvAdapter(this, tvList)
        binding.tvRecyclerView.adapter = adapter
    }
}