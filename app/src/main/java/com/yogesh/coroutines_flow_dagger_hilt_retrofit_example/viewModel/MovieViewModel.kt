package com.yogesh.coroutines_flow_dagger_hilt_retrofit_example.viewModel

import androidx.lifecycle.ViewModel
import com.yogesh.coroutines_flow_dagger_hilt_retrofit_example.repository.MovieRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MovieViewModel @Inject constructor(movieRepository: MovieRepository) : ViewModel() {

}