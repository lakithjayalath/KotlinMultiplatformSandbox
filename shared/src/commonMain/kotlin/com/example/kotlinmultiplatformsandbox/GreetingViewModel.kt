package com.example.kotlinmultiplatformsandbox

import dev.icerock.moko.mvvm.viewmodel.ViewModel

class GreetingViewModel(private val greeting: Greeting): ViewModel() {

    suspend fun getGreetMessageFromGreeting(): Array<String> = greeting.greetMessage()

    suspend fun getMissionNameFromGreeting(): String = greeting.getMissionName()
}