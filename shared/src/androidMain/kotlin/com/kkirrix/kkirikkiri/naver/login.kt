package com.kkirrix.kkirikkiri.naver

import com.navercorp.nid.NaverIdLoginSDK

actual class NaverLogin(
    private val sdk: NaverIdLoginSDK = NaverIdLoginSDK
) {
    actual fun getAccessToken() = sdk.getAccessToken()
    actual fun getRefreshToken() = sdk.getRefreshToken()
    //	actual fun getExpiresAt() = sdk.getExpiresAt()
    actual fun getTokenType() = sdk.getTokenType()
    actual fun getState() = sdk.getState().ordinal
}