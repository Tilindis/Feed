package com.ito.feed.utils.interactor

import kotlinx.coroutines.flow.Flow

interface LoginInteractor {
    fun getUserNameValue(): Flow<String?>
    suspend fun saveUserNameValue(status: String)

    fun getUserTokenValue(): Flow<String?>
    suspend fun saveUserTokenValue(status: String)
}
