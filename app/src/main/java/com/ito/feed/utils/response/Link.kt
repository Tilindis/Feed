package com.ito.feed.utils.response

import com.squareup.moshi.Json

data class Link(
    @field:Json(name = "href")
    val href: String? = null,

    @field:Json(name = "type")
    val type: String? = null,
)
