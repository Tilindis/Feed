package com.ito.feed.utils.interactor

import com.ito.feed.utils.domain.FeedsDomain
import com.ito.feed.utils.domain.Parameter
import com.ito.feed.utils.repository.FeedsRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.combine
import javax.inject.Inject

class FeedsInteractorImpl @Inject constructor(
    private val feedsRepository: FeedsRepository,
) : FeedsInteractor {
    private var cardCounter = 0
    private var fieldCounter = 0

    override suspend fun requestFeeds() {
        feedsRepository.requestFeeds()
    }

    override suspend fun requestFeed(path: String) {
        feedsRepository.requestFeed(path)
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
                    timelineUrl?.let { getFeed(it) },
                    userUrl?.let { getFeed(it) },
                    securityUrl?.let { getFeed(it) },
                    discussionsUrl?.let { getFeed(it) },
                    categoryUrl?.let { getFeed(it) },
                )
            }
        }.also {
            cardCounter = 0
            fieldCounter = 0
        }
    }

    private fun getFeed(url: String): FeedsDomain {
        val path = getPath(url)
        val pathParameters = getParametersFromPath(path)
        val parameters = if (pathParameters.isNotEmpty()) {
            getParameters(pathParameters, cardCounter)
        } else emptyList()
        return FeedsDomain(url, path, cardCounter, parameters).also { cardCounter += 1 }
    }

    private fun getPath(url: String): String {
        val pathStartIndex = url.indexOf('/', url.indexOf("//") + 2)
        return url.substring(pathStartIndex)
    }

    private fun getParametersFromPath(path: String): List<String> {
        val regex = Regex("\\{([^}]+)\\}")
        val matches = regex.findAll(path)
        return matches.map { it.groupValues[1] }.toList()
    }

    private fun getParameters(pathParameters: List<String>, cardId: Int): List<Parameter> {
        val parameters = mutableListOf<Parameter>()
        pathParameters.forEach {
            parameters.add(Parameter(it, cardId, fieldCounter))
            fieldCounter += 1
        }

        return parameters
    }
}
