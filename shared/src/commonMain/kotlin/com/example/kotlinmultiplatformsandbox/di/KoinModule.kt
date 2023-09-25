package com.example.kotlinmultiplatformsandbox.di

import com.example.kotlinmultiplatformsandbox.Greeting
//import com.example.kotlinmultiplatformsandbox.repository.platformModule
import org.koin.core.context.startKoin
import org.koin.dsl.KoinAppDeclaration
import org.koin.dsl.module

//fun initKoin(appDeclaration: KoinAppDeclaration = {}) =
//    startKoin {
//        appDeclaration()
//        modules(commonModule(), platformModule())
//    }
//
//fun commonModule() = module {
//    single { Greeting() }
//}
//
////fun initKoin() = startKoin{
////    modules(
////        module {
////            single {
////                Greeting()
////            }
////        },
////    )
//}