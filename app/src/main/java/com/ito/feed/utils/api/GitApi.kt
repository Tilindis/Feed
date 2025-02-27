package com.ito.feed.utils.api

import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface GitApi {
    @GET("{path}")
    suspend fun getFeed(@Path("path") path: String): Response<String>
}
