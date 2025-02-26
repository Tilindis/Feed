package com.ito.feed.utils.repository

import kotlinx.coroutines.flow.Flow

interface LoginRepository {
    fun getUserNameValue(): Flow<String?>
    suspend fun saveUserNameValue(status: String)

    fun getUserTokenValue(): Flow<String?>
    suspend fun saveUserTokenValue(status: String)
}
