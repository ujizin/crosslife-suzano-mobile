package br.com.yujiyoshimine.login.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import br.com.yujiyoshimine.commons.extensions.catchNetwork
import br.com.yujiyoshimine.commons.extensions.viewmodel.ViewModelExtensions
import br.com.yujiyoshimine.domain.model.ServerError
import br.com.yujiyoshimine.domain.model.Status
import br.com.yujiyoshimine.domain.repository.UserRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val userRepository: UserRepository,
) : ViewModel(), ViewModelExtensions {

    val login: StateFlow<LoginUiState> = MutableStateFlow(LoginUiState.Initial)

    fun fetchLogin(username: String, password: String) {
        userRepository.fetchLogin(username, password)
            .onStart { login().value = LoginUiState.Loading }
            .catchNetwork { error ->
                login().value = when (error.status) {
                    Status.UNAUTHORIZED -> LoginUiState.InvalidAccount
                    else -> LoginUiState.Error(error)
                }
            }
            .onEach { login().value = LoginUiState.Success }
            .launchIn(viewModelScope)
    }
}

sealed class LoginUiState {
    object Initial : LoginUiState()
    object Loading : LoginUiState()
    data class Error(val error: ServerError) : LoginUiState()
    object Success : LoginUiState()
    object InvalidAccount : LoginUiState()
}