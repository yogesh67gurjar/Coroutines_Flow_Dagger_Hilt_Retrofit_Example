package com.yogesh.coroutines_flow_dagger_hilt_retrofit_example.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.yogesh.coroutines_flow_dagger_hilt_retrofit_example.connect.Resource
import com.yogesh.coroutines_flow_dagger_hilt_retrofit_example.models.MovieResp
import com.yogesh.coroutines_flow_dagger_hilt_retrofit_example.repository.MovieRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MovieViewModel @Inject constructor(val movieRepository: MovieRepository) : ViewModel() {
    var _movieResp = MutableStateFlow<Resource<MovieResp>?>(null)
    var movieResp: StateFlow<Resource<MovieResp>?> = _movieResp

    fun getMovie(page: Int) {
        viewModelScope.launch {
            movieRepository.getMovies(page).collect {
                _movieResp.value = it
            }
        }
    }
}