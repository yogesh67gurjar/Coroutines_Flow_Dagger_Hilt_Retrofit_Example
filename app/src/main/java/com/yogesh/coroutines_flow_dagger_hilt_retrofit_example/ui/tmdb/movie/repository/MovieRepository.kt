package com.yogesh.coroutines_flow_dagger_hilt_retrofit_example.ui.tmdb.movie.repository

import com.yogesh.coroutines_flow_dagger_hilt_retrofit_example.connect.ApiService
import com.yogesh.coroutines_flow_dagger_hilt_retrofit_example.connect.Resource
import com.yogesh.coroutines_flow_dagger_hilt_retrofit_example.ui.tmdb.movie.models.MovieResp
import com.yogesh.coroutines_flow_dagger_hilt_retrofit_example.utils.HandleApiError
import com.yogesh.coroutines_flow_dagger_hilt_retrofit_example.utils.MyConstants
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import retrofit2.HttpException

import javax.inject.Inject
import javax.inject.Named

class MovieRepository @Inject constructor(@Named("TMDB") val apiService: ApiService) {

    val lang = "en-US"

    fun getMovies(page: Int): Flow<Resource<MovieResp>> = flow {
        emit(Resource.loading())
        emit(Resource.success(apiService.getMovies(lang, page, MyConstants.AUTH_KEY)))
    }.catch {
        val errorMessage = when (it) {
            is HttpException -> HandleApiError.getServerError(
                it.code(),
                it.response()?.errorBody()
            )

            else -> "Failed: ${it.message}"
        }
        emit(Resource.failed(errorMessage.toString()))
    }.flowOn(Dispatchers.IO)

    //  getMovies method will return a flow which includes all the possible states of response i.e. loading,failure,success
    //  firstly we'll emit loading
    //  after that we'll emit success with api's response as parameter
    //  we don't need to handle failure in the flow block because we have catch block for it (server errors)
    //  in the catch block, firstly we'll check if the error is HttpException
    //  we'll run this flow on IO thread
}