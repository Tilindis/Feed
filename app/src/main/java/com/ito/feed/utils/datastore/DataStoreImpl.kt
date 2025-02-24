package com.ito.feed.utils.datastore

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import com.ito.feed.utils.constants.Constants.DATASTORE_NAME
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

import com.ito.feed.utils.datastore.DataStore as AppDataStore

class DataStoreImpl @Inject constructor(@ApplicationContext val context: Context) : AppDataStore {
    override fun getTimelineUrlValue(): Flow<String?> = context.dataStore.data
        .map { preferences ->
            preferences[TIMELINE_URL_KEY]
        }

    override suspend fun saveTimelineUrlValue(status: String) {
        context.dataStore.edit { preferences ->
            preferences[TIMELINE_URL_KEY] = status
        }
    }

    override fun getUserUrlValue(): Flow<String?> = context.dataStore.data
        .map { preferences ->
            preferences[USER_URL_KEY]
        }

    override suspend fun saveUserUrlValue(status: String) {
        context.dataStore.edit { preferences ->
            preferences[USER_URL_KEY] = status
        }
    }

    override fun getSecurityAdvisoriesUrlValue(): Flow<String?> = context.dataStore.data
        .map { preferences ->
            preferences[SECURITY_ADVISORIES_URL_KEY]
        }

    override suspend fun saveSecurityAdvisoriesUrlValue(status: String) {
        context.dataStore.edit { preferences ->
            preferences[SECURITY_ADVISORIES_URL_KEY] = status
        }
    }

    override fun getRepositoryDiscussionsUrlValue(): Flow<String?> = context.dataStore.data
        .map { preferences ->
            preferences[REPOSITORY_DISCUSSIONS_URL_KEY]
        }

    override suspend fun saveRepositoryDiscussionsUrlValue(status: String) {
        context.dataStore.edit { preferences ->
            preferences[REPOSITORY_DISCUSSIONS_URL_KEY] = status
        }
    }

    override fun getRepositoryDiscussionsCategoryUrlValue(): Flow<String?> = context.dataStore.data
        .map { preferences ->
            preferences[REPOSITORY_DISCUSSIONS_CATEGORY_URL_KEY]
        }

    override suspend fun saveRepositoryDiscussionsCategoryUrlValue(status: String) {
        context.dataStore.edit { preferences ->
            preferences[REPOSITORY_DISCUSSIONS_CATEGORY_URL_KEY] = status
        }
    }

    companion object {
        private val Context.dataStore: DataStore<Preferences> by preferencesDataStore(DATASTORE_NAME)
        private val TIMELINE_URL_KEY = stringPreferencesKey("key.timeline_url")
        private val USER_URL_KEY = stringPreferencesKey("key.user_url")
        private val SECURITY_ADVISORIES_URL_KEY = stringPreferencesKey("key.security_advisories_url")
        private val REPOSITORY_DISCUSSIONS_URL_KEY = stringPreferencesKey("key.repository_discussions_url")
        private val REPOSITORY_DISCUSSIONS_CATEGORY_URL_KEY = stringPreferencesKey("key.repository_discussions_category_url")
    }
}
