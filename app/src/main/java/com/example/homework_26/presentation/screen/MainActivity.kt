package com.example.homework_26.presentation.screen

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.homework_26.R
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }
}