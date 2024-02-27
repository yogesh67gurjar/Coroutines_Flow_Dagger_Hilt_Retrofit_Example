package com.yogesh.coroutines_flow_dagger_hilt_retrofit_example.ui.testing

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.activity.viewModels
import androidx.lifecycle.lifecycleScope
import com.yogesh.coroutines_flow_dagger_hilt_retrofit_example.R
import com.yogesh.coroutines_flow_dagger_hilt_retrofit_example.connect.Resource
import com.yogesh.coroutines_flow_dagger_hilt_retrofit_example.databinding.ActivityPostByIdBinding
import com.yogesh.coroutines_flow_dagger_hilt_retrofit_example.ui.testing.models.PostResp
import com.yogesh.coroutines_flow_dagger_hilt_retrofit_example.ui.testing.viewModel.TestingViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

@AndroidEntryPoint
class PostByIdActivity : AppCompatActivity() {

    lateinit var binding: ActivityPostByIdBinding
    val testingViewModel: TestingViewModel by viewModels()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPostByIdBinding.inflate(layoutInflater)
        setContentView(binding.root)

        attachObservers()
        clickEvents()

    }

    private fun attachObservers() {
        lifecycleScope.launch {
            testingViewModel.post.collect {

                when (it) {
                    is Resource.Loading -> {
                        binding.progress.visibility = View.VISIBLE
                        binding.card.visibility = View.GONE
                    }

                    is Resource.Success -> {
                        handleSuccess(it.data)
                        binding.progress.visibility = View.GONE
                    }

                    is Resource.Failed -> {
                        handleFailure(it.message)
                        binding.progress.visibility = View.GONE
                    }

                    else -> {}
                }
            }
        }
    }

    private fun handleFailure(message: String) {
        binding.text.setText(message)
        binding.card.visibility = View.VISIBLE

    }

    private fun handleSuccess(data: PostResp) {
        binding.text.setText(data.title)
        binding.card.visibility = View.VISIBLE

    }

    private fun clickEvents() {
        binding.searchBtn.setOnClickListener {
            if (binding.searchId.text.toString().isEmpty()) {
                binding.searchId.setError("Please enter id")
                binding.searchId.requestFocus()
            } else {
                testingViewModel.getPostById(binding.searchId.text.toString().toInt())
            }
        }
    }
}