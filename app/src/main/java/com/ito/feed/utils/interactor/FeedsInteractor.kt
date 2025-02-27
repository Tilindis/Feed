package com.ito.feed.utils.interactor

import com.ito.feed.utils.domain.FeedsDomain
import kotlinx.coroutines.flow.Flow

interface FeedsInteractor {
    suspend fun requestFeeds()
    suspend fun requestFeed(path: String)

    fun getFeeds(): Flow<List<FeedsDomain>>
}
