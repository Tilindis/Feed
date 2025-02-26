package com.ito.feed.utils.datastore

import kotlinx.coroutines.flow.Flow

interface DataStore {
    fun getTimelineUrlValue(): Flow<String?>
    suspend fun saveTimelineUrlValue(status: String)

    fun getUserUrlValue(): Flow<String?>
    suspend fun saveUserUrlValue(status: String)

    fun getSecurityAdvisoriesUrlValue(): Flow<String?>
    suspend fun saveSecurityAdvisoriesUrlValue(status: String)

    fun getRepositoryDiscussionsUrlValue(): Flow<String?>
    suspend fun saveRepositoryDiscussionsUrlValue(status: String)

    fun getRepositoryDiscussionsCategoryUrlValue(): Flow<String?>
    suspend fun saveRepositoryDiscussionsCategoryUrlValue(status: String)

    fun getUserNameValue(): Flow<String?>
    suspend fun saveUserNameValue(status: String)

    fun getUserTokenValue(): Flow<String?>
    suspend fun saveUserTokenValue(status: String)
}
