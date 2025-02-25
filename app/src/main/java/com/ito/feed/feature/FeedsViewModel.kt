package com.ito.feed.feature

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ito.feed.utils.interactor.FeedsInteractor
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class FeedsViewModel @Inject constructor(
    private val feedsInteractor: FeedsInteractor,
) : ViewModel() {
    private val _state = MutableStateFlow(FeedsState())
    val state = _state.asStateFlow()

    init {
        viewModelScope.launch {
            _state.update { it.copy(isLoading = true) }
            getUrlsData()
        }
    }

    private suspend fun getUrlsData() {
        feedsInteractor.requestFeed()
        feedsInteractor.getFeeds().collect { feeds ->
            _state.update { state ->
                state.copy(feeds = feeds.map { it.toFeedsModel() })
            }
        }
        _state.update { it.copy(isLoading = false) }
    }
}
