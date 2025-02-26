package com.ito.feed.feature.login

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ito.feed.utils.interactor.LoginInteractor
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class LoginViewModel @Inject constructor(
    private val loginInteractor: LoginInteractor,
) : ViewModel() {
    private val _state = MutableStateFlow(LoginState())
    val state = _state.asStateFlow()

    fun updateUser(value: String) {
        _state.update { it.copy(currentUser = value) }
    }

    fun updateToken(value: String) {
        _state.update { it.copy(token = value) }
    }

    fun onLogin() {
        viewModelScope.launch {
            loginInteractor.saveUserNameValue(_state.value.currentUser)
            loginInteractor.saveUserTokenValue(_state.value.token)
        }
    }
}
