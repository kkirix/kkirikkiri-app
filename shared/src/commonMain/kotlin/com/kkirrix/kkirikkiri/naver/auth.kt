package com.kkirrix.kkirikkiri.naver

expect class ApplicationContext
expect interface AuthCallback

expect class NaverAuth {
    fun initialize(context: ApplicationContext, id: String, secret: String, name: String)
    fun authenticate(authCallback: AuthCallback)
}