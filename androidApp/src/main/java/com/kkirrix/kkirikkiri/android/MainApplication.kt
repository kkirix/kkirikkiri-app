package com.kkirrix.kkirikkiri.android

import android.app.Application
import com.kakao.sdk.common.KakaoSdk
import com.navercorp.nid.NaverIdLoginSDK

class MainApplication : Application() {

    override fun onCreate() {
        super.onCreate()

        NaverIdLoginSDK.initialize(
            this, "AbHsG_wTKLoq9k5eOK4A", "WZHJxwDCDf", "끼리끼리"
        )
        KakaoSdk.init(this, "1bc27726f851132d862a40f4997cf524")
    }
}