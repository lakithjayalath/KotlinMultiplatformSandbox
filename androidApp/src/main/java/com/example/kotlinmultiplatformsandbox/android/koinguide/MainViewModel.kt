package com.example.kotlinmultiplatformsandbox.android.koinguide

import androidx.lifecycle.ViewModel

class MainViewModel(
    private val repository: MainRepository
): ViewModel() {

    fun doNetworkCall() {
        println("ViewModel working")
    }
}