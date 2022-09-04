package com.kkirrix.kkirikkiri.domain.interactor

import com.kkirrix.kkirikkiri.domain.repository.GatheringRepository

class GetGatheringsUseCase(
    private val gatheringRepository: GatheringRepository
) {

    suspend operator fun invoke() = gatheringRepository.getGatherings()
}