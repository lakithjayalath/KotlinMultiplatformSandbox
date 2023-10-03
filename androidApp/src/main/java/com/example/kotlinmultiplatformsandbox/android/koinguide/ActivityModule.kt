package com.example.kotlinmultiplatformsandbox.android.koinguide

import com.example.kotlinmultiplatformsandbox.android.MainActivity
import org.koin.core.qualifier.named
import org.koin.dsl.module

val activityModule = module {
    scope<MainActivity> {
        scoped(qualifier = named("hello")) {
            "Hello"
        }
        scoped(qualifier = named("bye")) {
            "Bye"
        }
    }
}