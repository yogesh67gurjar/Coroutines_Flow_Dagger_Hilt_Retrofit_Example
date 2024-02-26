package com.yogesh.coroutines_flow_dagger_hilt_retrofit_example.utils

import android.util.Log
import com.google.gson.GsonBuilder
import com.yogesh.coroutines_flow_dagger_hilt_retrofit_example.connect.CommonErrorResp
import okhttp3.ResponseBody

object HandleApiError {

    fun getServerError( responseCode: Int, responseBody: ResponseBody?): String? {
        var serverHandling = "Error $responseCode Please try again."
        try {
            if (responseBody != null) {

                if (responseCode == 401) {
                    Log.d("YOGESH_TAG", "response code is ${responseCode}")

                } else {
                    val gson = GsonBuilder().create()
                    val commonStatusMessageResponseOne: CommonErrorResp = gson.fromJson(
                        responseBody.string(), CommonErrorResp::class.java
                    )
                    serverHandling = commonStatusMessageResponseOne.statusMessage
                    return serverHandling
                }
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }
        return serverHandling
    }

}