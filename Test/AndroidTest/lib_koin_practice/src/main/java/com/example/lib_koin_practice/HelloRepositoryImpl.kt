package com.example.lib_koin_practice

import com.example.lib_koin_export.HelloRepository

class HelloRepositoryImpl() : HelloRepository {
    override fun giveHello(): String = "hello koin"
}