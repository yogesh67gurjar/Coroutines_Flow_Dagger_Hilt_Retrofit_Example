package com.yogesh.coroutines_flow_dagger_hilt_retrofit_example.ui.testing

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.activity.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.yogesh.coroutines_flow_dagger_hilt_retrofit_example.connect.Resource
import com.yogesh.coroutines_flow_dagger_hilt_retrofit_example.databinding.ActivityPostsBinding
import com.yogesh.coroutines_flow_dagger_hilt_retrofit_example.ui.testing.adapters.PostsAdapter
import com.yogesh.coroutines_flow_dagger_hilt_retrofit_example.ui.testing.models.PostResp
import com.yogesh.coroutines_flow_dagger_hilt_retrofit_example.ui.testing.viewModel.TestingViewModel
import com.yogesh.coroutines_flow_dagger_hilt_retrofit_example.ui.tmdb.movie.adapter.MovieAdapter
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class PostsActivity : AppCompatActivity() {

    val testingViewModel: TestingViewModel by viewModels()
    lateinit var binding: ActivityPostsBinding
    var postsList: MutableList<PostResp> = mutableListOf()
    var linearLayoutManager: LinearLayoutManager = LinearLayoutManager(this)

    lateinit var adapter: PostsAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPostsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initSetup()
        attachObservers()
        callGetPostsApi()
    }

    private fun callGetPostsApi() {
        testingViewModel.getPosts()
    }

    private fun attachObservers() {
        lifecycleScope.launch {
            testingViewModel.posts.collect {
                when (it) {
                    is Resource.Success -> {
                        handleSuccess(it.data)
                        binding.progressBar.visibility = View.GONE
                    }

                    is Resource.Loading -> {
                        binding.progressBar.visibility = View.VISIBLE
                    }

                    is Resource.Failed -> {
                        handleFailure(it.message)
                        binding.progressBar.visibility = View.GONE
                    }

                    else -> {
                        binding.progressBar.visibility = View.VISIBLE
                    }
                }
            }
        }

    }

    private fun handleFailure(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()

    }

    private fun handleSuccess(data: List<PostResp>) {

        postsList.addAll(data)
        adapter.notifyDataSetChanged()
    }

    private fun initSetup() {
        adapter = PostsAdapter(this, postsList)
        binding.postsRecyclerView.adapter = adapter
        binding.postsRecyclerView.layoutManager = linearLayoutManager
    }
}