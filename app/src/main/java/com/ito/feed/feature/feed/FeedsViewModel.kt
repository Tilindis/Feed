package com.ito.feed.feature.feed

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ito.feed.utils.interactor.FeedsInteractor
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.first
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
        val initialFeeds = feedsInteractor.getFeeds().first()
        if (initialFeeds.isEmpty()) feedsInteractor.requestFeeds()
        feedsInteractor.getFeeds().collect { feeds ->
            val feedList = feeds.map { it.toFeedsModel() }
            val parametersSize = feedList.flatMap { it.parameters }.size
            _state.update { state ->
                state.copy(feeds = feedList, parameterInputs = List(size = parametersSize) { "" })
            }
        }
        _state.update { it.copy(isLoading = false) }
    }

    fun setSelectedInputField(index: Int) {
        _state.update { it.copy(selectedInput = index) }
    }

    fun setSelectedInputValue(input: String) {
        _state.update {
            it.copy(
                parameterInputs = buildList {
                    addAll(it.parameterInputs)
                    set(it.selectedInput, input)
                }
            )
        }
    }

    fun requestFeed(cardId: Int) {
        val path = _state.value.feeds.first { it.cardId == cardId }.path
        val parameters = _state.value.feeds.flatMap { it.parameters }.filter { it.cardId == cardId }

        var filledPath = path
        parameters.forEach { parameter ->
            val placeholder = "{${parameter.name}}"
            val replacement = _state.value.parameterInputs[parameter.fieldNumber]
            filledPath = filledPath.replace(placeholder, replacement)
        }

        viewModelScope.launch {
            feedsInteractor.requestFeed(filledPath.removePrefix("/"))
        }
    }
}
