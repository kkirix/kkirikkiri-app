package com.kkirrix.kkirikkiri.naver

import platform.UIKit.UIApplication
import kotlin.naver.login.NaverThirdPartyLoginConnection
import kotlin.naver.login.NaverThirdPartyLoginConnectionDelegateProtocol

actual typealias ApplicationContext = UIApplication
actual typealias AuthCallback = NaverThirdPartyLoginConnectionDelegateProtocol

actual class NaverAuth(
    private val sdk: NaverThirdPartyLoginConnection = requireNotNull(NaverThirdPartyLoginConnection.getSharedInstance())
) {
    actual fun initialize(context: ApplicationContext, id: String, secret: String, name: String) = with(sdk) {
        setIsNaverAppOauthEnable(true)
        setIsInAppOauthEnable(true)
        setConsumerSecret(secret)
        setConsumerKey(id)
        setAppName(name)
    }

    actual fun authenticate(authCallback: AuthCallback) {
        sdk.delegate = authCallback
        sdk.requestThirdPartyLogin()
    }
}