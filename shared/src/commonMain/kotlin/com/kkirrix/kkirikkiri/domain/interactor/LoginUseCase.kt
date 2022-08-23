package com.kkirrix.kkirikkiri.domain.interactor

import com.kkirrix.kkirikkiri.domain.repository.UserRepository

class LoginUseCase(
    private val userRepository: UserRepository
) {
    suspend operator fun invoke(email: String, passwd: String) = userRepository.login(email, passwd)
}