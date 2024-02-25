package com.yogesh.coroutines_flow_dagger_hilt_retrofit_example.connect

import com.yogesh.coroutines_flow_dagger_hilt_retrofit_example.models.MovieResp
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {
    @GET("3/trending/movie/day")
    suspend fun getMovies(@Query("language") lang: String, @Query("page") page: Integer): MovieResp
}