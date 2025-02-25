package com.ito.feed.feature

import com.ito.feed.utils.model.FeedsModel

data class FeedsState(
    val isLoading: Boolean = false,
    val feeds: List<FeedsModel> = listOf(),
)
