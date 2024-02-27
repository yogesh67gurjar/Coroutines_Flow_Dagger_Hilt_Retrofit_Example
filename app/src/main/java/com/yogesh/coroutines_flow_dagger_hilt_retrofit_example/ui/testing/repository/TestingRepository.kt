package com.yogesh.coroutines_flow_dagger_hilt_retrofit_example.ui.testing.repository

import com.yogesh.coroutines_flow_dagger_hilt_retrofit_example.connect.ApiService
import com.yogesh.coroutines_flow_dagger_hilt_retrofit_example.connect.Resource
import com.yogesh.coroutines_flow_dagger_hilt_retrofit_example.ui.testing.models.PostResp
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import retrofit2.HttpException
import javax.inject.Inject
import javax.inject.Named

class TestingRepository @Inject constructor(@Named("TESTING") val apiService: ApiService) {

    fun getPosts(): Flow<Resource<List<PostResp>>> = flow {
        emit(Resource.loading())
        emit(Resource.success(apiService.getPosts()))
    }.catch {
        val error = when (it) {
            is HttpException -> "Failure: ${it.message()}"
            else -> {
                "Failure: ${it.message}"
            }
        }
        emit(Resource.failed(error))
    }.flowOn(Dispatchers.IO)


    fun getPostById(id: Int): Flow<Resource<PostResp>> = flow<Resource<PostResp>> {
        emit(Resource.loading())
        emit(Resource.success(apiService.getPostbyId(id)))
    }.catch {
        var error = when (it) {
            is HttpException -> {
                "Failed: ${it.message}"
            }

            else -> {
                "Failed: ${it.message}"
            }
        }
        emit(Resource.failed(error))
    }.flowOn(Dispatchers.IO)
}