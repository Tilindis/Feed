package com.ito.feed.utils.model

import com.ito.feed.utils.domain.Parameter

data class FeedsModel(
    val url: String,
    val path: String,
    val cardId: Int,
    val parameters: List<Parameter>,
)
