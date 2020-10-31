package com.ljl.androidtest

import com.example.lib_koin_export.HelloRepository
import com.example.lib_koin_practice.HelloRepositoryImpl
import com.example.lib_koin_export.MySimplePresenter
import org.koin.dsl.module

val appModule = module {
    //single instance of HelloRepository
    single<HelloRepository> { HelloRepositoryImpl() }

    //simple presenter factory
    factory { MySimplePresenter(get()) }
}