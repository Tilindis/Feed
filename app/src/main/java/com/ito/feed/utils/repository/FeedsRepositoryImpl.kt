package com.ito.feed.utils.repository

import android.util.Log
import com.ito.feed.utils.api.FeedsApi
import com.ito.feed.utils.api.GitApi
import com.ito.feed.utils.datastore.DataStore
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class FeedsRepositoryImpl @Inject constructor(
    private val api: FeedsApi,
    private val gitApi: GitApi,
    private val dataStore: DataStore,
) : FeedsRepository {
    override suspend fun requestFeeds() {
        runCatching {
            api.getFeeds().body()
        }.onSuccess { feedResponse ->
            feedResponse?.let { response ->
                response.timeline_url?.let {
                    dataStore.saveTimelineUrlValue(it)
                }

                response.user_url?.let {
                    dataStore.saveUserUrlValue(it)
                }

                response.security_advisories_url?.let {
                    dataStore.saveSecurityAdvisoriesUrlValue(it)
                }

                response.repository_discussions_url?.let {
                    dataStore.saveRepositoryDiscussionsUrlValue(it)
                }

                response.repository_discussions_category_url?.let {
                    dataStore.saveRepositoryDiscussionsCategoryUrlValue(it)
                }
            }
        }.onFailure {
            Log.e("FeedRepositoryImpl", "Feed Api: ${it.message}")
        }
    }

    override suspend fun requestFeed(path: String) {
        runCatching {
            gitApi.getFeed(path).body()
        }.onSuccess { feedResponse ->
            Log.e("FeedRepositoryImpl", "Feed Api: $feedResponse")
        }.onFailure {
            Log.e("FeedRepositoryImpl", "Feed Api: ${it.message}")
        }
    }

    override fun getTimelineUrlValue(): Flow<String?> {
        return dataStore.getTimelineUrlValue()
    }

    override fun getUserUrlValue(): Flow<String?> {
        return dataStore.getUserUrlValue()
    }

    override fun getSecurityAdvisoriesUrlValue(): Flow<String?> {
        return dataStore.getSecurityAdvisoriesUrlValue()
    }

    override fun getRepositoryDiscussionsUrlValue(): Flow<String?> {
        return dataStore.getRepositoryDiscussionsUrlValue()
    }

    override fun getRepositoryDiscussionsCategoryUrlValue(): Flow<String?> {
        return dataStore.getRepositoryDiscussionsCategoryUrlValue()
    }
}
