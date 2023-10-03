package com.example.kotlinmultiplatformsandbox.android.koinguide

import retrofit2.http.GET

interface MyApi {

    @GET("my/endpoint")
    fun callApi()
}