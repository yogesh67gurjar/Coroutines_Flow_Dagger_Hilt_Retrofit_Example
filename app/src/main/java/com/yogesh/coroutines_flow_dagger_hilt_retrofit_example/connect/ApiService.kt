package com.yogesh.coroutines_flow_dagger_hilt_retrofit_example.connect

import com.yogesh.coroutines_flow_dagger_hilt_retrofit_example.ui.testing.models.PostResp
import com.yogesh.coroutines_flow_dagger_hilt_retrofit_example.ui.tmdb.movie.models.MovieResp
import com.yogesh.coroutines_flow_dagger_hilt_retrofit_example.ui.tmdb.tv.models.TvResp
import retrofit2.http.Field
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Path
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

    @GET("posts")
    suspend fun getPosts(): List<PostResp>

    @GET("posts/{id}")
    suspend fun getPostbyId(
        @Path("id") id: Int
    ): PostResp
}