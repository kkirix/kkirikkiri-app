package com.kkirrix.kkirikkiri.domain.repository

import com.kkirrix.kkirikkiri.presentation.login.Platform

interface ThirdPartyRepository {
    fun login(platform: Platform)
}