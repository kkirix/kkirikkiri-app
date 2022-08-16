package com.kkirrix.kkirikkiri

import androidx.compose.runtime.Composable
import com.arkivanov.decompose.extensions.compose.jetpack.stack.Children
import com.kkirrix.kkirikkiri.login.LoginContent
import com.kkirrix.kkirikkiri.main.MainContent
import com.kkirrix.kkirikkiri.presentation.root.KRXRoot

@Composable
fun RootContent(component: KRXRoot) {
    Children(component.childStack) {
        when (val child = it.instance) {
            is KRXRoot.Child.Main -> MainContent(child.component)
            is KRXRoot.Child.Login -> LoginContent(child.component)
        }
    }
}