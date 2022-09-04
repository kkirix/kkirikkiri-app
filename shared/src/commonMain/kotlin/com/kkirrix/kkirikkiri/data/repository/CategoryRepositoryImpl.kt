package com.kkirrix.kkirikkiri.data.repository

import com.kkirrix.kkirikkiri.domain.repository.CategoryRepository

class CategoryRepositoryImpl : CategoryRepository {
    override suspend fun getCategories(): List<String> {
        return listOf("축구", "농구", "야구", "볼링", "배구", "골프", "러닝", "등산", "자전거", "클라이밍")
            .sorted()
    }
}