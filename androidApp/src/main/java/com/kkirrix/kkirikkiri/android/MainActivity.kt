package com.kkirrix.kkirikkiri.android

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.compose.setContent
import com.arkivanov.decompose.defaultComponentContext
import com.kkirrix.kkirikkiri.RootContent
import com.kkirrix.kkirikkiri.presentation.root.RootComponent

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            val rootComponent = RootComponent(defaultComponentContext())
            RootContent(component = rootComponent)
        }
    }
}
