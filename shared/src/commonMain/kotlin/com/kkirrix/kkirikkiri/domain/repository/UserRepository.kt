package com.kkirrix.kkirikkiri.domain.repository

interface UserRepository {
    suspend fun login(email: String, passwd: String): Result<Unit>
}