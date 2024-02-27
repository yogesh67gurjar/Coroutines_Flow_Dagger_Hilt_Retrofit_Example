package com.yogesh.coroutines_flow_dagger_hilt_retrofit_example.ui.tmdb.tv.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.yogesh.coroutines_flow_dagger_hilt_retrofit_example.connect.Resource
import com.yogesh.coroutines_flow_dagger_hilt_retrofit_example.ui.tmdb.tv.models.TvResp
import com.yogesh.coroutines_flow_dagger_hilt_retrofit_example.ui.tmdb.tv.repository.TvRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class TvViewModel @Inject constructor(val tvRepository: TvRepository) : ViewModel() {
    val _tvResp = MutableStateFlow<Resource<TvResp>?>(null)
    val tvResp: StateFlow<Resource<TvResp>?> = _tvResp
    fun getTv(page: Int) {
        viewModelScope.launch {
            tvRepository.getTv(page).collect {
                _tvResp.value = it
            }
        }
    }



}