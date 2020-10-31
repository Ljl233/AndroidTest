package com.example.lib_koin_export

import com.example.lib_koin_export.HelloRepository
import com.example.lib_koin_export.Presenter

class MySimplePresenter(val repo: HelloRepository) : Presenter {
    override fun sayHello() = "${repo.giveHello()}from $this"
}