package com.ito.feed.utils.model

import com.ito.feed.utils.domain.Parameter

data class FeedsModel(
    val url: String,
    val path: String,
    val parameters: List<Parameter>,
)
