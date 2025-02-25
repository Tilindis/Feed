package com.ito.feed.utils.domain

import com.ito.feed.utils.model.FeedsModel

data class FeedsDomain(
    val url: String,
    val parameters: List<String>,
) {
    fun toFeedsModel(): FeedsModel {
        return FeedsModel(
            url = url,
            parameters = parameters,
        )
    }
}
