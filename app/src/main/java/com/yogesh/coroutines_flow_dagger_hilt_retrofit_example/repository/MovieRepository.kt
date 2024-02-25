package com.yogesh.coroutines_flow_dagger_hilt_retrofit_example.repository

import com.yogesh.coroutines_flow_dagger_hilt_retrofit_example.connect.ApiService
import com.yogesh.coroutines_flow_dagger_hilt_retrofit_example.connect.Resource
import com.yogesh.coroutines_flow_dagger_hilt_retrofit_example.models.MovieResp
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow

import javax.inject.Inject

class MovieRepository @Inject constructor(val apiService: ApiService) {

    val lang = "en-US"
    val auth =
        "Bearer eyJhbGciOiJIUzI1NiJ9.eyJhdWQiOiJlZGU1MzFhMTJlMjY3NzVjMGYyMzZlMzkzYTYyMDJlNSIsInN1YiI6IjY1ZGIxNmQ3YWUyODExMDE2MzRjMWIzZCIsInNjb3BlcyI6WyJhcGlfcmVhZCJdLCJ2ZXJzaW9uIjoxfQ.tbtY5v3Vo7FSo8AAMYkbM0EBF-RdzUGnOtp5erj0kds"

    fun getMovies(page: Int): Flow<Resource<MovieResp>> = flow {
        emit(Resource.loading())
        emit(Resource.success(apiService.getMovies(lang, page, auth)))
    }.catch {
        emit(Resource.failed(it.message!!))
    }
}