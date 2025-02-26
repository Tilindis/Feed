package com.ito.feed.utils.repository

import com.ito.feed.utils.datastore.DataStore
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class LoginRepositoryImpl @Inject constructor(
    private val dataStore: DataStore,
) : LoginRepository {
    override fun getUserNameValue(): Flow<String?> {
        return dataStore.getUserNameValue()
    }

    override suspend fun saveUserNameValue(status: String) {
        dataStore.saveUserNameValue(status)
    }

    override fun getUserTokenValue(): Flow<String?> {
        return dataStore.getUserTokenValue()
    }

    override suspend fun saveUserTokenValue(status: String) {
        dataStore.saveUserTokenValue(status)
    }
}
