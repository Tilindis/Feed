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
    private var counter = 0

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
                    timelineUrl?.let { getFeed(it) },
                    userUrl?.let { getFeed(it) },
                    securityUrl?.let { getFeed(it) },
                    discussionsUrl?.let { getFeed(it) },
                    categoryUrl?.let { getFeed(it) },
                )
            }
        }.also { counter = 0 }
    }

    private fun getFeed(url: String): FeedsDomain {
        val path = getPath(url)
        val pathParameters = getParametersFromPath(path)
        val parameters = if (pathParameters.isNotEmpty()) {
            getParameters(pathParameters)
        } else emptyList()
        return FeedsDomain(url, path, parameters)
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

    private fun getParameters(pathParameters: List<String>): List<Parameter> {
        val parameters = mutableListOf<Parameter>()
        pathParameters.forEach {
            parameters.add(Parameter(it, counter))
            counter += 1
        }

        return parameters
    }
}
