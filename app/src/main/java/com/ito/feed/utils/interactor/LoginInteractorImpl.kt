package com.ito.feed.utils.interactor

import com.ito.feed.utils.repository.LoginRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class LoginInteractorImpl @Inject constructor(
    private val loginRepository: LoginRepository,
) : LoginInteractor {
    override fun getUserNameValue(): Flow<String?> {
        return loginRepository.getUserNameValue()
    }

    override suspend fun saveUserNameValue(status: String) {
        if (status.isNotBlank()) {
            loginRepository.saveUserNameValue(status)
        }
    }

    override fun getUserTokenValue(): Flow<String?> {
        return loginRepository.getUserTokenValue()
    }

    override suspend fun saveUserTokenValue(status: String) {
        if (status.isNotBlank()) {
            loginRepository.saveUserTokenValue(status)
        }
    }
}
