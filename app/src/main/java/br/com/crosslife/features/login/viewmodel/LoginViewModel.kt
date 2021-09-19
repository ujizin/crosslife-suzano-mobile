package br.com.crosslife.features.login.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import br.com.crosslife.core.network.ServerError
import br.com.crosslife.core.network.Status
import br.com.crosslife.data.Result
import br.com.crosslife.domain.repositories.UserRepository
import br.com.crosslife.extensions.notify
import br.com.crosslife.extensions.catchNetwork
import br.com.crosslife.extensions.viewmodel.ViewModelExtensions
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val userRepository: UserRepository,
) : ViewModel(), ViewModelExtensions {

    val login: StateFlow<LoginUiState> = MutableStateFlow(LoginUiState.Initial)
    val logout: StateFlow<Result<Unit>> = MutableStateFlow(Result.Initial)
    val forgotPassword: StateFlow<Result<Unit>> = MutableStateFlow(Result.Initial)

    fun fetchLogin(username: String, password: String) {
        userRepository.fetchLogin(username, password)
            .onStart { login().value = LoginUiState.Loading }
            .catchNetwork { error ->
                login().value = when (error.status) {
                    Status.Unauthorized -> LoginUiState.InvalidAccount
                    else -> LoginUiState.Error(error)
                }
            }
            .onEach { login().value = LoginUiState.Success }
            .launchIn(viewModelScope)
    }

    fun fetchLogout() {
        userRepository.fetchLogout()
            .notify(viewModelScope, logout())
    }

    fun fetchForgotPassword(username: String) {
        userRepository.fetchPassword(username)
            .notify(viewModelScope, forgotPassword())
    }
}

sealed class LoginUiState {
    object Initial : LoginUiState()
    object Loading : LoginUiState()
    data class Error(val error: ServerError) : LoginUiState()
    object Success : LoginUiState()
    object InvalidAccount : LoginUiState()
}