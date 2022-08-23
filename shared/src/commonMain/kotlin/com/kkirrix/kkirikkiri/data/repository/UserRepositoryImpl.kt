package com.kkirrix.kkirikkiri.data.repository

import com.kkirrix.kkirikkiri.domain.repository.UserRepository

class UserRepositoryImpl : UserRepository {
    override suspend fun login(email: String, passwd: String) = runCatching {

    }
}