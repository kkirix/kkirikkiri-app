package com.kkirrix.kkirikkiri.domain.repository

interface GatheringRepository {
    suspend fun getGatherings(): List<String>
}