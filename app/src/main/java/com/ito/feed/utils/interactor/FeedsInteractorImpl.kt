package com.ito.feed.utils.interactor

import com.ito.feed.utils.domain.FeedsDomain
import com.ito.feed.utils.repository.FeedsRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.combine
import javax.inject.Inject

class FeedsInteractorImpl @Inject constructor(
    private val feedsRepository: FeedsRepository,
) : FeedsInteractor {
    override suspend fun requestFeed() {
        feedsRepository.requestFeed()
    }

    override fun getFeeds(): Flow<List<FeedsDomain>> {
        return with(feedsRepository) {
            combine(
                getTimelineUrlValue(),
                getUserUrlValue(),
                getSecurityAdvisoriesUrlValue(),
                getRepositoryDiscussionsUrlValue(),
                getRepositoryDiscussionsCategoryUrlValue(),
            ) { timelineUrl, userUrl, securityUrl, discussionsUrl, categoryUrl ->
                listOfNotNull(
                    timelineUrl?.let { FeedsDomain(it, emptyList()) },
                    userUrl?.let { FeedsDomain(it, emptyList()) },
                    securityUrl?.let { FeedsDomain(it, emptyList()) },
                    discussionsUrl?.let { FeedsDomain(it, emptyList()) },
                    categoryUrl?.let { FeedsDomain(it, emptyList()) },
                )
            }
        }
    }
}
