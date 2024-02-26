package com.yogesh.coroutines_flow_dagger_hilt_retrofit_example.connect

import com.google.gson.annotations.SerializedName

class CommonErrorResp(
    @SerializedName("status_code") var statusCode: Int? = null,
    @SerializedName("status_message") var statusMessage: String = "",
    @SerializedName("success") var success: Boolean? = null
)