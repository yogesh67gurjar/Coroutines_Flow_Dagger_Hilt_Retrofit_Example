package com.yogesh.coroutines_flow_dagger_hilt_retrofit_example.ui.testing

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.yogesh.coroutines_flow_dagger_hilt_retrofit_example.R
import com.yogesh.coroutines_flow_dagger_hilt_retrofit_example.databinding.ActivityPostByIdBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class PostByIdActivity : AppCompatActivity() {

    lateinit var binding: ActivityPostByIdBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPostByIdBinding.inflate(layoutInflater)
        setContentView(binding.root)


    }
}