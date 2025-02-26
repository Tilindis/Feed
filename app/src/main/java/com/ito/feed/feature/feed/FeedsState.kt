package com.ito.feed.feature.feed

import com.ito.feed.utils.model.FeedsModel

data class FeedsState(
    val isLoading: Boolean = false,
    val feeds: List<FeedsModel> = listOf(),
    val selectedInput: Int = 0,
    val parameterInputs: List<String> = listOf(),
)
