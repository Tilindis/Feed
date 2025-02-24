package com.ito.feed.utils.response

import com.squareup.moshi.Json

data class Links(
    @field:Json(name = "timeline")
    val timeline: Link? = null,

    @field:Json(name = "user")
    val user: Link? = null,

    @field:Json(name = "repository_discussions")
    val repository_discussions: Link? = null,

    @field:Json(name = "repository_discussions_category")
    val repository_discussions_category: Link? = null,

    @field:Json(name = "security_advisories")
    val security_advisories: Link? = null,
)
