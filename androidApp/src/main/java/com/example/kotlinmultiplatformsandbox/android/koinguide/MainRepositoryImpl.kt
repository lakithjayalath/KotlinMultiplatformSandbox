package com.example.kotlinmultiplatformsandbox.android.koinguide

class MainRepositoryImpl(
    private val api: MyApi
): MainRepository {
    override fun doNetworkCall() {
        api.callApi()
    }

}