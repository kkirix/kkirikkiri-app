package com.kkirrix.kkirikkiri.domain.interactor

import com.kkirrix.kkirikkiri.domain.repository.ThirdPartyRepository
import com.kkirrix.kkirikkiri.presentation.login.Platform

class ThirdPartyLoginUseCase(
    private val thirdPartyRepository: ThirdPartyRepository
) {
    operator fun invoke(platform: Platform) = thirdPartyRepository.login(platform)
}