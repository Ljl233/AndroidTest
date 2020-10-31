package com.ljl.androidtest

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import android.widget.Toast
import com.example.lib_koin_export.MySimplePresenter
import com.example.lib_koin_export.Presenter
import org.koin.android.ext.android.inject

class Main2Activity : AppCompatActivity() {

    val firstPresenter: MySimplePresenter by inject()
    lateinit var textView: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main2)

        Toast.makeText(this, "main activity 2", Toast.LENGTH_SHORT).show()
        textView = findViewById(R.id.text)
        textView.text = firstPresenter.sayHello()
    }
}
