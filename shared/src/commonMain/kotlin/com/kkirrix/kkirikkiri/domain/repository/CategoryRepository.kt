package com.kkirrix.kkirikkiri.domain.repository

interface CategoryRepository {
    suspend fun getCategories(): List<String>
}