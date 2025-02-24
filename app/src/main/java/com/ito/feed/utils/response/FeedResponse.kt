package com.ito.feed.utils.response

import com.squareup.moshi.Json

data class FeedResponse(
    @field:Json(name = "timeline_url")
    val timeline_url: String? = null,

    @field:Json(name = "user_url")
    val user_url: String? = null,

    @field:Json(name = "repository_discussions_url")
    val repository_discussions_url: String? = null,

    @field:Json(name = "repository_discussions_category_url")
    val repository_discussions_category_url: String? = null,

    @field:Json(name = "security_advisories_url")
    val security_advisories_url: String? = null,

    @field:Json(name = "_links")
    val _links: Links? = null,
)
