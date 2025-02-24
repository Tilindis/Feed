package com.ito.feed.utils.api

import com.ito.feed.utils.response.FeedsResponse
import retrofit2.Response
import retrofit2.http.GET

interface FeedsApi {
    @GET("feeds")
    suspend fun getFeeds(): Response<FeedsResponse>
}
