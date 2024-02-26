package com.yogesh.coroutines_flow_dagger_hilt_retrofit_example.application

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.yogesh.coroutines_flow_dagger_hilt_retrofit_example.R

class BaseActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_base)
    }


}