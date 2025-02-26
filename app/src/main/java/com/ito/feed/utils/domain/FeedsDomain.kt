package com.ito.feed.utils.domain

import com.ito.feed.utils.model.FeedsModel

data class FeedsDomain(
    val url: String,
    val path: String,
    val parameters: List<Parameter>,
) {
    fun toFeedsModel(): FeedsModel {
        return FeedsModel(
            url = url,
            path = path,
            parameters = parameters,
        )
    }
}

data class Parameter(
    val name: String,
    val fieldNumber: Int,
)
