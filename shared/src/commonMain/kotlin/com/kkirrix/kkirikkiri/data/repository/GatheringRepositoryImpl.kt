package com.kkirrix.kkirikkiri.data.repository

import com.kkirrix.kkirikkiri.domain.repository.GatheringRepository

class GatheringRepositoryImpl : GatheringRepository {
    override suspend fun getGatherings(): List<String> {
        return emptyList()
    }
}