package com.kkirrix.kkirikkiri.domain.interactor

import com.kkirrix.kkirikkiri.domain.repository.CategoryRepository

class GetCategoriesUseCase(
    private val categoryRepository: CategoryRepository
) {
    suspend operator fun invoke() = categoryRepository.getCategories()
}