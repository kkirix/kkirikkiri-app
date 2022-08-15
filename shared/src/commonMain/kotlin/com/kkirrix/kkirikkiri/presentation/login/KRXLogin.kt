package com.kkirrix.kkirikkiri.presentation.login

enum class Platform {
    NAVER, KAKAO
}

interface KRXLogin {
    fun onLogin(platform: Platform)
}