package com.example.kotlinmultiplatformsandbox

import org.koin.core.context.startKoin
import org.koin.dsl.module

fun initKoin() = startKoin{
    modules(
        module {
            single {
                Greeting()
            }
        },
    )
}