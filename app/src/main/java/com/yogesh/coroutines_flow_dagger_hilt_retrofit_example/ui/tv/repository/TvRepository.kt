package com.yogesh.coroutines_flow_dagger_hilt_retrofit_example.ui.tv.repository

import com.yogesh.coroutines_flow_dagger_hilt_retrofit_example.connect.ApiService
import com.yogesh.coroutines_flow_dagger_hilt_retrofit_example.connect.Resource
import com.yogesh.coroutines_flow_dagger_hilt_retrofit_example.ui.tv.models.TvResp
import com.yogesh.coroutines_flow_dagger_hilt_retrofit_example.utils.HandleApiError
import com.yogesh.coroutines_flow_dagger_hilt_retrofit_example.utils.MyConstants
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import javax.inject.Inject

class TvRepository @Inject constructor(val apiService: ApiService) {
    val lang = "en-US"
    fun getTv(page: Int): Flow<Resource<TvResp>> = flow {
        emit(Resource.loading())
        emit(Resource.success(apiService.getTv(lang, page, MyConstants.AUTH_KEY)))
    }.catch {
        var errorString = when (it) {
            is HttpException -> HandleApiError.getServerError(
                it.code(), it.response()?.errorBody()
            )

            else -> {
                "Failed: ${it.message}"
            }
        }
        emit(Resource.failed(errorString.toString()))
    }

}