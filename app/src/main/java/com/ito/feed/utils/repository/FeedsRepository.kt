package com.ito.feed.utils.repository

import kotlinx.coroutines.flow.Flow

interface FeedsRepository {
    suspend fun requestFeeds()
    suspend fun requestFeed(path: String)

    fun getTimelineUrlValue(): Flow<String?>
    fun getUserUrlValue(): Flow<String?>
    fun getSecurityAdvisoriesUrlValue(): Flow<String?>
    fun getRepositoryDiscussionsUrlValue(): Flow<String?>
    fun getRepositoryDiscussionsCategoryUrlValue(): Flow<String?>
}
