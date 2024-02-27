package com.yogesh.coroutines_flow_dagger_hilt_retrofit_example.ui.testing.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.yogesh.coroutines_flow_dagger_hilt_retrofit_example.connect.Resource
import com.yogesh.coroutines_flow_dagger_hilt_retrofit_example.ui.testing.models.PostResp
import com.yogesh.coroutines_flow_dagger_hilt_retrofit_example.ui.testing.repository.TestingRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class TestingViewModel @Inject constructor(val testingRepository: TestingRepository) : ViewModel() {

    var posts: MutableStateFlow<Resource<List<PostResp>>?> = MutableStateFlow(null)

    fun getPosts() {
        viewModelScope.launch {
            testingRepository.getPosts().collect() {
                posts.value = it
            }
        }
    }
}