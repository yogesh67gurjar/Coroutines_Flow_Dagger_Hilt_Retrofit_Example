package com.yogesh.coroutines_flow_dagger_hilt_retrofit_example.connect

import com.yogesh.coroutines_flow_dagger_hilt_retrofit_example.ui.movie.models.MovieResp
import com.yogesh.coroutines_flow_dagger_hilt_retrofit_example.ui.tv.models.TvResp
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Query

interface ApiService {
    @GET("3/trending/movie/day")
    suspend fun getMovies(
        @Query("language") lang: String,
        @Query("page") page: Int,
        @Header("Authorization") auth: String
    ): MovieResp

    @GET("3/trending/tv/day")
    suspend fun getTv(
        @Query("language") lang: String,
        @Query("page") page: Int,
        @Header("Authorization") auth: String
    ): TvResp
}